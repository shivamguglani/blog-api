package com.backend.spring.boot.repositories;

import com.backend.spring.boot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
