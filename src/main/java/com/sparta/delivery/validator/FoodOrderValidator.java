package com.sparta.delivery.validator;

import com.sparta.delivery.common.Constants;
import com.sparta.delivery.common.exception.RegisterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodOrderValidator {
    public static void isValidQuantity(int quantity) throws RegisterException{
        if(quantity <= 0){
            throw new RegisterException(Constants.ExceptionClass.ORDER_QUANTITY, HttpStatus.BAD_REQUEST, "음식 주문 수량은 1이상의 값으로 입력해주세요.");
        }

        if(quantity > 100){
            throw new RegisterException(Constants.ExceptionClass.ORDER_QUANTITY, HttpStatus.BAD_REQUEST, "음식 주문 수량은 100이하의 값으로 입력해주세요.");
        }
    }

    public static void isValidMinPrice(int price, int restauratMinPrice) throws RegisterException {
        if (price < restauratMinPrice) {
            throw new RegisterException(Constants.ExceptionClass.ORDER_LOWERMINPRICE, HttpStatus.BAD_REQUEST, "음식 주문 수량은 1이상의 값으로 입력해주세요.");
        }
    }

}
