package com.qqri.whatsub.domain.posts;

import com.qqri.whatsub.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
//entity 클래스와 controller 에서 쓰는 dto는 분리해서 사용해야 한다.
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 500, nullable = false)
    private String day; //요일

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String content; //교통수단

    private String author;

    @Builder
    public Posts(String day, String content, String author) {
        this.day = day;
        this.content = content;
        this.author = author;
    }

    public void update(String day , String content) {
        this.day = day;
        this.content = content;
    }
}
