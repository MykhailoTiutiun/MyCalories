package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.entities.Diet;
import com.mykhailotiutiun.mycalories.persistence.entities.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.DietRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;
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
    public Optional<Diet> getDietByUser(User user){
        return dietRepository.findByUser(user);
    }

    public void save(User user){
        dietRepository.save(new Diet(user.getId(), user));
    }

    @Transactional
    public void setDailyParams(Long id, Integer dailyCalories, Integer dailyProteins, Integer dailyCarbs, Integer dailyFats){
        Diet diet = dietRepository.findById(id).get();
        diet.setDailyParams(dailyCalories, dailyProteins, dailyCarbs, dailyFats);
        // dietRepository.save(diet);
    }
}
