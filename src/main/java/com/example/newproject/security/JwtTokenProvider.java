package com.example.newproject.security;


import com.example.newproject.common.AppProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final ObjectMapper om;
    private final AppProperties appProperties;
    private SecretKeySpec secretKeySpec;


    @PostConstruct//bean등록 후 di 주입이 이뤄지고 나서 호출
    public void init() {
        this.secretKeySpec = new SecretKeySpec(appProperties.getJwt().getSecret().getBytes(),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateAccessToken(MyPrincipal principal) {
        return generateToken(principal, appProperties.getJwt().getAccessTokenExpiry());
    }

    public String generateRefreshToken(MyPrincipal principal) {
        return generateToken(principal, appProperties.getJwt().getRefreshTokenExpiry());
    }

    private String generateToken(MyPrincipal principal, long tokenValidMs) {
        return Jwts.builder()
                .claims(createClaims(principal))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenValidMs))
                .signWith(secretKeySpec)
                .compact();
    }

    private Claims createClaims(MyPrincipal principal) {
        try {
            String json = om.writeValueAsString(principal);
            return Jwts.claims()
                    .add("user", json)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String auth = req.getHeader(appProperties.getJwt().getHeaderSchemeName());
        if(auth == null) {
            return null;
        }
        if(auth.startsWith(appProperties.getJwt().getTokenType())) {
            return auth.substring(appProperties.getJwt().getTokenType().length()).trim(); //trim 양쪽 공백제거
        }
        return null;
    }

    public boolean isValidateToken(String token) {
        try {
            // 만료기간 현재시간보다 전이면 false, 현재시간이 만료기간보다 후이면 false
            // 만료기간 현재시간보다 후면 true, 현재시간이 만료기간보다 전이면 true
            return !getAllClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKeySpec)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = getUserDetailsFromToken(token);

        return userDetails == null
                ? null
                : new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    public UserDetails getUserDetailsFromToken(String token) {
        try {
            Claims claims = getAllClaims(token);
            String json = (String) claims.get("user");
            MyPrincipal myPrincipal = om.readValue(json, MyPrincipal.class);
            return MyUserDetails.builder()
                    .myPrincipal(myPrincipal)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
