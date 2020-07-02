package com.qqri.whatsub.web.dto;


import com.qqri.whatsub.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String day;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.day = entity.getDay();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }

}
