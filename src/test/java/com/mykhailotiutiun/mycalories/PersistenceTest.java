package com.mykhailotiutiun.mycalories;

import com.mykhailotiutiun.mycalories.persistence.models.Role;
import com.mykhailotiutiun.mycalories.persistence.models.User;
import com.mykhailotiutiun.mycalories.persistence.repositories.DietRepository;
import com.mykhailotiutiun.mycalories.persistence.repositories.RoleRepository;
import com.mykhailotiutiun.mycalories.persistence.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistenceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    DietRepository dietRepository;

    @Test
    public void userPersitenceTest(){
        User testUser = new User("TestUser", "TestEmail", "TestPassword");
        testUser.setId(10L);
        userRepository.save(testUser);
        Assert.assertNotNull(userRepository.findById(10L).get());
        userRepository.delete(testUser);
    }

    @Test
    public void rolePersitenceTest(){
        Role testRole = roleRepository.save(new Role(10L, "TestName"));
        Assert.assertNotNull(roleRepository.findById(10L).get());
        roleRepository.delete(testRole);
    }

//    @Test
//    public void dietPersistenceTest(){
//        User testUser = new User("TestUser", "TestEmail", "TestPassword");
//        testUser.setId(10L);
//        userRepository.save(testUser);
//        Diet testDiet = new Diet(testUser.getId(), testUser);
//        testDiet.setDailyParams(10,10,10,10);
//        dietRepository.save(testDiet);
//        Assert.assertNotNull(dietRepository.findById(testDiet.getId()).get().getDailyCalories());
//        Assert.assertNotNull(dietRepository.findByUser(testUser).get().getDailyCalories());
//        dietRepository.delete(testDiet);
//        userRepository.delete(testUser);
//    }
}
