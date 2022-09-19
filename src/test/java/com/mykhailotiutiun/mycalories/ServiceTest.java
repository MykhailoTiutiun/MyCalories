package com.mykhailotiutiun.mycalories;

import com.mykhailotiutiun.mycalories.persistence.entities.User;
import com.mykhailotiutiun.mycalories.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void userServiceTest(){
        User testUser = new User("TestUser", "TestEmail", "TestPassword");
        userService.createUser(testUser);
        testUser = userService.getUserByEmail("TestEmail").get();
        Assert.assertNotNull(userService.getUserByEmail("TestEmail").get());
        Assert.assertNotNull(userService.getUserById(testUser.getId()).get());

        User finalTestUser = testUser;
        Assert.assertNotNull(userService.getAllUsers().stream().filter(user -> user.getId().equals(finalTestUser.getId())).toList().get(0));

        userService.deleteById(testUser.getId());
    }

}
