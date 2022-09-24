package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.entities.Diet;
import com.mykhailotiutiun.mycalories.persistence.entities.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.DietRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DietService {

    private final DietRepository dietRepository;

    public DietService(DietRepository dietRepository){
        this.dietRepository = dietRepository;
    }

    public Optional<Diet> getDietById(Long id){
        return dietRepository.findById(id);
    }

    public void save(Diet diet){
        dietRepository.save(diet);
    }

    public void create(User user){
        save(new Diet(user.getId(), user));
    }

    public void setDailyParams(Long id, Float dailyCalories, Float dailyProteins, Float dailyCarbs, Float dailyFats){
        Diet diet = dietRepository.findById(id).get();
        diet.setDailyParams(dailyCalories, dailyProteins, dailyCarbs, dailyFats);
        dietRepository.save(diet);
    }

    public void setTodayParams(Long id, Float todayCalories, Float todayProteins, Float todayCarbs, Float todayFats){
        Diet diet = dietRepository.findById(id).get();
        diet.setTodayParams(todayCalories, todayProteins, todayCarbs, todayFats);
        dietRepository.save(diet);
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Kiev")
    private void resetParamsEveryDay(){
        dietRepository.findAll().forEach(diet -> setTodayParams(diet.getId(), 0F,0F,0F,0F));
    }
}
