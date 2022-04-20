package com.mabrouki.smart_house.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {

    private String id;
    private String name;
    private String address;
    private String name_floor;
    private String name_room;
    private String name_device;
    private String status;
}
