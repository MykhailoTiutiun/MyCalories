package com.mykhailotiutiun.mycalories.persistence.dto;

import com.mykhailotiutiun.mycalories.persistence.entities.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
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

    public UserDto() {
    }

    public UserDto(Long id, String name, String email, String password, Set<Role> roles, DetailsDto details, DietDto diet) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.details = details;
        this.diet = diet;
    }
}
