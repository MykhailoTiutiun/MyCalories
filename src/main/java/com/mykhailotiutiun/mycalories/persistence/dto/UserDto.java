package com.mykhailotiutiun.mycalories.persistence.dto;

import com.mykhailotiutiun.mycalories.persistence.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    @NotEmpty
    private String name;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;

    private DetailsDto details;

    private DietDto diet;
    private Set<DishDto> favoriteDishes;
    private Set<DishDto> ownedDishes;


}
