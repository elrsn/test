package com.leftovers.restaurants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leftovers.restaurants.dao.RestaurantDao;
import com.leftovers.restaurants.model.Restaurant;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Time;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class LeftoversRestaurantsApplicationTests {
    String endpoint = "/restaurants";
    Restaurant r1 = new Restaurant("Billy", 1, "(503) 111-1111", new Time (100000), new Time (180000));
    Restaurant r2 = new Restaurant("Bob", 2, "(231) 111-1111", new Time (110000), new Time (200000));
    Restaurant r3 = new Restaurant("Boil", 2, "(111) 111-1111", new Time (120000), new Time (140000));

    @Autowired
    RestaurantDao dao;

    @Autowired
    private MockMvc mock;
     /*
    @Test
    @Order(1)
    @DisplayName("Restaurant Post Test")
    void restaurantPostTest() throws Exception {
        mock.perform(MockMvcRequestBuilders
                .post(endpoint)
                .content(asJsonString(r1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(r1.getName()));

        mock.perform(MockMvcRequestBuilders
                .post(endpoint)
                .content(asJsonString(r2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(r2.getName()));

        mock.perform(MockMvcRequestBuilders
                .post(endpoint)
                .content(asJsonString(r3))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(r3.getName()));

        var result = dao.getLatestRestaurant();
        r1.setId(result.getId());
        mock.perform(MockMvcRequestBuilders
                .post(endpoint)
                .content(asJsonString(r1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }


    @Test
    @Order(2)
    @DisplayName("Restaurant Delete Test")
    void RestaurantDeleteTest() throws Exception {
        var result = dao.getLatestRestaurant();

        mock.perform(MockMvcRequestBuilders
                .delete(endpoint + "/{id}", result.getId().toString()))
                .andExpect(status().isOk());

        mock.perform(MockMvcRequestBuilders
                .delete(endpoint + "/{id}", result.getId().toString()))
                .andExpect(status().isGone());
    }

    @Test
    @Order(3)
    @DisplayName("Restaurant Get All Test")
    void RestaurantGetAllTest() throws Exception {
        mock.perform(MockMvcRequestBuilders
                .get(endpoint)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("Restaurant Get by ID Test")
    void RestaurantGetByIdTest() throws Exception {
        var result = dao.getLatestRestaurant();
        mock.perform(MockMvcRequestBuilders
                .get(endpoint + "/" + result.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mock.perform(MockMvcRequestBuilders
                .get(endpoint + "/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    @DisplayName("Restaurant Get by Name Test")
    void RestaurantGetByNameTest() throws Exception {
        mock.perform(MockMvcRequestBuilders
                .get(endpoint)
                .param("name", "j")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").value("Jerry"))
                .andExpect(status().isOk());

        mock.perform(MockMvcRequestBuilders
                .get(endpoint)
                .param("name", "z")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    */



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}