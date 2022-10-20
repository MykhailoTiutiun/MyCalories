package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.dto.DishDto;
import com.mykhailotiutiun.mycalories.persistence.models.Dish;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DishService {
    Page<Dish> getAllDishes(Pageable pageable);

    Page<Dish> getDishesByName(String name, Pageable pageable);

    Page<Dish> getPage(String name, Pageable pageable);

    Page<DishDto> getFavoriteDishDtos(User user, Pageable pageable);

    List<List<DishDto>> getFormatedPage(List<Dish> dishes);

    void save(Dish dish);

    void create(User user, Dish dish, MultipartFile image);
    void addToFavorite(Long dishId, User user);
    void removeFromFavorite(Long dishId, User user);
    void delete(Dish dish);
}
