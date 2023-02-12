package com.backend.spring.boot.services;

import com.backend.spring.boot.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create

     CategoryDto createCategory(CategoryDto categoryDto);

    //update

      CategoryDto updateCategory (CategoryDto categoryDto,Integer categoryId);



    //delete

     void  deleteCategory (Integer categoryId);


    //get

      CategoryDto getCategoryById(Integer categoryId);


    //get All

     List<CategoryDto> getAllCategories ();



}
