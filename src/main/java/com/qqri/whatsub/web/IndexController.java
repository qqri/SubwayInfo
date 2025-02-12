package com.qqri.whatsub.web;

import com.qqri.whatsub.service.posts.PostsService;
import com.qqri.whatsub.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

/*
* model : 서버 템플릿 엔진에서 사용할 수 있는 개체를 저장할 수 있다.
* 여기서는 postsService.findAllDesc() 로 가져온 결과를 posts 로 index.mustache 에 전달한다.
* */
//    @GetMapping("/")
//    public String index(Model model){
//        model.addAttribute("posts", postsService.findAllDesc());
//
//        return "index";
//    }

    @GetMapping("/subway")
    public String toSubway() {
        return "subway-index";
    }


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";

    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
