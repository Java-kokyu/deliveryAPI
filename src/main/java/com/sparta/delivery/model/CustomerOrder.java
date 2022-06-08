package com.sparta.delivery.model;

import com.sparta.delivery.dto.FoodOrderDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class CustomerOrder {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    @Column(nullable = false)
    private String restaurantName;

    @OneToMany(mappedBy = "customerOrder")
    List<FoodOrder> foodList; //food와 연관관계

//    @Column(nullable = false)
//    private List<FoodOrderDto> foodOrderDtos;

    @Column(nullable = false)
    int totalPrice;

    @Column(nullable = false)
    int deliveryFee;

    public CustomerOrder(Restaurant restaurant, int totalPrice) {
        this.restaurantName = restaurant.getName();
        this.deliveryFee = restaurant.getDeliveryFee();
        this.totalPrice = totalPrice;
    }

}
