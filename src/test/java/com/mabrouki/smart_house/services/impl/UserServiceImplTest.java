package com.mabrouki.smart_house.services.impl;

import com.mabrouki.smart_house.dto.UserDto;
import com.mabrouki.smart_house.dto.services.IMapClassWithDto;
import com.mabrouki.smart_house.entities.UserEntity;
import com.mabrouki.smart_house.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    IMapClassWithDto<UserEntity, UserDto> userMApping;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    UserDto userDto=new UserDto("1","abdoulaadim","abdoulaadim","Abdoulaadim@gmail.com","abdoulaadim");;
    UserEntity userEntity=new UserEntity("2","mabrouki","mabrouki","mabrouki@gmail.com","mabrouki");;

    List<UserDto> listuserDto= new ArrayList<>(Arrays.asList(userDto));
    List<UserEntity> listuserEntity= new ArrayList<>(Arrays.asList(userEntity));

    @Test
    void getALL() {
        Mockito.when(userRepository.findAll()).thenReturn(listuserEntity);
        Mockito.when(userMApping.convertListToListDto(listuserEntity,UserDto.class)).thenReturn(listuserDto);

        List<UserDto> userx=userServiceImpl.getAllUsers();
        assertEquals(userx,listuserDto);
    }


    @Test
    void create() {
        Mockito.when(bCryptPasswordEncoder.encode(userDto.getPassword())).thenReturn("passwordIsEncoded");
        Mockito.when(userMApping.convertToEntity(userDto,UserEntity.class)).thenReturn(userEntity);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userMApping.convertToDto(userEntity,UserDto.class)).thenReturn(userDto);

        UserDto userx=userServiceImpl.addUser(userDto);
        assertEquals(userx,userDto);
    }

    @Test
    void delete() {
        Mockito.when(userRepository.findUserEntitiesById("1")).thenReturn(userEntity);
        boolean cltFinded=userServiceImpl.delete("1");
        assertEquals(true,cltFinded);
    }

    @Test
    void update() throws Exception {
        Mockito.when(userRepository.findUserEntitiesById("1")).thenReturn(userEntity);
        Mockito.when(userMApping.convertToEntity(userDto,UserEntity.class)).thenReturn(userEntity);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userMApping.convertToDto(userEntity,UserDto.class)).thenReturn(userDto);

        UserDto userx=userServiceImpl.updateUser("1", userDto);
        assertEquals(userx,userDto);
    }

}