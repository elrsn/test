package com.leftovers.restaurants.model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Food {

    @Id
    @Column(name = "id" , nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;


    public Food() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() { return restaurant; }

    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
}