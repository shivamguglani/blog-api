package com.backend.spring.boot.payloads;

import com.backend.spring.boot.entities.Categories;
import com.backend.spring.boot.entities.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter@Setter@NoArgsConstructor
public class PostDto {

    private Integer id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String content;


    private  String imageName;


    private Date date;


    private UserDto user;


    private CategoryDto categories;

}
