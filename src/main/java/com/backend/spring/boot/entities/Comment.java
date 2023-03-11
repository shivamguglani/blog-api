package com.backend.spring.boot.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private  String content;

//    @ManyToOne(mappedBy="comments",cascade = CascadeType.ALL)
//    @OneToMany(mapped
//    By = "categories", cascade = CascadeType.ALL,fetch = FetchType.LAZY)

    @ManyToOne
    private  Post post;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
