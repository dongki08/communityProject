package com.example.newproject.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommunityInsDto {
    @JsonIgnore
    private long iuser;
    @JsonIgnore
    private int iboard;
    private String title;
    private String content;
    @JsonIgnore
    private List<String> pics = new ArrayList<>();
    @JsonIgnore
    private List<MultipartFile> files;
}
