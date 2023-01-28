package com.backend.spring.boot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users",uniqueConstraints = { @UniqueConstraint(columnNames = { "email"}) })

@NoArgsConstructor
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name",nullable = false,length = 100)


    private String name;
    @Column(unique = true)
     private  String email;
      private  String password;
      private  String address;



}
