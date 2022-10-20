package com.mykhailotiutiun.mycalories.services.impl;

import com.mykhailotiutiun.mycalories.persistence.models.Diet;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.DietRepository;
import com.mykhailotiutiun.mycalories.services.DietService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;

    public DietServiceImpl(DietRepository dietRepository){
        this.dietRepository = dietRepository;
    }

    @Override
    public Diet getDietById(Long id){
        return dietRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void save(Diet diet){
        dietRepository.save(diet);
    }

    @Override
    public void create(User user){
        save(new Diet(user.getId(), user));
    }

    @Override
    public void setDailyParams(Long id, Float dailyCalories, Float dailyProteins, Float dailyCarbs, Float dailyFats){
        Diet diet = dietRepository.findById(id).get();
        diet.setDailyParams(dailyCalories, dailyProteins, dailyCarbs, dailyFats);
        dietRepository.save(diet);
    }

    @Override
    public void setTodayParams(Long id, Float todayCalories, Float todayProteins, Float todayCarbs, Float todayFats){
        Diet diet = dietRepository.findById(id).get();
        diet.setTodayParams(todayCalories, todayProteins, todayCarbs, todayFats);
        dietRepository.save(diet);
    }

    @Override
    public void calculateParams(Long id){
        Diet diet = dietRepository.findById(id).get();

        AtomicReference<Float> calories = new AtomicReference<>(0F);
        AtomicReference<Float> proteins = new AtomicReference<>(0F);
        AtomicReference<Float> carbs = new AtomicReference<>(0F);
        AtomicReference<Float> fats = new AtomicReference<>(0F);

        diet.getMeals().forEach(meal -> {
            calories.updateAndGet(v -> v + meal.getCalories());
            proteins.updateAndGet(v -> v + meal.getProteins());
            carbs.updateAndGet(v -> v + meal.getCarbs());
            fats.updateAndGet(v -> v + meal.getFats());
        });

        diet.setTodayParams(calories.get(), proteins.get(), carbs.get(), fats.get());

        dietRepository.save(diet);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Kiev")
    private void resetParamsEveryDay(){
        dietRepository.findAll().forEach(diet -> setTodayParams(diet.getId(), 0F,0F,0F,0F));
    }
}
