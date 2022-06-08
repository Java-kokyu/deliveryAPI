package com.sparta.delivery.Service;

import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.dto.FoodOrderDto;
import com.sparta.delivery.dto.FoodOrderRequestDto;
import com.sparta.delivery.dto.OrderDto;
import com.sparta.delivery.model.CustomerOrder;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.FoodOrder;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.FoodOrderRepository;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.OrderRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import com.sparta.delivery.validator.FoodOrderValidator;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.matcher.FilterableList;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerOrderService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    private final FoodOrderRepository foodOrderRepository;
    private final OrderRepository orderRepository;
    public OrderDto postOrder(Long restaurantId, List<FoodOrderRequestDto> foodOrderRequestDtoList) throws RegisterException {
        int totalFoodPrice = 0;
        List<FoodOrder> foodOrderList = new ArrayList<>();
        List<FoodOrderDto> foodOrderDtos = new ArrayList<>();

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("레스토랑이 존재하지 않습니다.")
        );

        for (FoodOrderRequestDto foodOrderDto : foodOrderRequestDtoList) {
            Food food = foodRepository.findById(foodOrderDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 음식입니다.")
            );
            FoodOrderValidator.isValidQuantity(foodOrderDto.getQuantity());
            totalFoodPrice += food.getPrice() * foodOrderDto.getQuantity();
        }
        FoodOrderValidator.isValidMinPrice(totalFoodPrice, restaurant.getMinOrderPrice());
        CustomerOrder customerOrder = new CustomerOrder(restaurant, totalFoodPrice+restaurant.getDeliveryFee());
        orderRepository.save(customerOrder);

        for (FoodOrderRequestDto foodOrderDto : foodOrderRequestDtoList) {
            Food food = foodRepository.findById(foodOrderDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 음식입니다.")
            );
            FoodOrder foodOrder = new FoodOrder(food, foodOrderDto, customerOrder);
            foodOrderRepository.save(foodOrder);
            foodOrderDtos.add(new FoodOrderDto(foodOrder));
            foodOrderList.add(foodOrder);
        }

        return new OrderDto(customerOrder, foodOrderDtos);

    }
}
