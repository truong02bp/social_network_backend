package com.socical.network.services.impl;

import com.socical.network.data.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

//    @Test
//    void shouldBeCreatedSuccessUser() {
//        User user = new User();
//        user.setEmail("truong02.bp@gmail.com");
//        user.setPassword("123456");
//        user.setPhone("0964279710");
//        user = userService.create(user);
//        assert user != null;
//    }
//
//    @Test
//    void shouldBeCreatedFailureUser() {
//        assertThrows(RuntimeException.class, () -> {
//            User user = new User();
//            user.setEmail("truong02.bp@gmail.com");
//            user.setPassword("123456");
//            user.setPhone("0964279710");
//            userService.create(user);
//        });
//    }
}
