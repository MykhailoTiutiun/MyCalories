package com.mykhailotiutiun.mycalories.persistence.dto;

import com.mykhailotiutiun.mycalories.persistence.entities.Details;
import com.mykhailotiutiun.mycalories.persistence.entities.Diet;
import com.mykhailotiutiun.mycalories.persistence.entities.User;

public class DtoConverter {


    public static Details detailsFromDetailsDto(DetailsDto detailsDto){
        return new Details(detailsDto.getId(), detailsDto.getUser(), detailsDto.getAge(), detailsDto.getHeight(), detailsDto.getWeight(), detailsDto.getFat());
    }

    public static DetailsDto detailsDtoFromDetails(Details details){
        return new DetailsDto(details.getId(), details.getUser(), details.getAge(), details.getHeight(), details.getWeight(), details.getFat());
    }


    public static User userFromUserDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getRoles(), detailsFromDetailsDto(userDto.getDetails()), dietFromDietDto(userDto.getDiet()));
    }

    public static UserDto userDtoFromUser(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles(), detailsDtoFromDetails(user.getDetails()), dietDtoFromDiet(user.getDiet()));
    }


    public static Diet dietFromDietDto(DietDto dietDto){
        return new Diet(dietDto.getId(), dietDto.getUser(), dietDto.getDailyCalories(), dietDto.getTodayCalories(), dietDto.getDailyProteins(), dietDto.getTodayProteins(), dietDto.getDailyCarbs(), dietDto.getTodayCarbs(), dietDto.getDailyFats(), dietDto.getTodayFats());
    }

    public static DietDto dietDtoFromDiet(Diet diet){
        return new DietDto(diet.getId(), diet.getUser(), diet.getDailyCalories(), diet.getTodayCalories(), diet.getDailyProteins(), diet.getTodayProteins(), diet.getDailyCarbs(), diet.getTodayCarbs(), diet.getDailyFats(), diet.getTodayFats());
    }

}

