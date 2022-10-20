package com.mykhailotiutiun.mycalories.persistence.repositories;

import com.mykhailotiutiun.mycalories.persistence.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}