package com.qqri.whatsub.web.dto;

import com.qqri.whatsub.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String day;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String day, String content, String author) {
        this.day = day;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .day(day)
                .content(content)
                .author(author)
                .build();
    }

}
