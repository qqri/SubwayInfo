package com.qqri.whatsub.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String day = "테스트 요일";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .day(day)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getDay()).isEqualTo(day);
        assertThat(posts.getContent()).isEqualTo(content);
    }
//
//    @Test
//    public void BaseTimeEntity_등록 () {
//        //given
//        LocalDateTime now = LocalDateTime.now();
//        postsRepository.save(Posts.builder()
//                .day("테스트 게시글")
//                .content("테스트 본문")
//                .author("jojoldu@gmail.com")
//                .build());
//        //when
//        List<Posts> postsList = postsRepository.findAll();
//
//        //then
//        Posts posts = postsList.get(0);
////        assertTrue(posts.getCreatedDate().isAfter(now));
////        assertTrue(posts.getModifiedDate().isAfter(now));
//    }

}