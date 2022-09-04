package com.mykhailotiutiun.mycalories.services;

import com.mykhailotiutiun.mycalories.persistence.entities.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.RoleRepository;
import com.mykhailotiutiun.mycalories.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getUserByEmail(email).get();
    }

    public void saveUser(User user){
        user.setId(generateId());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleRepository.findById(0L).get()));
        userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    private Long generateId(){
        Long generatedId;
        Random random = new Random();
        do {
            generatedId = random.nextLong(10000000000L, 100000000000L);
        }
        while (userRepository.existsById(generatedId));
        return generatedId;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }
}
