package com.backend.spring.boot.services.impl;

import com.backend.spring.boot.entities.Categories;
import com.backend.spring.boot.entities.Post;
import com.backend.spring.boot.entities.User;
import com.backend.spring.boot.exceptions.ResourceNotFound;
import com.backend.spring.boot.payloads.CategoryDto;
import com.backend.spring.boot.payloads.PostDto;
import com.backend.spring.boot.repositories.CategoryRepo;
import com.backend.spring.boot.repositories.PostRepo;
import com.backend.spring.boot.repositories.UserRepo;
import com.backend.spring.boot.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepo postRepo;


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User","User_id",userId));
        Categories categories=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("Category","category Id",categoryId));

        Post post=dtoToPost(postDto);
        post.setImageName("default.png");
        Date date = new Date();
        post.setDate(date);

        System.out.println(date);

        post.setUser(user);
        post.setCategories(categories);

        Post post1=this.postRepo.save(post);

        PostDto postDto1=PostToPostDto(post1);

        return  postDto1;



    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", postId));
        post.setName(postDto.getName());
        post.setContent(postDto.getContent());


        post.setImageName(post.getImageName());
        System.out.println(postDto.getImageName());

        post.setDate(new Date());

        Post savedPost = this.postRepo.save(post);
        PostDto postDto1 = PostToPostDto(savedPost);



        return postDto1;
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", postId));

        PostDto postDto = PostToPostDto(post);


        return postDto;
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", postId));

        this.postRepo.delete(post);


    }

    @Override
    public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {



        Pageable pageable=  PageRequest.of(pageNumber,pageSize);

       Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> allPosts = pagePost.getContent();

        List<PostDto> allPostsDto = allPosts.stream().map((e) -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());

        return  allPostsDto;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Categories categories=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("category","categoryId",categoryId));
        List<Post> posts = this.postRepo.findByCategories(categories);

//        List<PostDto> postDtos=PostToPostDto(posts);

        List<PostDto> postDtoList = posts.stream().map((e) -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());

        return postDtoList;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "userId", userId));
        List<Post> postListByUser = this.postRepo.findByUser(user);

        List<PostDto> postDtos = postListByUser.stream().map((e) -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());



        return postDtos;
    }


    private Post dtoToPost (PostDto postDto){
        Post post = this.modelMapper.map(postDto,Post.class);


        return  post;
    }




    private PostDto PostToPostDto (Post post){
        PostDto postDto=this.modelMapper.map(post,PostDto.class);


        return postDto;


    }

}
