package com.sparta.delivery.model;

import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.validator.FoodValidator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FOOD_ID")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    private @Column(nullable = false)
    int price;

    public Food(Restaurant restaurant, FoodDto foodDto) throws RegisterException{
        FoodValidator.isValidFoodPrice(foodDto.getPrice());
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.restaurant = restaurant;
    }
}