package com.leftovers.restaurants.dao;

import com.leftovers.restaurants.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantDao {
    @Autowired
    RestaurantRepository repo;

    public boolean addRestaurant(Restaurant r) {
        try {
            repo.save(r);
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public void deleteRestaurant(Integer id) {
        repo.deleteById(id);
    }
    public Restaurant getRestaurant(Integer id) {
        return repo.findRestaurantById(id);
    }
    public Restaurant getLatestRestaurant() {
        return repo.findTopByOrderByIdDesc();
    }
    public Iterable<Restaurant> getAllRestaurants() {
        return repo.findAll();
    }
    public Iterable<Restaurant> getRestaurantByNameContaining(String str) {
        return repo.findRestaurantByNameContainingIgnoreCase(str);
    }
}
