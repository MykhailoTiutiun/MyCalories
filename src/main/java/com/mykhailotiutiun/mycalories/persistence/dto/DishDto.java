package com.mykhailotiutiun.mycalories.persistence.dto;

import com.mykhailotiutiun.mycalories.persistence.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.mykhailotiutiun.mycalories.persistence.models.Dish} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto implements Serializable {

    private Long id;
    @NotEmpty
    @Size(max = 30)
    private String name;
    @NotEmpty
    private String discription;
    private byte[] image;
    private String imageBase64;
    @NotNull
    @Min(1)
    @Max(1000)
    private Float calories;
    @NotNull
    @Min(0)
    @Max(100)
    private Float proteins;
    @NotNull
    @Min(0)
    @Max(100)
    private Float carbs;
    @NotNull
    @Min(0)
    @Max(100)
    private Float fats;
    private Integer views;
    private Integer recentViews;
    private LocalDate lastViewsUpdateDate;
    private Set<User> favoriteUsers;

    public DishDto(String name, String discription, Float calories, Float proteins, Float carbs, Float fats) {
        this.name = name;
        this.discription = discription;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }
}