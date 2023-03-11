package com.backend.spring.boot.services;

import com.backend.spring.boot.entities.Post;
import com.backend.spring.boot.payloads.PostDto;
import com.backend.spring.boot.payloads.PostResponse;

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

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String order);


   // get Post by category

    PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize);

    //get Post by users
    PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize);


    List<PostDto> searchPosts(String keyword);

    List <PostDto> SearchPostByName(String keword);





}
