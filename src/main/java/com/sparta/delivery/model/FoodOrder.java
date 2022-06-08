package com.sparta.delivery.model;

import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.dto.FoodOrderRequestDto;
import com.sparta.delivery.validator.FoodOrderValidator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.catalina.webresources.StandardRoot;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int price;

    @Column(nullable = false)
    int quantity;

    @OneToOne
    @JoinColumn(name = "FOOD_ID", nullable = false)
    Food food;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ORDER_ID", nullable = false)
    private CustomerOrder customerOrder;

    public FoodOrder(Food food, FoodOrderRequestDto foodOrderRequestDto, CustomerOrder customerOrder) throws RegisterException {
        this.customerOrder = customerOrder;
        this.food = food;
        this.name = food.getName();
        this.price = food.getPrice();
        FoodOrderValidator.isValidQuantity(foodOrderRequestDto.getQuantity());
        this.quantity = foodOrderRequestDto.getQuantity();
    }


}

