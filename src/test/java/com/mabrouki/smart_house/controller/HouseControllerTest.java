package com.mabrouki.smart_house.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mabrouki.smart_house.dto.HouseDto;
import com.mabrouki.smart_house.entities.HouseEntity;
import com.mabrouki.smart_house.repositories.HouseRepository;
import com.mabrouki.smart_house.services.HouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class HouseControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper =new ObjectMapper();
    ObjectWriter objectWriter =objectMapper.writer();

    @Mock
    private HouseRepository houseRepository;
    @Mock
    private HouseService houseService;
    @InjectMocks
    private HouseController houseController;

    HouseDto h1 = new HouseDto("1","house1","address1","floor1","room1","device1","ON");
    HouseDto h2 = new HouseDto("2","house2","address2","floor2","room2","device2","OFF");
    HouseDto h3 = new HouseDto("3","house3","address3","floor3","room3","device3","ON");
    HouseDto h4 = new HouseDto("4","house4","address4","floor4","room4","device4","OFF");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(houseController).build();
        List<HouseDto> clients= new ArrayList<>(Arrays.asList(h1,h2,h3,h4));
    }

    @Test
    void getAllHouses() throws Exception {
        List<HouseDto> allHouse= new ArrayList<>(Arrays.asList(h1,h2,h3,h4));
        Mockito.when(houseService.getAllHouse()).thenReturn(allHouse);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/house/")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name",is("house3")))
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    void getHouseById() {

    }

    @Test
    void createHouse() throws Exception {
        HouseDto h5 =new HouseDto("5","house5","address5","floor5","room5","device5","ON");

        Mockito.when(houseService.addHouse(h5)).thenReturn(h5);
        String content =objectWriter.writeValueAsString(h5);
        MockHttpServletRequestBuilder mockReq =MockMvcRequestBuilders.post("/house")

                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockReq)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",is("house5")))
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    @Test
    void updateHouse() throws Exception {
        HouseDto updateh5 =new HouseDto("5","house6","address5","floor5","room5","device5","ON");
        Mockito.when(houseService.updateHouse("5",updateh5)).thenReturn(updateh5);
        String content =objectWriter.writeValueAsString(updateh5);
        MockHttpServletRequestBuilder mockReq =MockMvcRequestBuilders.put("/house/5")

                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockReq)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",is("house6")))
                .andDo(MockMvcResultHandlers.print()).andReturn()
        ;
    }

    @Test
    void deleteHouse() throws Exception {

        Mockito.when(houseService.deleteHouse(h4.getId())).thenReturn(Boolean.TRUE);
        MockHttpServletRequestBuilder mockReq =MockMvcRequestBuilders.delete("/house/4")

                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockReq)
                .andExpect(status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("$",is(Boolean.TRUE)))
                .andDo(MockMvcResultHandlers.print()).andReturn();


    }
}
