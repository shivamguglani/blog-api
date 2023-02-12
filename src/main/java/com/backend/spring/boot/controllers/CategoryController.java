package com.backend.spring.boot.controllers;

import com.backend.spring.boot.payloads.CategoryDto;
import com.backend.spring.boot.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    // create
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto  categoryDto){
    CategoryDto  categoryDto1= this.categoryService.createCategory(categoryDto);
    return  new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    // update

    @PutMapping ("/{categoryId}")
    public  ResponseEntity<CategoryDto> updateCategory (@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto categoryDto1=this.categoryService.updateCategory(categoryDto,categoryId);

        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }


    //get

    @GetMapping ("/{categoryId}")
    public  ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
        CategoryDto categoryDto=this.categoryService.getCategoryById(categoryId);

        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }




    //getAll

    @GetMapping("/allCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
       List<CategoryDto> categoryDtos= this.categoryService.getAllCategories();
       return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{categoryId}")
    public  String deleteCategory(@PathVariable Integer categoryId){

        this.categoryService.deleteCategory(categoryId);

        return  "Category Deleted Successfully";
    }


}
