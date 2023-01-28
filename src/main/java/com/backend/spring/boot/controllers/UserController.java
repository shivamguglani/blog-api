package com.backend.spring.boot.controllers;

import com.backend.spring.boot.entities.User;
import com.backend.spring.boot.payloads.ApiResponse;
import com.backend.spring.boot.payloads.UserDto;
import com.backend.spring.boot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/test")
    public  String tester(){
        return "this is test";
    }

    @Autowired
  public UserService userService;



    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user1=this.userService.createUser(userDto);

        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

@PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable int userId){
        UserDto user1=this.userService.updateUser(userDto,userId);

        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);


    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable int userId){

        UserDto user1=this.userService.getUserById(userId);

        return  new ResponseEntity<>(user1,HttpStatus.OK);
    }


    @GetMapping("/all")
    public  List<UserDto> getAllUsers(){
        List<UserDto> list =this.userService.getAllUsers();

        return  list;
    }


    @DeleteMapping("/{userId}")

    public ResponseEntity <ApiResponse> deleteUser(@PathVariable   int userId){

       this.userService.deleteUser(userId);



//        Map <String,String> res=new HashMap<>();
//        res.put("message","user deleted successfully");


        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted success",true), HttpStatus.OK);
    }




}
