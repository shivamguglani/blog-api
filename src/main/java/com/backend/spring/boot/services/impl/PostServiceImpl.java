package com.backend.spring.boot.services.impl;

import com.backend.spring.boot.entities.Categories;
import com.backend.spring.boot.entities.Post;
import com.backend.spring.boot.entities.User;
import com.backend.spring.boot.exceptions.ResourceNotFound;
import com.backend.spring.boot.payloads.CategoryDto;
import com.backend.spring.boot.payloads.PostDto;
import com.backend.spring.boot.payloads.PostResponse;
import com.backend.spring.boot.repositories.CategoryRepo;
import com.backend.spring.boot.repositories.PostRepo;
import com.backend.spring.boot.repositories.UserRepo;
import com.backend.spring.boot.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


        post.setImageName(postDto.getImageName());
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
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String order) {
         Pageable pageable;
        System.out.println(order);
        if (order.equalsIgnoreCase("ASC")){
             pageable=  PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).ascending());
        }
        else{
             pageable=  PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());
        }





       Page<Post> pagePost = this.postRepo.findAll(pageable);
        List<Post> allPosts = pagePost.getContent();

        List<PostDto> allPostsDto = allPosts.stream().map((e) -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(allPostsDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());


        return  postResponse;
    }

    @Override
    public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize) {

        Categories categories=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("category","categoryId",categoryId));
        Pageable pageable=  PageRequest.of(pageNumber,pageSize);



        Page<Post> posts = this.postRepo.findByCategories(categories,pageable);
        List<Post> allPosts = posts.getContent();



        List<PostDto> postDtoList = allPosts.stream().map((e) -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize) {

//        int pageNumber=0;
//        int pageSize=5;


        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "userId", userId));
        Pageable pageable=PageRequest.of(pageNumber,pageSize);



        Page<Post> postListByUser = this.postRepo.findByUser(user,pageable);

        List<PostDto> postDtos = postListByUser.stream().map((e) -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postListByUser.getNumber());
        postResponse.setTotalElements(postListByUser.getTotalPages());
        postResponse.setPageSize(postListByUser.getSize());
        postResponse.setLastPage(postListByUser.isLast());
        postResponse.setTotalPages(postListByUser.getTotalPages());

        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

        if (keyword.isBlank() ){
           throw new ResourceNotFound("keword is Empty",keyword,12);
        }


        List<Post> postList = this.postRepo.findByContentContaining(keyword);
        List<PostDto> postDtoList = postList.stream().map(e -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> SearchPostByName(String keword) {

        if (keword.isBlank() ){
            throw new ResourceNotFound("keword is Empty",keword,12);
        }

        List<Post> posts = this.postRepo.SearchPostByName("%"+keword+"%");
        List<PostDto> list = posts.stream().map(e -> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());

        return  list;


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
