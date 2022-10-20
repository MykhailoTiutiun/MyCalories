package com.mykhailotiutiun.mycalories.web.controller;

import com.mykhailotiutiun.mycalories.persistence.dto.DtoConverter;
import com.mykhailotiutiun.mycalories.persistence.dto.UserDto;
import com.mykhailotiutiun.mycalories.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AuthorzationController {

    private final UserService userService;

    public AuthorzationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-in")
    public String signInPage(){
        return "authorization/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model){
        model.addAttribute("userDto", new UserDto());
        return "authorization/sign-up";
    }

    @PostMapping("/sign-up")
    public String createUser(@ModelAttribute @Valid UserDto userDto, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/sign-up?invalidDataError";
        } else if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            return "redirect:/sign-up?passwordMatchError";
        }
        userService.createUser(DtoConverter.userFromUserDtoOnCreate(userDto));
        return "redirect:/sign-in";
    }


}
