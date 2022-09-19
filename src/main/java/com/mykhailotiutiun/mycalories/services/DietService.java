package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.entities.Diet;
import com.mykhailotiutiun.mycalories.persistence.entities.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.DietRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DietService {

    DietRepository dietRepository;

    public DietService(DietRepository dietRepository){
        this.dietRepository = dietRepository;
    }

    public Optional<Diet> getDietById(Long id){
        return dietRepository.findById(id);
    }

    public void save(User user){
        dietRepository.save(new Diet(user.getId(), user));
    }

    public void setDailyParams(Long id, Float dailyCalories, Float dailyProteins, Float dailyCarbs, Float dailyFats){
        Diet diet = dietRepository.findById(id).get();
        diet.setDailyParams(dailyCalories, dailyProteins, dailyCarbs, dailyFats);
        dietRepository.save(diet);
    }
}
