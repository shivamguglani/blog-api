package com.backend.spring.boot.repositories;

import com.backend.spring.boot.entities.Categories;
import com.backend.spring.boot.entities.Post;
import com.backend.spring.boot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    //Custom Finder method

    List<Post> findByUser(User user);
    List<Post> findByCategories(Categories categories);


}
