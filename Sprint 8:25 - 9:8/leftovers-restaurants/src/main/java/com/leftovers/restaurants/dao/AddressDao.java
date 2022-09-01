package com.leftovers.restaurants.dao;

import com.leftovers.restaurants.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressDao {
    @Autowired
    AddressRepository repo;

    public boolean addAddress(Address a) {
        var result = repo.findAddressById(a.getId());
        if(result != null) return false;

        try {
            repo.save(a);
        }
        catch(Exception e) {
            throw e;
        }

        return true;
    }

    public Address getAddress(Integer id) {
        return repo.findAddressById(id);
    }
}
