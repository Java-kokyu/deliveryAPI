package com.sparta.delivery.common;

public class Constants {
    public enum ExceptionClass {

        MIN_ORDER_PRICE("최소 주문 금액"), DELIVERY_FEE("배달비"), FOOD_NAME("음식 이름"), FOOD_PRICE("음식 가격")
        , ORDER_QUANTITY("음식 주문 수량"), ORDER_LOWERMINPRICE("음식점의 최소주문 금액");

        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " 오류 발생. ";
        }

    }
}
