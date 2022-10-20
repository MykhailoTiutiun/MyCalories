package com.mykhailotiutiun.mycalories.services.impl;

import com.mykhailotiutiun.mycalories.persistence.models.Details;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.DetailsRepository;
import com.mykhailotiutiun.mycalories.services.DetailsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DetailsServiceImpl implements DetailsService {

    private final DetailsRepository detailsRepository;

    public DetailsServiceImpl(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    @Override
    public Details getDetailsById(Long id){
        return detailsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void save(User user){
        detailsRepository.save(new Details(user.getId(), user));
    }

    @Override
    public void setDetailsParams(Long id, Integer age, Integer height, Integer weight, Integer fat){
        Details details = detailsRepository.findById(id).get();
        details.setParams(age, height, weight, fat);
        detailsRepository.save(details);
    }

    @Override
    public void delete(Long id){
        detailsRepository.deleteById(id);
    }

}
