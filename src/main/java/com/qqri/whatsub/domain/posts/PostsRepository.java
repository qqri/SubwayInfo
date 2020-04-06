package com.qqri.whatsub.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}

/*MyBatis의 DAO와 같은 역할을 한다.
   인터페이스를 생성하면 기본적인 CRUD가 생성된다.
*/

