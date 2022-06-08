package com.sparta.delivery.dto;

import com.sparta.delivery.model.CustomerOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
public class OrderDto {

    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderDto(CustomerOrder customerOrder ,List<FoodOrderDto> foods) {
        this.restaurantName = customerOrder.getRestaurantName();
        this.foods = foods;
        this.deliveryFee = customerOrder.getDeliveryFee();
        this.totalPrice = customerOrder.getTotalPrice();
    }
}
