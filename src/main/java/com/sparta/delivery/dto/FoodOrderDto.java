package com.sparta.delivery.dto;

import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.model.FoodOrder;
import com.sparta.delivery.validator.FoodOrderValidator;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class FoodOrderDto {
    private String name;
    private int price;
    private int quantity;

    public FoodOrderDto(FoodOrder foodOrder) throws RegisterException {
        this.name = foodOrder.getFood().getName();
        this.price = foodOrder.getFood().getPrice() * foodOrder.getQuantity();
        FoodOrderValidator.isValidQuantity(foodOrder.getQuantity());
        this.quantity = foodOrder.getQuantity();
    }
}
