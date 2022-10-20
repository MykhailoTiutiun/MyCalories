package com.mykhailotiutiun.mycalories.persistence.dto;

import com.mykhailotiutiun.mycalories.persistence.models.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DetailsDto implements Serializable {

    private Long id;
    private User user;

    @NotNull
    @Min(12)
    @Max(100)
    private Integer age;
    @NotNull
    @Min(50)
    @Max(400)
    private Integer height;
    @NotNull
    @Min(20)
    @Max(400)
    private Integer weight;
    @NotNull
    @Min(1)
    @Max(80)
    private Integer fat;

    public DetailsDto(Long id, User user, Integer age, Integer height, Integer weight, Integer fat) {
        this.id = id;
        this.user = user;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.fat = fat;
    }
}
