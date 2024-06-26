package com.example.newproject.community;

import com.example.newproject.community.model.CommunityInsDto;
import com.example.newproject.community.model.CommunityInsPicVo;
import com.example.newproject.community.model.CommunityUpdDto;
import com.example.newproject.exception.AllErrorCode;
import com.example.newproject.exception.RestApiException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "커뮤니티 등록")
    public CommunityInsPicVo communityIns(@RequestPart(required = false) List<MultipartFile> pics,
                                          @RequestPart CommunityInsDto dto) {
        if(pics != null) {
            dto.setFiles(pics);
            if(dto.getFiles().size() > 5) {
                throw new RestApiException(AllErrorCode.PICS_OVER);
            }
        }
        return service.communityIns(dto);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "커뮤니티 수정")
    public CommunityInsPicVo communityUpd(@RequestPart(required = false) List<MultipartFile> pics,
                                          @RequestPart CommunityUpdDto dto) {
        if(pics != null) {
            dto.setFiles(pics);
            if(dto.getFiles().size() > 5) {
                throw new RestApiException(AllErrorCode.PICS_OVER);
            }
        }
        return service.communityUpd(dto);
    }
}
