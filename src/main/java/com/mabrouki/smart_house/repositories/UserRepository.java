package com.mabrouki.smart_house.repositories;

import com.mabrouki.smart_house.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity,String> {
    UserEntity findByEmail(String email);
    UserEntity findUserEntitiesById(String id);
    UserEntity deleteUserEntitiesById(String id);

}
