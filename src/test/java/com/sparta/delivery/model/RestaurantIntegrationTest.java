package com.sparta.delivery.model;

import com.sparta.delivery.common.Constants;
import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.dto.RestaurantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantIntegrationTest {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;

    @BeforeEach
    void setup() {
        name = "동대문 엽기 떡볶이";
        minOrderPrice = 14000;
        deliveryFee = 1000;
    }

    @Nested
    @DisplayName("최소주문 가격")
    class CheckMinOrderPrice{
        @Test
        @DisplayName("1000원 미만 에러")
        void fail1(){
            //given
            minOrderPrice = 500;
            RestaurantDto restaurantDto = new RestaurantDto(name, minOrderPrice, deliveryFee);

            //when
            RegisterException exception = assertThrows(RegisterException.class, () -> {
                new Restaurant(restaurantDto);
            });

            //then
            assertEquals(exception.getExceptionClass(), Constants.ExceptionClass.MIN_ORDER_PRICE);
        }

        @Test
        @DisplayName("100,000원 초과 에러")
        void fail2(){
            //given
            minOrderPrice = 110000;
            RestaurantDto restaurantDto = new RestaurantDto(name, minOrderPrice, deliveryFee);

            //when
            RegisterException exception = assertThrows(RegisterException.class, () -> {
               new Restaurant(restaurantDto);
            });

            //then
            assertEquals(exception.getExceptionClass(), Constants.ExceptionClass.MIN_ORDER_PRICE);
        }

        @Test
        @DisplayName("100원 단위로 입력 안 됨 에러")
        void fail3(){
            //given
            minOrderPrice = 2220;
            RestaurantDto restaurantDto = new RestaurantDto(name, minOrderPrice, deliveryFee);

            //when
            RegisterException exception = assertThrows(RegisterException.class, () ->{
                new Restaurant(restaurantDto);
            });

            //then
            assertEquals(exception.getExceptionClass(), Constants.ExceptionClass.MIN_ORDER_PRICE);
        }
    }

    @Nested
    @DisplayName("기본 배달비")
    class CheckDeliveryFee{
        @Test
        @DisplayName("0원 미만 에러")
        void fail1(){
            //given
            int deliveryFee = -10;
            RestaurantDto restaurantDto = new RestaurantDto(name, minOrderPrice, deliveryFee);

            //when
            RegisterException exception = assertThrows(RegisterException.class, () -> {
                new Restaurant(restaurantDto);
            });

            //then
            assertEquals(exception.getExceptionClass(), Constants.ExceptionClass.DELIVERY_FEE);
        }

        @Test
        @DisplayName("10,000원 초과 에러")
        void fail2(){
            //given
            deliveryFee = 11000;
            RestaurantDto restaurantDto = new RestaurantDto(name, minOrderPrice, deliveryFee);

            //when
            RegisterException exception = assertThrows(RegisterException.class, () -> {
                new Restaurant(restaurantDto);
            });

            //then
            assertEquals(exception.getExceptionClass(), Constants.ExceptionClass.DELIVERY_FEE);
        }

        @Test
        @DisplayName("500원 단위로 입력 안 됨 에러")
        void fail3(){
            //given
            deliveryFee = 2200;
            RestaurantDto restaurantDto = new RestaurantDto(name, minOrderPrice, deliveryFee);

            //when
            RegisterException exception = assertThrows(RegisterException.class, () -> {
                new Restaurant(restaurantDto);
            });

            //then
            assertEquals(exception.getExceptionClass(), Constants.ExceptionClass.DELIVERY_FEE);
        }

    }
}