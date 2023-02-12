package com.backend.spring.boot.services.impl;


import com.backend.spring.boot.entities.Categories;
import com.backend.spring.boot.entities.User;
import com.backend.spring.boot.exceptions.ResourceNotFound;
import com.backend.spring.boot.payloads.CategoryDto;
import com.backend.spring.boot.payloads.UserDto;
import com.backend.spring.boot.repositories.CategoryRepo;
import com.backend.spring.boot.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Categories categories=dtoToCategory(categoryDto);
        Categories savedCategory= this.categoryRepo.save(categories);
        CategoryDto categoryDto1=CategorytoCategoryDto(savedCategory);
        return  categoryDto1;

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {


        Categories categories= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("User","userId",categoryId));


        categories.setCategoryName(categoryDto.getCategoryName());
        categories.setCategoryDescription(categoryDto.getCategoryDescription());

        this.categoryRepo.save(categories);

       return CategorytoCategoryDto(categories);





    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Categories categories= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("User","userId",categoryId));
        this.categoryRepo.delete(categories);


    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {

       Categories categories= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFound("User","userId",categoryId));
      return CategorytoCategoryDto(categories);


    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Categories> list=this.categoryRepo.findAll();
        List<CategoryDto> dtoList=list.stream().map(m-> this.CategorytoCategoryDto(m)).collect(Collectors.toList());
        return dtoList;


    }


    private Categories dtoToCategory (CategoryDto categoryDto){
        Categories categories = this.modelMapper.map(categoryDto,Categories.class);


        return  categories;
    }


    private CategoryDto CategorytoCategoryDto (Categories categories){
        CategoryDto categoryDto=this.modelMapper.map(categories,CategoryDto.class);


        return categoryDto;


    }
}
