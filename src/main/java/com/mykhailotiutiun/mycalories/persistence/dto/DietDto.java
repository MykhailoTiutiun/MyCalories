package com.mykhailotiutiun.mycalories.persistence.dto;

import com.mykhailotiutiun.mycalories.persistence.entities.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class DietDto implements Serializable {

    private Long id;
    private User user;

    private Float dailyCalories;
    private Float todayCalories;

    private Float dailyProteins;
    private Float todayProteins;

    private Float dailyCarbs;
    private Float todayCarbs;

    private Float dailyFats;
    private Float todayFats;

    public DietDto(Long id, User user, Float dailyCalories, Float todayCalories, Float dailyProteins, Float todayProteins, Float dailyCarbs, Float todayCarbs, Float dailyFats, Float todayFats) {
        this.id = id;
        this.user = user;
        this.dailyCalories = dailyCalories;
        this.todayCalories = todayCalories;
        this.dailyProteins = dailyProteins;
        this.todayProteins = todayProteins;
        this.dailyCarbs = dailyCarbs;
        this.todayCarbs = todayCarbs;
        this.dailyFats = dailyFats;
        this.todayFats = todayFats;
    }
}
