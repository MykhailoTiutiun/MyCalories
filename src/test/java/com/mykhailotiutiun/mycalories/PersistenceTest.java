package com.mykhailotiutiun.mycalories;

import com.mykhailotiutiun.mycalories.persistence.entities.Role;
import com.mykhailotiutiun.mycalories.persistence.entities.User;
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

    @Test
    public void userPersitenceTest(){
        User testUser = userRepository.save(new User(10L, "TestUser", "TestEmail", "TestPassword"));
        Assert.assertNotNull(userRepository.findById(10L).get());
        userRepository.delete(testUser);
    }

    @Test
    public void rolePersitenceTest(){
        Role testRole = roleRepository.save(new Role(10L, "TestName"));
        Assert.assertNotNull(roleRepository.findById(10L).get());
        roleRepository.delete(testRole);
    }
}
