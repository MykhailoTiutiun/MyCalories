package com.mykhailotiutiun.mycalories.services.impl;

import com.mykhailotiutiun.mycalories.persistence.models.Dish;
import com.mykhailotiutiun.mycalories.persistence.models.Role;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.RoleRepository;
import com.mykhailotiutiun.mycalories.persistence.repositories.UserRepository;
import com.mykhailotiutiun.mycalories.services.DetailsService;
import com.mykhailotiutiun.mycalories.services.DietService;
import com.mykhailotiutiun.mycalories.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DietService dietService;
    private final DetailsService detailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, DietService dietService, DetailsService detailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.dietService = dietService;
        this.detailsService = detailsService;
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getUserByEmail(email);
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public void createUser(User user){
        user.setId(generateId());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        if(roleRepository.findById(0L).isEmpty()){
            roleRepository.save(new Role(0L, "ROLE_USER"));
        }

        user.setRoles(Set.of(roleRepository.findById(0L).orElseThrow(EntityNotFoundException::new)));
        dietService.create(user);
        detailsService.save(user);

        user.setDiet(dietService.getDietById(user.getId()));
        user.setDetails(detailsService.getDetailsById(user.getId()));

        userRepository.save(user);
    }

    @Override
    public void addDishOwned(User user, Dish dish) {
        Set<Dish> dishes = user.getOwnedDishes();
        dishes.add(dish);
        user.setOwnedDishes(dishes);

        saveUser(user);
    }

    @Override
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    private Long generateId(){
        Long generatedId;
        Random random = new Random();
        do generatedId = random.nextLong(10000000000L, 100000000000L);
        while (userRepository.existsById(generatedId));
        return generatedId;
    }


    @Override
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }
}
