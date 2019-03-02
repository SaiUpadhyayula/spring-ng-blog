package com.programming.techie.springngblog.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostDto {
    private Long id;
    @NotEmpty
    private String content;
    @NotEmpty
    private String title;
    private String username;
    private String permalink;
}
