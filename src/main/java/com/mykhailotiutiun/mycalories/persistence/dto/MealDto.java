package com.mykhailotiutiun.mycalories.persistence.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class MealDto implements Serializable {
    private Long id;
    private String name;
    private Float calories;
    private Float proteins;
    private Float carbs;
    private Float fats;
}