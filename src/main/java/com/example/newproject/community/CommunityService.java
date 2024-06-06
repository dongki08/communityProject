package com.example.newproject.community;

import com.example.newproject.common.MyFileUtils;
import com.example.newproject.community.model.CommunityInsDto;
import com.example.newproject.community.model.CommunityInsPicVo;
import com.example.newproject.community.model.CommunitySelPicVo;
import com.example.newproject.community.model.CommunityUpdDto;
import com.example.newproject.security.AuthenticationFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final CommunityMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;


    @Transactional
    public CommunityInsPicVo communityIns(CommunityInsDto dto) {
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
        CommunityInsPicVo vo = CommunityInsPicVo.builder()
                .iboard(dto.getIboard())
                .pics(dto.getPics())
                .build();

        return vo;
    }

    @Transactional
    public CommunityInsPicVo communityUpd(CommunityUpdDto dto) {
        //List<CommunitySelPicVo> bDto = mapper.communitySelBoard(dto.getIboard());

        dto.setIuser(authenticationFacade.getLoginUserPk());

        mapper.communityUpd(dto);

        String target = "/community/" + dto.getIboard();
        if(dto.getIpic() != null && !dto.getIpic().isEmpty()) {
            List<CommunitySelPicVo> ipic = mapper.communitySelPic(dto.getIpic());
            for (CommunitySelPicVo ipics : ipic) {
                myFileUtils.delFolderTrigger2(target + "/" + ipics.getPic());
            }
            mapper.communityDelPic(dto.getIpic());
        }
        if(dto.getFiles() != null) {

            for (MultipartFile file : dto.getFiles()) {
                String saveFileNm = myFileUtils.transferTo(file, target);
                dto.getPics().add(saveFileNm);
            }
            mapper.communityInsPic(dto);
        }
        CommunityInsPicVo vo = CommunityInsPicVo.builder()
                .iboard(dto.getIboard())
                .pics(dto.getPics())
                .build();

        return vo;

    }

}
