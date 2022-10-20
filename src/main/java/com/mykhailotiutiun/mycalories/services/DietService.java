package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.models.Diet;
import com.mykhailotiutiun.mycalories.persistence.models.User;

public interface DietService {
    Diet getDietById(Long id);

    void save(Diet diet);

    void create(User user);

    void setDailyParams(Long id, Float dailyCalories, Float dailyProteins, Float dailyCarbs, Float dailyFats);

    void setTodayParams(Long id, Float todayCalories, Float todayProteins, Float todayCarbs, Float todayFats);

    void calculateParams(Long id);
}
