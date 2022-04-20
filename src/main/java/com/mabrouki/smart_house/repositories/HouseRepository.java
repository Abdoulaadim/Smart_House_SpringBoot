package com.mabrouki.smart_house.repositories;

import com.mabrouki.smart_house.entities.HouseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends MongoRepository<HouseEntity,String> {
    HouseEntity findUserEntitiesById(String id);

}