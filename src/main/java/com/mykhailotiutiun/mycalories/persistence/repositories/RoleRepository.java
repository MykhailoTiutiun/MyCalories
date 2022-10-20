package com.mykhailotiutiun.mycalories.persistence.repositories;

import com.mykhailotiutiun.mycalories.persistence.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}