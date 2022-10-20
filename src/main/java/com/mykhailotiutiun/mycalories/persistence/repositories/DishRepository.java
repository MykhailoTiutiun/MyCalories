package com.mykhailotiutiun.mycalories.persistence.repositories;

import com.mykhailotiutiun.mycalories.persistence.models.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DishRepository extends PagingAndSortingRepository<Dish, Long> {

    Page<Dish> findAll(Pageable pageable);

    Page<Dish> findAllByNameContaining(String name, Pageable pageable);

}