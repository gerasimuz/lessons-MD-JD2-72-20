package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.LoginUserEntity;
import com.github.nastyasivko.project_final.model.LoginUser;

public class LoginUserConverter {
    public static LoginUserEntity toEntity(LoginUser loginUser) {
        if (loginUser == null) {
            return null;
        }
        final LoginUserEntity loginUserEntity = new LoginUserEntity();
        loginUserEntity.setId(loginUser.getId());
        loginUserEntity.setLogin(loginUser.getLogin());
        loginUserEntity.setPassword(loginUser.getPassword());
        loginUserEntity.setUserId(loginUser.getUserId());
        return loginUserEntity;
    }

    public static LoginUser fromEntity(LoginUserEntity loginUserEntity) {
        if (loginUserEntity == null) {
            return null;
        }
        return new LoginUser(
                loginUserEntity.getId(),
                loginUserEntity.getLogin(),
                loginUserEntity.getPassword(),
                loginUserEntity.getUserId());
    }
}
