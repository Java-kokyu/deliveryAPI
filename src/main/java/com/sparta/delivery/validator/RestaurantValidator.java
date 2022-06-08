package com.sparta.delivery.validator;

import com.sparta.delivery.common.Constants;
import com.sparta.delivery.common.exception.RegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator {
    public static void isValidPrice(int minOrderPrice, int deliveryFee) throws RegisterException {
        if(minOrderPrice < 1000 || minOrderPrice > 100000){
            throw new RegisterException(Constants.ExceptionClass.MIN_ORDER_PRICE, HttpStatus.BAD_REQUEST, "최소주문 가격은 1,000 ~ 100,000원 사이의 값으로 입력해주세요.");
        }
        if(minOrderPrice % 100 != 0){
            throw new RegisterException(Constants.ExceptionClass.MIN_ORDER_PRICE, HttpStatus.BAD_REQUEST, "최소주문 가격은 100원 단위로 입력해주세요.");
        }
        if(deliveryFee < 0 || deliveryFee > 10000){
            throw new RegisterException(Constants.ExceptionClass.DELIVERY_FEE, HttpStatus.BAD_REQUEST, "기본 배달비는 0 ~ 10,000원 사이의 값으로 입력해주세요.");
        }
        if(deliveryFee % 500 != 0){
            throw new RegisterException(Constants.ExceptionClass.DELIVERY_FEE, HttpStatus.BAD_REQUEST, "기본 배달비는 500원 단위로 입력해주세요.");
        }
    }
}
