package com.backend.spring.boot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
private  Integer categoryId;
    @Column(name = "name",nullable = false)

    private  String categoryName;

    @Column(name = "description")

    private  String categoryDescription;


@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();




}
