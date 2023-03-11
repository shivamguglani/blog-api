package com.backend.spring.boot.repositories;

import com.backend.spring.boot.entities.Categories;
import com.backend.spring.boot.entities.Post;
import com.backend.spring.boot.entities.User;
import com.backend.spring.boot.payloads.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {



    Page<Post> findByUser(User user,Pageable pageable);
    Page<Post> findByCategories(Categories categories, Pageable pageable);

    List<Post> findByContentContaining(String title);


    @Query(value = "select p from Post p where p.name like :key")
    List <Post> SearchPostByName(@Param("key") String title);


}
