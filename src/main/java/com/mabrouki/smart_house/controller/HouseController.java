package com.mabrouki.smart_house.controller;

import com.mabrouki.smart_house.dto.HouseDto;
import com.mabrouki.smart_house.dto.UserDto;
import com.mabrouki.smart_house.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    HouseService houseService;

    //------- All users : -------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<HouseDto>> getAllUsers(){
        List<HouseDto> houseDto = houseService.getAllHouse();
        return ResponseEntity.ok(houseDto);
    }

    //------- Add user : ------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<HouseDto> addUser(@RequestBody HouseDto houseDto )  {

        HouseDto newhouse = houseService.addHouse(houseDto);
        return new ResponseEntity<HouseDto>(newhouse, HttpStatus.CREATED);
    }



    //------- Update user by id : ---------------------------------------------------------
    @PutMapping ("/{id}")
    public ResponseEntity<HouseDto> UpdateUser(@PathVariable String id, @RequestBody HouseDto houseDto) throws Exception {

        HouseDto uc = houseService.updateHouse(id,houseDto);
        return new ResponseEntity<HouseDto>(uc, HttpStatus.CREATED);
    }

    //------- Delete user by id : ---------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){

       boolean isdeleted= houseService.deleteHouse(id);
        return new ResponseEntity<Boolean>(isdeleted,HttpStatus.NO_CONTENT);
    }
}
