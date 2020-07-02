package com.qqri.whatsub.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

/*MyBatis의 DAO와 같은 역할을 한다.
   인터페이스를 생성하면 기본적인 CRUD가 생성된다.
*/

