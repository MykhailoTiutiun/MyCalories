package com.mykhailotiutiun.mycalories.persistence.dto;

import com.mykhailotiutiun.mycalories.persistence.models.Details;
import com.mykhailotiutiun.mycalories.persistence.models.Diet;
import com.mykhailotiutiun.mycalories.persistence.models.Dish;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import org.springframework.util.Base64Utils;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class DtoConverter {

    // Details
    public static Details detailsFromDetailsDto(DetailsDto detailsDto){
        return new Details(detailsDto.getId(), detailsDto.getUser(), detailsDto.getAge(), detailsDto.getHeight(), detailsDto.getWeight(), detailsDto.getFat());
    }
    public static DetailsDto detailsDtoFromDetails(Details details){
        return new DetailsDto(details.getId(), details.getUser(), details.getAge(), details.getHeight(), details.getWeight(), details.getFat());
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // User
    public static User userFromUserDtoOnCreate(UserDto userDto){
        return new User(userDto.getName(), userDto.getEmail(), userDto.getPassword());
    }
    public static User userFromUserDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getRoles(), detailsFromDetailsDto(userDto.getDetails()), dietFromDietDto(userDto.getDiet()), dishesFromDishDtos(userDto.getFavoriteDishes()), dishesFromDishDtos(userDto.getOwnedDishes()));
    }
    public static UserDto userDtoFromUser(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), null, user.getRoles(), detailsDtoFromDetails(user.getDetails()), dietDtoFromDiet(user.getDiet()), dishDtosFromDishes(user.getFavoriteDishes()), dishDtosFromDishes(user.getOwnedDishes()));
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Diet
    public static Diet dietFromDietDto(DietDto dietDto){
        return new Diet(dietDto.getId(), dietDto.getUser(), dietDto.getDailyCalories(), dietDto.getTodayCalories(), dietDto.getDailyProteins(), dietDto.getTodayProteins(), dietDto.getDailyCarbs(), dietDto.getTodayCarbs(), dietDto.getDailyFats(), dietDto.getTodayFats());
    }
    public static DietDto dietDtoFromDiet(Diet diet){
        return new DietDto(diet.getId(), diet.getUser(), diet.getDailyCalories(), diet.getTodayCalories(), diet.getDailyProteins(), diet.getTodayProteins(), diet.getDailyCarbs(), diet.getTodayCarbs(), diet.getDailyFats(), diet.getTodayFats());
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // Dishes
    public static Dish dishFromDishDto(DishDto dishDto){
        return new Dish(dishDto.getId(), dishDto.getName(), dishDto.getDiscription(), dishDto.getCalories(), dishDto.getProteins(), dishDto.getCarbs(), dishDto.getFats(), dishDto.getViews(), dishDto.getRecentViews(), dishDto.getLastViewsUpdateDate(), dishDto.getImage(), dishDto.getFavoriteUsers());
    }
    public static DishDto dishDtoFromDish(Dish dish){
        return new DishDto(dish.getId(), dish.getName(), dish.getDiscription(), dish.getImage(), Base64Utils.encodeToString(dish.getImage()), dish.getCalories(), dish.getProteins(), dish.getCarbs(), dish.getFats(), dish.getViews(), dish.getRecentViews(), dish.getLastViewsUpdateDate(), dish.getFavoriteUsers());
    }
    public static Set<Dish> dishesFromDishDtos(Set<DishDto> dishDtos){
        Set<Dish> dishes = new HashSet<>();
        dishDtos.forEach(dishDto -> dishes.add(dishFromDishDto(dishDto)));
        return dishes;
    }
    public static Set<DishDto> dishDtosFromDishes(Set<Dish> dishes){
        Set<DishDto> dishDtos = new HashSet<>();
        dishes.forEach(dishe -> dishDtos.add(dishDtoFromDish(dishe)));
        return dishDtos;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
}

