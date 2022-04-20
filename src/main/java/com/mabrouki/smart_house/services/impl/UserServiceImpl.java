package com.mabrouki.smart_house.services.impl;

import com.mabrouki.smart_house.dto.UserDto;
import com.mabrouki.smart_house.dto.services.IMapClassWithDto;
import com.mabrouki.smart_house.entities.UserEntity;
import com.mabrouki.smart_house.repositories.UserRepository;
import com.mabrouki.smart_house.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    IMapClassWithDto<UserEntity, UserDto> userMapping;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userMapping.convertListToListDto(users,UserDto.class);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        String pass = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(pass);
        UserEntity userEntity = userMapping.convertToEntity(userDto, UserEntity.class);

        userEntity = userRepository.save(userEntity);

        return userMapping.convertToDto(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity currentUser = userRepository.findByEmail(email);
        return userMapping.convertToDto(currentUser, UserDto.class);
    }

    @Override
    public boolean delete(String id) {

        UserEntity user = userRepository.findUserEntitiesById(id);
        if(user == null) throw new RuntimeException("user not found");
        userRepository.deleteById(id);

        return true;
    }

    //--------- Update User : -------------------------------------------------------------------
    @Override
    public UserDto updateUser(String id,UserDto userDto) throws Exception {
             UserEntity userEntityfinded = userRepository.findUserEntitiesById(id);

        if (Objects.isNull(userEntityfinded)) throw new Exception("user not found");

        UserEntity userEntity = userMapping.convertToEntity(userDto, UserEntity.class);
        userEntity.setId(id);
        userEntity = userRepository.save(userEntity);


             return userMapping.convertToDto(userEntity, UserDto.class);


    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
}
