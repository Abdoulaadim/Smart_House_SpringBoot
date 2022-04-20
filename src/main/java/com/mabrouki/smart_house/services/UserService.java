package com.mabrouki.smart_house.services;

import com.mabrouki.smart_house.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> getAllUsers();
    UserDto addUser(UserDto user);
    UserDto getUser(String email);
    boolean delete(String id);
    UserDto updateUser(String id,UserDto userDto) throws Exception;
}
