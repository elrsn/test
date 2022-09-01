package com.leftovers.restaurants.model;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Collection;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address_id", nullable = false)
    private Integer addressId;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "address_id", nullable = false)
//    private Address address;

    @Column(name = "phone_number", length = 15)
    private String phoneNo;

    @Column(name = "website", nullable = false)
    private String website;

    @Column(name = "open_time")
    private Time openTime;

    @Column(name = "close_time")
    private Time closeTime;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "rating_count")
    private Integer ratingCount;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private Collection<Food> menuItems;

    public Restaurant() {}
    public Restaurant(String name, Integer addressId, String phoneNo,
                      Time openTime, Time closeTime) {
        this.name = name;
        this.addressId = addressId;
        this.phoneNo = phoneNo;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }


    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getAddressId() { return addressId; }

    public void setAddressId(Integer addressId) { this.addressId = addressId; }

//    public Address getAddress() { return address; }
//
//    public void setAddress(Address address) { this.address = address; }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closingTime) {
        this.closeTime = closingTime;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getRatingCount() { return ratingCount; }

    public void setRatingCount(Integer ratingCount) { this.ratingCount = ratingCount; }

    public Collection<Food> getMenuItems() { return menuItems; }

    public void setMenuItems(Collection<Food> menuItems) { this.menuItems = menuItems; }
}