package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.LoginUserEntity;
import com.github.nastyasivko.project_final.model.LoginUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoginUserConverter {
    @Test
    void fromEntityNull() {
        final LoginUser loginUser = LoginUserConverter.fromEntity(null);
        assertNull(loginUser);
    }

    @Test
    void fromEntityNotNull() {
        final LoginUserEntity loginUserEntity = new LoginUserEntity();
        loginUserEntity.setId(1L);
        loginUserEntity.setLogin("user");
        loginUserEntity.setPassword("2");
        loginUserEntity.setUserId(2L);

        final LoginUser loginUser = LoginUserConverter.fromEntity(loginUserEntity);

        assertNotNull(loginUser);
        assertEquals(loginUser.getId(), loginUserEntity.getId());
        assertEquals(loginUser.getLogin(), loginUserEntity.getLogin());
        assertEquals(loginUser.getPassword(), loginUserEntity.getPassword());
        assertEquals(loginUser.getUserId(), loginUserEntity.getUserId());
    }

    @Test
    void toEntityNull() {
        final LoginUserEntity loginUserEntity = LoginUserConverter.toEntity(null);
        assertNull(loginUserEntity);
    }

    @Test
    void toEntityNotNull() {
        final LoginUser loginUser = new LoginUser(1L, "user", "2", 2L);

        final LoginUserEntity loginUserEntity = LoginUserConverter.toEntity(loginUser);

        assertNotNull(loginUserEntity);
        assertEquals(loginUser.getId(), loginUserEntity.getId());
        assertEquals(loginUser.getLogin(), loginUserEntity.getLogin());
        assertEquals(loginUser.getPassword(), loginUserEntity.getPassword());
        assertEquals(loginUser.getUserId(), loginUserEntity.getUserId());
    }
}

