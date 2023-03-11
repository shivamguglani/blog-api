package com.backend.spring.boot.services.impl;

import com.backend.spring.boot.entities.Comment;
import com.backend.spring.boot.entities.Post;
import com.backend.spring.boot.entities.User;
import com.backend.spring.boot.exceptions.ResourceNotFound;
import com.backend.spring.boot.payloads.CommentDto;
import com.backend.spring.boot.repositories.CommentRepo;
import com.backend.spring.boot.repositories.PostRepo;
import com.backend.spring.boot.repositories.UserRepo;
import com.backend.spring.boot.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "userId", userId));

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", postId));

      Comment comment=  this.modelMapper.map(commentDto, Comment.class);

      comment.setUser(user);
      comment.setPost(post);

        Comment savedComment = this.commentRepo.save(comment);



        return  this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFound("comment", "commentId", commentId));

        return  this.modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFound("comment", "commentId", commentId));

        comment.setContent(commentDto.getContent());

        Comment savedComment = commentRepo.save(comment);


        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public String deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFound("comment", "commentId", commentId));

        commentRepo.delete(comment);

        return  "Comment deleted successfully";


    }
}
