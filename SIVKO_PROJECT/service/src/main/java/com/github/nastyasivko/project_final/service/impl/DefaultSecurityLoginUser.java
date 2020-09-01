package com.github.nastyasivko.project_final.service.impl;

import com.github.nastyasivko.project_final.dao.LoginUserDao;
import com.github.nastyasivko.project_final.model.LoginUser;
import com.github.nastyasivko.project_final.service.SecurityLoginUser;
import org.springframework.transaction.annotation.Transactional;

public class DefaultSecurityLoginUser implements SecurityLoginUser {

    private final LoginUserDao loginUserDao;

    public DefaultSecurityLoginUser(LoginUserDao loginUserDao) {
        this.loginUserDao = loginUserDao;
    }

    @Override
    @Transactional
    public boolean dublicateLogin(String login) {
        LoginUser user = loginUserDao.findLoginUser(login);
        if (user == null) {
            return true;
        } else {
            return false;
        }
    }

}
