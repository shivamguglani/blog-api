package com.backend.spring.boot.controllers;

import com.backend.spring.boot.configs.Appconstants;
import com.backend.spring.boot.entities.Post;
import com.backend.spring.boot.payloads.PostDto;
import com.backend.spring.boot.payloads.PostResponse;
import com.backend.spring.boot.payloads.UserDto;
import com.backend.spring.boot.services.FileService;
import com.backend.spring.boot.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {




    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
//        UserDto user1=this.userService.createUser(userDto);

        PostDto postDto1=this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}")
    public  ResponseEntity<PostResponse>  getPostByUser(@PathVariable Integer userId, @RequestParam(value = "pageNumber",defaultValue = Appconstants.PAGE_NUMBER,required = false) Integer pageNumber,
        @RequestParam(value = "pageSize",defaultValue = Appconstants.PAGE_SIZE,required = false) Integer pageSize ){

        PostResponse postResponse = this.postService.getPostByUser(userId,pageNumber,pageSize);

        return  new ResponseEntity<>(postResponse,HttpStatus.OK);
    }



    @GetMapping("/category/{categoryId}")

    public  ResponseEntity<PostResponse> getPostsbyCategory (@PathVariable Integer categoryId ,@RequestParam(value = "pageNumber",defaultValue = Appconstants.PAGE_NUMBER,required = false) Integer pageNumber,
    @RequestParam(value = "pageSize",defaultValue = Appconstants.PAGE_SIZE,required = false) Integer pageSize )
    {


         PostResponse postResponse=  this.postService.getPostByCategory(categoryId,pageNumber,pageSize);

        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }


    @GetMapping("/all")

    public  ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = Appconstants.PAGE_NUMBER,required = false)
                                                          Integer pageNumber, @RequestParam(value = "pageSize",defaultValue =Appconstants.PAGE_NUMBER,required = false) Integer pageSize,
        @RequestParam(value = "sortby",defaultValue = Appconstants.SORT_BY,required = false) String sortby,
                                                     @RequestParam(value = "order",defaultValue = Appconstants.SORT_ORDER,required = false) String order
                                                     ){


        PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize,sortby,order);

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



    @GetMapping("/search/content")

    private  ResponseEntity<List<PostDto>> searchPost (@RequestParam(value = "keyword",required = true) String keyword ){

        List<PostDto> postDtos = this.postService.searchPosts(keyword);

        return  new ResponseEntity<>(postDtos,HttpStatus.OK);


    }


    @GetMapping("/search/postname")

    private  ResponseEntity<List<PostDto>> searchInPost(@RequestParam(value = "keyword",required = true) String keyword ){
        List<PostDto> postDtos = this.postService.SearchPostByName(keyword);

        return  new ResponseEntity<>(postDtos,HttpStatus.OK);
    }




    @PostMapping("/image/upload/{postId}")
    private ResponseEntity<PostDto> uploadImage(
            @PathVariable Integer postId,
            @RequestParam("image") MultipartFile image
            ) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String uploadImageName = this.fileService.uploadImage(path, image);

        postDto.setImageName(uploadImageName);
        PostDto postDto1 = this.postService.updatePost(postDto, postId);

    return  new ResponseEntity<>(postDto1,HttpStatus.OK);
    }


    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_PNG_VALUE)
    public void downloadImage(@PathVariable ("imageName") String imageName, HttpServletResponse response) throws IOException {

        InputStream file = this.fileService.getFile(path, imageName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(file, response.getOutputStream());


    }






}
