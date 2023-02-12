package com.backend.spring.boot.services;

import com.backend.spring.boot.entities.Post;
import com.backend.spring.boot.payloads.PostDto;

import java.util.List;

public interface PostService {

    //create

    PostDto createPost (PostDto postDto, Integer userId,Integer categoryId);

    // update

    PostDto updatePost (PostDto postDto,Integer postId);

    //get

    PostDto getPostById (Integer postId);


    // delete

    void deletePost(Integer postId);


    //getAllPost

    List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);


   // get Post by category

    List<PostDto> getPostByCategory(Integer categoryId);

    //get Post by users
    List<PostDto> getPostByUser(Integer userId);








}
