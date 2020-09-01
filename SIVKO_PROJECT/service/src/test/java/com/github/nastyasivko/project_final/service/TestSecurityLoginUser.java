package com.github.nastyasivko.project_final.service;

import com.github.nastyasivko.project_final.dao.LoginUserDao;
import com.github.nastyasivko.project_final.model.LoginUser;
import com.github.nastyasivko.project_final.service.impl.DefaultSecurityLoginUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestSecurityLoginUser {
    @Mock
    LoginUserDao loginUserDao;

    @InjectMocks
    DefaultSecurityLoginUser service;

    @Test
    void testDublicateLoginNotExist() {
        when(loginUserDao.findLoginUser("user")).thenReturn(null);
        boolean result = service.dublicateLogin("user");
        assertTrue(result);
    }

    @Test
    void testDublicateLoginExist() {
        when(loginUserDao.findLoginUser("user")).thenReturn(new LoginUser(null, "user", "user", null));
        boolean result = service.dublicateLogin("user");
        assertFalse(result);
    }

}
