package com.mykhailotiutiun.mycalories.persistence.repositories;

import com.mykhailotiutiun.mycalories.persistence.models.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long> {


}
