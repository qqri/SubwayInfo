package com.qqri.whatsub.web.dto;

import com.qqri.whatsub.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String day;
    private String content;
    private String author;

    //굳이 모든 필드를 가진 생성자가 필요하지 않으므로 entity를 가지고 처리한다.
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.day = entity.getDay();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
