package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.LoginUserDao;
import com.github.nastyasivko.project_final.dao.converter.LoginUserConverter;
import com.github.nastyasivko.project_final.dao.entity.LoginUserEntity;
import com.github.nastyasivko.project_final.dao.repository.LoginUserRepository;
import com.github.nastyasivko.project_final.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.NoSuchElementException;

public class DefaultLoginUserDao implements LoginUserDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultLoginUserDao.class);

    private final LoginUserRepository repository;

    public DefaultLoginUserDao(LoginUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoginUser findLoginUser(String login) {
        LoginUserEntity loginUser;
        try {
            loginUser = repository.findByLogin(login).get();
        } catch (NoSuchElementException e) {
            log.info("user not found by login{}", login);
            loginUser = null;
        }
        return LoginUserConverter.fromEntity(loginUser);
    }

    @Override
    public void updatePassword(String login, String newPassword) {
        repository.updatePassword(login, newPassword);
        log.info("update user {} password, new password {}", login, newPassword);

    }

    @Override
    public LoginUser get(Long id) {
        final LoginUserEntity loginUserEntity = repository.getOne(id);
        return LoginUserConverter.fromEntity(loginUserEntity);
    }

    @Override
    public LoginUser getLoginUser(Long id) {
        LoginUserEntity loginUser;
        try {
            loginUser = repository.findByUserId(id).get();
        } catch (NoResultException e) {
            log.info("user not found by userId{}", id);
            loginUser = null;
        }
        return LoginUserConverter.fromEntity(loginUser);
    }

}
