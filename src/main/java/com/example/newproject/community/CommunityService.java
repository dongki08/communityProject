package com.example.newproject.community;

import com.example.newproject.common.MyFileUtils;
import com.example.newproject.common.ResVo;
import com.example.newproject.community.model.CommunityInsDto;
import com.example.newproject.community.model.CommunityInsVo;
import com.example.newproject.security.AuthenticationFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final CommunityMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;


    @Transactional
    public CommunityInsVo communityIns(CommunityInsDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());

        mapper.communityIns(dto);

        if(dto.getFiles() != null) {
            String target = "/community/" + dto.getIboard();
            for (MultipartFile file : dto.getFiles()) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                dto.getPics().add(saveFileNm);
            }
            mapper.communityInsPic(dto);
        }
        CommunityInsVo vo = CommunityInsVo.builder()
                .iboard(dto.getIboard())
                .pics(dto.getPics())
                .build();

        return vo;
    }

}
