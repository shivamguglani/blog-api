package com.backend.spring.boot.controllers;

import com.backend.spring.boot.entities.Post;
import com.backend.spring.boot.payloads.PostDto;
import com.backend.spring.boot.payloads.UserDto;
import com.backend.spring.boot.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {




    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
//        UserDto user1=this.userService.createUser(userDto);

        PostDto postDto1=this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}")
    public  ResponseEntity<List<PostDto>>  getPostByUser(@PathVariable Integer userId){
        List<PostDto> postByUser = this.postService.getPostByUser(userId);

        return  new ResponseEntity<>(postByUser,HttpStatus.OK);
    }



    @GetMapping("/category/{categoryId}")

    public  ResponseEntity<List<PostDto>> getPostsbyCategory (@PathVariable Integer categoryId){

        List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<>(postByCategory,HttpStatus.OK);
    }


    @GetMapping("/all")

    public  ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)
                                                          Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize){
        List<PostDto> allPost = this.postService.getAllPost(pageNumber, pageSize);

        return  new ResponseEntity<>(allPost,HttpStatus.OK);
    }


    @GetMapping("/{postId}")

    public  ResponseEntity<PostDto> getPostById (@PathVariable Integer postId){
        PostDto postById = this.postService.getPostById(postId);

        return new ResponseEntity<>(postById,HttpStatus.OK);
    }



    @DeleteMapping("/{postId}")

    public String deletePost(@PathVariable Integer postId){



        this.postService.deletePost(postId);

        return "post deleted successfully";

    }


    @PutMapping("/{postId}")

    private ResponseEntity<PostDto> updatePost ( @Valid @PathVariable Integer postId,@RequestBody PostDto postDto){


        PostDto postDto1 = this.postService.updatePost(postDto, postId);

        return  new ResponseEntity<>(postDto1,HttpStatus.OK);

    }































}
