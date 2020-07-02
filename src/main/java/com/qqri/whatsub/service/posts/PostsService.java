package com.qqri.whatsub.service.posts;

import com.qqri.whatsub.domain.posts.Posts;
import com.qqri.whatsub.domain.posts.PostsRepository;
import com.qqri.whatsub.web.dto.PostsListResponseDto;
import com.qqri.whatsub.web.dto.PostsResponseDto;
import com.qqri.whatsub.web.dto.PostsSaveRequestDto;
import com.qqri.whatsub.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
    * update 기능에서 데이터베이스에 쿼리 날리는 부분이 존재하지 않는다.
    * 이는 JPA의 영속성 컨텍스트 때문이다.
    * 영속성 컨텍스트란? 엔티티를 영구 저장하는 환경이다.
    *
    * 트랜잭션 안에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
    *
    * 트랜잭션이 끝나는 시점에서 해당 테이블에 변경분을 반영한다.
    * entity 객체의 값만 변경하면 별도록  update쿼리를 날릴 필요가 없는 것이다.
    * */
    @Transactional
    public Long update(Long id , PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        posts.update(requestDto.getDay(),requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto :: new)
                .collect(Collectors.toList());
    }
}
