package com.sparta.delivery.model;

import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.dto.RestaurantDto;
import com.sparta.delivery.validator.RestaurantValidator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "RESTAURANT_ID")
    private Long id;

//    @OneToMany(mappedBy = "restaurant")
//    List<Food> foodList = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    public Restaurant(RestaurantDto restaurantDto) throws RegisterException {
        RestaurantValidator.isValidPrice(restaurantDto.getMinOrderPrice(), restaurantDto.getDeliveryFee());
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }


}
