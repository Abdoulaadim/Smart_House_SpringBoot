package com.mabrouki.smart_house.services;

import com.mabrouki.smart_house.dto.HouseDto;


import java.util.List;

public interface HouseService {

    List<HouseDto> getAllHouse();
    HouseDto addHouse(HouseDto house);
    boolean deleteHouse(String id);
    HouseDto updateHouse(String id,HouseDto userDto) throws Exception;
}
