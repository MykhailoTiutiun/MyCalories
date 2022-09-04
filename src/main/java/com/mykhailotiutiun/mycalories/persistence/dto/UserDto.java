package com.mykhailotiutiun.mycalories.persistence.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
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
    @Size(min = 8)
    private String passwordConfirm;
    private Set<RoleDto> roles;

    public UserDto() {
    }

}
