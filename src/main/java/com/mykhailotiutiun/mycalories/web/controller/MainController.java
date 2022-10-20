package com.mykhailotiutiun.mycalories.web.controller;

import com.mykhailotiutiun.mycalories.persistence.models.Meal;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import com.mykhailotiutiun.mycalories.services.MealService;
import com.mykhailotiutiun.mycalories.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final MealService mealService;

    public MainController(UserService userService, MealService mealService) {
        this.userService = userService;
        this.mealService = mealService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("diet", user.getDiet());
        return "index";
    }

    @PostMapping("/create-meal")
    public String createMeal(@ModelAttribute(name = "meal-name") String mealName, BindingResult result) {

        if(result.hasErrors()){
            return "redirect:/?error";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());

        Meal meal = new Meal(mealName, user.getDiet());
        mealService.saveAndAddToDiet(meal, user.getDiet());

        return "redirect:/";
    }

    @PostMapping("/delete-meal")
    public String deleteMeal(@ModelAttribute(name = "meal-id") Long mealId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());

        mealService.removeAndDelete(mealService.getById(mealId), user.getDiet());
        return "redirect:/";
    }

}
