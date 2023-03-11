package com.backend.spring.boot.repositories;

import com.backend.spring.boot.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
