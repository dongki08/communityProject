package com.example.newproject.community;

import com.example.newproject.community.model.CommunityInsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {

    int communityIns(CommunityInsDto dto);

    int communityInsPic(CommunityInsDto dto);

    int communityUpd(CommunityInsDto dto);
}
