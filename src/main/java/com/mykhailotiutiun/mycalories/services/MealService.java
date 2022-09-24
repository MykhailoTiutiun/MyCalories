package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.entities.Diet;
import com.mykhailotiutiun.mycalories.persistence.entities.Meal;
import com.mykhailotiutiun.mycalories.persistence.repositories.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final DietService dietService;

    public MealService(MealRepository mealRepository, DietService dietService) {
        this.mealRepository = mealRepository;
        this.dietService = dietService;
    }

    public Optional<Meal> getById(Long id){
        return mealRepository.findById(id);
    }

    public void save(Meal meal){
        mealRepository.save(meal);
    }

    public void saveAndAddToDiet(Meal meal, Diet diet){
        save(meal);
        Set<Meal> meals = diet.getMeals();
        meals.add(meal);
        diet.setMeals(meals);
        dietService.save(diet);
    }


    public void delete(Long id){
        mealRepository.deleteById(id);
    }

    public void removeAndDelete(Meal meal, Diet diet){
        Set<Meal> meals = diet.getMeals();
        meals.remove(meal);
        diet.setMeals(meals);
        dietService.save(diet);
        mealRepository.deleteById(meal.getId());
    }
}
