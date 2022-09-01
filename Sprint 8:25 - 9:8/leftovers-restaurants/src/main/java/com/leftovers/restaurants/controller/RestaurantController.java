package com.leftovers.restaurants.controller;

import com.leftovers.restaurants.dao.AddressDao;
import com.leftovers.restaurants.dao.RestaurantDao;
import com.leftovers.restaurants.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantDao restaurants;

    @Autowired
    AddressDao addresses;


    // Restaurant Generation
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Restaurant> postRestaurant(@RequestBody Restaurant r) {
        if(!restaurantInputValidation(r))
            return new ResponseEntity<Restaurant>(HttpStatus.NOT_ACCEPTABLE);

        if(!restaurants.addRestaurant(r))
            return new ResponseEntity<Restaurant>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<Restaurant>(r,HttpStatus.CREATED);
    }

    // Restaurant Deletion
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Integer id) {
        var result = restaurants.getRestaurant(id);
        if(result == null) return new ResponseEntity<Restaurant>(HttpStatus.GONE);

        restaurants.deleteRestaurant(id);
        return new ResponseEntity<Restaurant>(HttpStatus.OK);
    }

    // Restaurant Getters
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Restaurant>> getAllRestaurants() {
        var result = restaurants.getAllRestaurants();
        return new ResponseEntity<Iterable<Restaurant>>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Integer id) {
        var result = restaurants.getRestaurant(id);
        if(result == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "", params = "name", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Restaurant>> getRestaurantsByName(@RequestParam(value = "name") String name) {
        var result = restaurants.getRestaurantByNameContaining(name);
        if(result == null || StreamSupport.stream(result.spliterator(), false).findAny().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // Validations
    boolean restaurantInputValidation(Restaurant r) {
        // Check if id exists
        if(r.getId() != null) {
            var result = restaurants.getRestaurant(r.getId());
            if(result != null) return false;
        }

        return r.getName() != null && r.getAddressId() != null && r.getPhoneNo() != null
                && r.getCloseTime() != null && r.getOpenTime() != null;
    }
}
