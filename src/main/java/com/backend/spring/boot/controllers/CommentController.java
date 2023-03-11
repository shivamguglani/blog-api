package com.backend.spring.boot.controllers;


import com.backend.spring.boot.payloads.CommentDto;
import com.backend.spring.boot.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    public  CommentService commentService;

    @PostMapping("/add/post/{postId}/user/{userId}")
   public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId, @PathVariable Integer userId){

        CommentDto comment = this.commentService.createComment(commentDto, postId,userId);

        return  new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @GetMapping("/{commentId}")

    public  ResponseEntity<CommentDto> getComment(@PathVariable Integer commentId){
        CommentDto commentById = this.commentService.getCommentById(commentId);

        return  new ResponseEntity<>(commentById,HttpStatus.OK);
    }



    @PutMapping("update/{commentId}")

    public  ResponseEntity<CommentDto> updateComment(@PathVariable Integer commentId,@RequestBody CommentDto commentDto){

        CommentDto commentDto1 = this.commentService.updateComment(commentDto, commentId);

        return  new ResponseEntity<>(commentDto1,HttpStatus.OK);
    }


    @DeleteMapping ("/delete/{commentId}")

    public ResponseEntity<String> deleteComment(
        @PathVariable Integer commentId){

        String s = this.commentService.deleteComment(commentId);


        return  new ResponseEntity<>(s, HttpStatus.OK);
    }



}
