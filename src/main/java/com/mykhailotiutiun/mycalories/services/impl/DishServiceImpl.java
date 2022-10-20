package com.mykhailotiutiun.mycalories.services.impl;

import com.mykhailotiutiun.mycalories.persistence.dto.DishDto;
import com.mykhailotiutiun.mycalories.persistence.dto.DtoConverter;
import com.mykhailotiutiun.mycalories.persistence.models.Dish;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.DishRepository;
import com.mykhailotiutiun.mycalories.services.DishService;
import com.mykhailotiutiun.mycalories.services.UserService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    @Override
    public Page<Dish> getAllDishes(Pageable pageable){
        return dishRepository.findAll(pageable);
    }
    @Override
    public Page<Dish> getDishesByName(String name, Pageable pageable){
        return dishRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Page<Dish> getPage(String name, Pageable pageable) {
        Page<Dish> dishes;

        if (name == null) {
            dishes = getAllDishes(pageable);
        } else {
            dishes = getDishesByName(name.substring(0, 1).toUpperCase() + name.substring(1), pageable);
        }


        return dishes;
    }

    @Override
    public Page<DishDto> getFavoriteDishDtos(User user, Pageable pageable) {
        Page<Dish> dishes = dishRepository.findAll(pageable);
        List<DishDto> dishDtos = new ArrayList<>();
        dishes.forEach(dish -> dishDtos.add(DtoConverter.dishDtoFromDish(dish)));

        return new PageImpl<>(dishDtos.stream().filter(dishDto -> dishDto.getFavoriteUsers().contains(user)).toList());
    }


    @Override
    public List<List<DishDto>> getFormatedPage(List<Dish> dishes){
        List<List<DishDto>> page = new ArrayList<>();

        for (int i = 0; i < dishes.size(); i += 2) {

            if (dishes.size() - i > 1) {
                page.add(List.of(new DishDto[]{DtoConverter.dishDtoFromDish(dishes.get(i)), DtoConverter.dishDtoFromDish(dishes.get(i + 1))}));
            } else {
                page.add(List.of(new DishDto[]{DtoConverter.dishDtoFromDish(dishes.get(i))}));
            }
        }

        return page;
    }

    @Override
    public void save(Dish dish){
        dishRepository.save(dish);
    }

    @Override
    public void create(User user, Dish dish, MultipartFile image){
        dish.setId(generateId());

        try {
            dish.setImage(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dish.setViews(0);
        dish.setRecentViews(0);
        dish.setLastViewsUpdateDate(LocalDate.now());
        dish.setOwnedUser(user);

        save(dish);
    }

    @Override
    public void addToFavorite(Long dishId, User user) {
        Dish dish = dishRepository.findById(dishId).orElseThrow(EntityNotFoundException::new);

        Set<User> favoriteUsers = dish.getFavoriteUsers();
        favoriteUsers.add(user);
        dish.setFavoriteUsers(favoriteUsers);

        save(dish);
    }

    @Override
    public void removeFromFavorite(Long dishId, User user) {
        Dish dish = dishRepository.findById(dishId).orElseThrow(EntityNotFoundException::new);

        Set<User> favoriteUsers = dish.getFavoriteUsers();
        favoriteUsers.remove(user);
        dish.setFavoriteUsers(favoriteUsers);

        save(dish);
    }

    @Override
    public void delete(Dish dish){
        dishRepository.delete(dish);
    }

    private Long generateId(){
        Long generatedId;
        Random random = new Random();
        do generatedId = random.nextLong(10000000000L, 100000000000L);
        while (dishRepository.existsById(generatedId));
        return generatedId;
    }
}
