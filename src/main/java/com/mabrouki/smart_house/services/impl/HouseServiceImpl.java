package com.mabrouki.smart_house.services.impl;

import com.mabrouki.smart_house.dto.HouseDto;
import com.mabrouki.smart_house.dto.UserDto;
import com.mabrouki.smart_house.dto.services.IMapClassWithDto;
import com.mabrouki.smart_house.entities.HouseEntity;
import com.mabrouki.smart_house.entities.UserEntity;
import com.mabrouki.smart_house.repositories.HouseRepository;
import com.mabrouki.smart_house.services.HouseService;
import com.mabrouki.smart_house.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    HouseRepository houseRepository;
    @Autowired
    IMapClassWithDto<HouseEntity, HouseDto> housemapping;




    @Override
    public List<HouseDto> getAllHouse() {
        List<HouseEntity> house = houseRepository.findAll();
        return housemapping.convertListToListDto(house,HouseDto.class);
    }


    @Override
    public HouseDto addHouse(HouseDto houseDto) {
        HouseEntity houseEntity = housemapping.convertToEntity(houseDto, HouseEntity.class);
        houseEntity = houseRepository.save(houseEntity);
        return housemapping.convertToDto(houseEntity, HouseDto.class);
    }

    @Override
    public boolean deleteHouse(String id) {

        Optional<HouseEntity> house = houseRepository.findById(id);
        if(house == null) return false;
        houseRepository.deleteById(id);
return true;
    }

    @Override
    public HouseDto updateHouse(String id, HouseDto userDto) throws Exception {

        HouseEntity houseEntity = houseRepository.findUserEntitiesById(id);

        if (Objects.isNull(houseEntity)) {
            /* handle this exception using {@link RestExceptionHandler} */
            throw new Exception("error to update ");
        }

        userDto.setId(id);
        return this.addHouse(userDto);
    }
}
