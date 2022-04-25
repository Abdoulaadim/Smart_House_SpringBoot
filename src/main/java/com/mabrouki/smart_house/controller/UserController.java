package com.mabrouki.smart_house.controller;

import com.mabrouki.smart_house.dto.UserDto;
import com.mabrouki.smart_house.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;



    //------- All users : -------------------------------------------------------------------

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDto = userService.getAllUsers();
        return ResponseEntity.ok(userDto);
    }

    //------- Add user : ------------------------------------------------------------------
    @PostMapping()
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto )  {

        UserDto newuser = userService.addUser(userDto);
        return new ResponseEntity<UserDto>(newuser, HttpStatus.CREATED);
    }

    //------- Delete  : --------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<UserDto> UpdateUser(@PathVariable String id, @RequestBody UserDto userDto) throws Exception {

        UserDto uc = userService.updateUser(id,userDto);
        return new ResponseEntity<UserDto>(uc, HttpStatus.CREATED);
    }



}
