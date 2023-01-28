package com.backend.spring.boot.services;

import com.backend.spring.boot.payloads.UserDto;

import java.util.List;

public interface UserService {

  UserDto createUser(UserDto user);
  UserDto updateUser(UserDto user,Integer userID);
  UserDto getUserById(Integer userId);

  List<UserDto> getAllUsers();

  void deleteUser(Integer userId);




  String changeStatus(Integer userId, boolean status);
}
