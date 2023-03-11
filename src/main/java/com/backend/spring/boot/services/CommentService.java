package com.backend.spring.boot.services;

import com.backend.spring.boot.payloads.CommentDto;

public interface CommentService {

   CommentDto createComment (CommentDto commentDto,Integer postId,Integer userId);

   CommentDto getCommentById(Integer commentId);

   CommentDto updateComment(CommentDto commentDto,Integer commentId);

   String deleteComment(Integer commentId);




}
