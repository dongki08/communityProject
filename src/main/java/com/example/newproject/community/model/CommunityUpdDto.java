package com.example.newproject.community.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommunityUpdDto {
    @JsonIgnore
    private long iuser;

    private int iboard;


    private List<Integer> ipic;
    private String title;
    private String content;
    @JsonIgnore
    private List<String> pics = new ArrayList<>();

    private List<MultipartFile> files;
}
