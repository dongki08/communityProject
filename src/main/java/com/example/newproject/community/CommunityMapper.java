package com.example.newproject.community;

import com.example.newproject.community.model.CommunityInsDto;
import com.example.newproject.community.model.CommunitySelPicVo;
import com.example.newproject.community.model.CommunityUpdDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {

    // -----------커뮤니티 등록 --------------
    int communityIns(CommunityInsDto dto);

    int communityInsPic(CommunityInsDto dto);

    // -------------------------------------


    // -----------커뮤니티 수정---------------

    List<CommunitySelPicVo> communitySelBoard(int board);
    int communityUpd(CommunityUpdDto dto);

    List<CommunitySelPicVo> communitySelPic(List<Integer> ipic);

    int communityDelPic(List<Integer> ipic);

    int communityInsPic(CommunityUpdDto dto);


}
