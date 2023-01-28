package com.backend.spring.boot.services.impl;

import com.backend.spring.boot.entities.User;
import com.backend.spring.boot.exceptions.ResourceNotFound;
import com.backend.spring.boot.payloads.UserDto;
import com.backend.spring.boot.repositories.UserRepo;
import com.backend.spring.boot.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public  UserDto createUser(UserDto userdto){
        User user =this.dtoToUser(userdto);

    User savedUser=this.userRepo.save(user);

    UserDto   savedDto=this.usertoUserDto(savedUser);
        return savedDto;
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userID) {

        User newUser=this.userRepo.findById(userID).orElseThrow(()->new ResourceNotFound("User","userId",userID));
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setAddress(user.getAddress());

        User user1=this.userRepo.save(newUser);

        UserDto   savedDto=this.usertoUserDto(user1);

        return savedDto;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User","userId",userId));

        UserDto   savedDto=this.usertoUserDto(user);

        return savedDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> list=this.userRepo.findAll();
        List<UserDto> dtoList=list.stream().map(m-> this.usertoUserDto(m)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User","userId",userId));
        this.userRepo.delete(user);

    }


    private User dtoToUser (UserDto userdto){
        User user = this.modelMapper.map(userdto,User.class);
//        user.setId(userdto.getId());
//        user.setName(userdto.getName());
//        user.setEmail(userdto.getEmail());
//        user.setAddress(userdto.getAddress());
//        user.setPassword(userdto.getPassword());

        return  user;
    }


    private UserDto usertoUserDto (User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);

//        userDto.setId(user.getId());
//        userDto.setAddress(user.getAddress());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());

        return userDto;


    }
}
