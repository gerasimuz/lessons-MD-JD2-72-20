package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.LoginUserDao;
import com.github.nastyasivko.project_final.dao.UserDao;
import com.github.nastyasivko.project_final.dao.config.DaoConfig;

import com.github.nastyasivko.project_final.model.LoginUser;
import com.github.nastyasivko.project_final.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class TestDefaultLoginUserDao {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginUserDao loginUserDao;

    @Test
    void findLoginUserExist() {
        User user = new User(null, "user", "user", "1234567");
        LoginUser loginUser = new LoginUser(null, "loginuser", "user", null);

        userDao.saveLoginUser(user, loginUser);
        LoginUser userForDb = loginUserDao.findLoginUser("loginuser");

        assertNotNull(user);
        assertEquals(userForDb.getLogin(), loginUser.getLogin());
        assertEquals(userForDb.getPassword(), loginUser.getPassword());
    }

    @Test
    void findUserNotExist() {
        User user = new User(null, "useruser", "useruser", "1234567");
        LoginUser loginUser = new LoginUser(null, "loginuseruser", "user", null);

        userDao.saveLoginUser(user, loginUser);
        final LoginUser userForDb = loginUserDao.findLoginUser("useruser");

        assertNull(userForDb);
    }

}
