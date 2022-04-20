package com.mabrouki.smart_house.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "House")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class HouseEntity {
    private static final long serialVersionUID = 1L;


    @Id
    private String id;
    private String name;
    private String address;
    private String name_floor;
    private String name_room;
    private String name_device;
    private String status;


}
