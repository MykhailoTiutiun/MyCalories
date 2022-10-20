package com.mykhailotiutiun.mycalories.services.impl;

import com.mykhailotiutiun.mycalories.persistence.models.Diet;
import com.mykhailotiutiun.mycalories.persistence.models.Meal;
import com.mykhailotiutiun.mycalories.persistence.repositories.MealRepository;
import com.mykhailotiutiun.mycalories.services.DietService;
import com.mykhailotiutiun.mycalories.services.MealService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final DietService dietService;

    public MealServiceImpl(MealRepository mealRepository, DietService dietService) {
        this.mealRepository = mealRepository;
        this.dietService = dietService;
    }

    @Override
    public Meal getById(Long id){
        return mealRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void save(Meal meal){
        mealRepository.save(meal);
    }

    @Override
    public void saveAndAddToDiet(Meal meal, Diet diet){
        save(meal);
        Set<Meal> meals = diet.getMeals();
        meals.add(meal);
        diet.setMeals(meals);
        dietService.save(diet);
    }


    @Override
    public void delete(Long id){
        mealRepository.deleteById(id);
    }

    @Override
    public void removeAndDelete(Meal meal, Diet diet){
        Set<Meal> meals = diet.getMeals();
        meals.remove(meal);
        diet.setMeals(meals);
        dietService.save(diet);
        mealRepository.deleteById(meal.getId());

        dietService.calculateParams(diet.getId());
    }
}
