package com.sparta.delivery.Service;

import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import com.sparta.delivery.validator.FoodValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    public void postFood(Long restaurantId, List<FoodDto> foodDtoList) throws RegisterException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("가게 정보가 없습니다.")
        );
        for (FoodDto foodDto : foodDtoList) {
            Food checkDubFood = foodRepository.findByRestaurantIdAndName(restaurantId, foodDto.getName());
            FoodValidator.isValidFoodName(checkDubFood);
            Food food = new Food(restaurant, foodDto);
            foodRepository.save(food);
        }
    }
}
