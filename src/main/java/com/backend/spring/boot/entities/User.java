package com.backend.spring.boot.entities;

import com.backend.spring.boot.payloads.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_id_email", columnList = "email")
}, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),

})

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

    @Column(columnDefinition = "boolean default true")
      private boolean is_active =true;

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();



//    private List<Comment> commentList=new ArrayList<>();


    @OneToOne
    private Comment comment;


}
