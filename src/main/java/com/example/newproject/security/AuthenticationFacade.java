package com.example.newproject.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public MyUserDetails getLoginUser() {
        return (MyUserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public long getLoginUserPk() {
        MyUserDetails myUserDetails = getLoginUser();
        return myUserDetails == null ? 0 : myUserDetails
                .getMyPrincipal()
                .getIuser();

    }
//    public int getLoginOwnerCheckShop(){
//        MyUserDetails myUserDetails = getLoginUser();
//        return myUserDetails == null ? 0 : myUserDetails
//                .getMyPrincipal()
//                .getCheckShop();
//    }
//    public long getLoginOwnerShopPk() {
//        MyUserDetails myUserDetails = getLoginUser();
//        return myUserDetails == null ? 0 : myUserDetails
//                .getMyPrincipal()
//                .getIshop();
//    }
//    public int getCheckShop(){
//        MyUserDetails myUserDetails = getLoginUser();
//        if(myUserDetails.getMyPrincipal().getRoles().get(0).equals("OWNER")){
//            return myUserDetails.getMyPrincipal().getCheckShop();
//        }
//        return 2;
//        // 0:고기집, 1:정육점, 2: 사용자 혹은 총 관리자
//    }

//    public String getLoginUserRole(){
//        MyUserDetails myUserDetails = getLoginUser();
//        return myUserDetails == null ? null : myUserDetails
//                .getMyPrincipal()
//                .getRole();
//    }

}
