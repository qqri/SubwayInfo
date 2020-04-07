package com.qqri.whatsub.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String day;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String day, String content) {
        this.day = day;
        this.content = content;
    }
}
