package com.github.nastyasivko.project_final.dao;

import com.github.nastyasivko.project_final.model.LoginUser;

public interface LoginUserDao {

    LoginUser findLoginUser(String login);

    LoginUser getLoginUser(Long userId);

    LoginUser get(Long id);

    void updatePassword(String login, String newPassword);
}
