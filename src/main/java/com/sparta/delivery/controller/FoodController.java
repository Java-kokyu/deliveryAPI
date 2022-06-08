package com.sparta.delivery.controller;

import com.sparta.delivery.Service.FoodService;
import com.sparta.delivery.common.exception.RegisterException;
import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;


    @PostMapping("/restaurant/{restaurantId}/foods/register")
    public void postFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDtolist) throws RegisterException {
        foodService.postFood(restaurantId, foodDtolist);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getMenu(@PathVariable Long restaurantId){
        return foodRepository.findByRestaurantId(restaurantId);
    }
}

