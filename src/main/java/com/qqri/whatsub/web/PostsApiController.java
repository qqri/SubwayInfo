package com.qqri.whatsub.web;


import com.qqri.whatsub.service.posts.PostsService;
import com.qqri.whatsub.web.dto.PostsResponseDto;
import com.qqri.whatsub.web.dto.PostsSaveRequestDto;
import com.qqri.whatsub.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//RequiterdArgsConstructor : final로 선언된 모든 필드를 인자값으로 하는 생성자 생성해줌
@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    //등록
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    //조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
