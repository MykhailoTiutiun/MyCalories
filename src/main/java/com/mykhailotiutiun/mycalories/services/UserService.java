package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.models.Dish;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserById(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    void saveUser(User user);

    void createUser(User user);

    void addDishOwned(User user, Dish dish);

    void deleteById(Long id);

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder();
}
