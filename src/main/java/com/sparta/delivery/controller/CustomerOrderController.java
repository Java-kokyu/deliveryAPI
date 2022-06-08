package com.sparta.delivery.controller;

import com.sparta.delivery.Service.CustomerOrderService;
import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.dto.FoodOrderDto;
import com.sparta.delivery.dto.OrderDto;
import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.model.CustomerOrder;
import com.sparta.delivery.model.FoodOrder;
import com.sparta.delivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;
    private final OrderRepository orderRepository;

    @PostMapping("/order/request")
    public OrderDto postOrder(@RequestBody OrderRequestDto orderRequestDto) throws RegisterException {
        return customerOrderService.postOrder(orderRequestDto.getRestaurantId(), orderRequestDto.getFoods());
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders() throws RegisterException {
        List<CustomerOrder> customerOrders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        List<FoodOrderDto> foodOrderDto = new ArrayList<>();

        for(CustomerOrder customerOrder : customerOrders){
            for(FoodOrder foodOrder : customerOrder.getFoodList()){
                foodOrderDto.add(new FoodOrderDto(foodOrder));
            }
            orderDtos.add(new OrderDto(customerOrder, foodOrderDto));
        }
        return orderDtos;
    }

}

