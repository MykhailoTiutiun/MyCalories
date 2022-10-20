package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.models.Diet;
import com.mykhailotiutiun.mycalories.persistence.models.Meal;

public interface MealService {
    Meal getById(Long id);

    void save(Meal meal);

    void saveAndAddToDiet(Meal meal, Diet diet);

    void delete(Long id);

    void removeAndDelete(Meal meal, Diet diet);
}
