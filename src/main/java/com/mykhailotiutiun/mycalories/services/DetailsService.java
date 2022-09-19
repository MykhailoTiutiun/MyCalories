package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.entities.Details;
import com.mykhailotiutiun.mycalories.persistence.entities.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.DetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailsService {

    private final DetailsRepository detailsRepository;

    public DetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    public Optional<Details> getDetailsById(Long id){
        return detailsRepository.findById(id);
    }

    public void save(User user){
        detailsRepository.save(new Details(user.getId(), user));
    }

    public void setDetailsParams(Long id, Integer age, Integer height, Integer weight, Integer fat){
        Details details = detailsRepository.findById(id).get();
        details.setParams(age, height, weight, fat);
        detailsRepository.save(details);
    }

    public void delete(Long id){
        detailsRepository.deleteById(id);
    }

}
