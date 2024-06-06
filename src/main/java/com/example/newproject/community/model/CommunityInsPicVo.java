package com.example.newproject.community.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommunityInsPicVo {
    private int iboard;
    private List<String> pics;
}
