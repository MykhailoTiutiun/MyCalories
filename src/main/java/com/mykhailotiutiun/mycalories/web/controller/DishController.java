package com.mykhailotiutiun.mycalories.web.controller;

import com.mykhailotiutiun.mycalories.persistence.dto.DishDto;
import com.mykhailotiutiun.mycalories.persistence.dto.DtoConverter;
import com.mykhailotiutiun.mycalories.persistence.models.Dish;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import com.mykhailotiutiun.mycalories.services.DishService;
import com.mykhailotiutiun.mycalories.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final UserService userService;
    private final DishService dishService;

    public DishController(UserService userService, DishService dishService) {
        this.userService = userService;
        this.dishService = dishService;
    }

    @GetMapping("/{page}")
    public String mainPage(@PathVariable(value = "page", required = false) Integer pageNumber, @RequestParam(name = "search-val", required = false) String searchName, Model model) {
        if (pageNumber == null) {
            pageNumber = 0;
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("page", pageNumber);

        Page<Dish> page =  dishService.getPage(searchName, PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.DESC, "recentViews")));

        model.addAttribute("dishes", dishService.getFormatedPage(page.getContent()));
        model.addAttribute("totalPages", page.getTotalPages());

        model.addAttribute("favoriteDishes", dishService.getFavoriteDishDtos(user, PageRequest.of(0,  Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "recentViews"))).get().toList());

        return "dishes/dishes";
    }

    @PostMapping("/favorite-actions")
    public String favoriteActions(@RequestParam(name = "action") String action, @RequestParam(name = "dish-id") Long dishId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());

        switch (action){
            case ("add") -> dishService.addToFavorite(dishId, user);
            case ("remove") -> dishService.removeFromFavorite(dishId, user);
            default -> throw new IllegalArgumentException(action);
        }

        return "redirect:/dishes/0";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());
        model.addAttribute("user", user);

        model.addAttribute("newDish", new DishDto());

        return "dishes/create-dish";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid DishDto dishDto, @RequestParam(name = "photo") MultipartFile image, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.getUserById(user.getId());

        dishService.create(user, DtoConverter.dishFromDishDto(dishDto), image);

        return "redirect:/dishes/0";
    }
}
