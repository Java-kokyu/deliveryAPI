package com.sparta.delivery.validator;

import com.sparta.delivery.common.Constants;
import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodValidator {

    public static void isValidFoodName(Food food) throws RegisterException{
        if(food != null){
            throw new RegisterException(Constants.ExceptionClass.FOOD_NAME, HttpStatus.BAD_REQUEST, "중복된 음식 이름이 존재합니다.");
        }
    }

    public static void isValidFoodPrice(int price) throws RegisterException{
        if(price < 100 || price > 1000000){
            throw new RegisterException(Constants.ExceptionClass.FOOD_PRICE, HttpStatus.BAD_REQUEST, "음식 가격은 100 ~ 1,000,000 사이의 값으로 입력해주세요.");
        }
        if(price % 100 != 0){
            throw new RegisterException(Constants.ExceptionClass.FOOD_PRICE, HttpStatus.BAD_REQUEST, "음식 가격은 100원 단위로 입력해주세요.");
        }
    }
}
