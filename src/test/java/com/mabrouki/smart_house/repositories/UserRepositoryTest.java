package com.mabrouki.smart_house.repositories;

import static org.junit.jupiter.api.Assertions.*;
import com.mabrouki.smart_house.entities.UserEntity;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureDataMongo
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void all() {
        List<UserEntity> user =userRepository.findAll();

        assertEquals(33,user.stream().count());

    }

    @Test
    void save() {
        UserEntity user =new UserEntity(null,"abdoulaadim","abdoulaadim","Abdoulaadim@gmail.com","abdoulaadim");
        /* ClientEntity clt =new ClientEntity();*/
        UserEntity userx =userRepository.save(user);
        assertNotNull(userx.getId());

    }

    @Test
    void update() {
        UserEntity device2 =new UserEntity("1","abdoulaadim","abdoulaadim","Abdoulaadim@gmail.com","abdoulaadim");
        device2.setNom("TVupdated");
        UserEntity saved =userRepository.save(device2);
        assertEquals("TVupdated",saved.getNom());
        assertEquals("1",saved.getId());

    }

    @Test
    void delete() {


        UserEntity deleted =userRepository.deleteUserEntitiesById("30");
        assertNotNull(deleted);

    }




}