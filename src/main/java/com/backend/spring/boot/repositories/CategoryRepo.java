package com.backend.spring.boot.repositories;

import com.backend.spring.boot.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Categories,Integer> {



}
