package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.UserEntity;
import com.github.nastyasivko.project_final.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserConverter {
    @Test
    void fromEntityNull() {
        final User user = UserConverter.fromEntity(null);
        assertNull(user);
    }

    @Test
    void fromEntityNotNull() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("user");
        userEntity.setSurname("2");
        userEntity.setPhone("2");

        final User user = UserConverter.fromEntity(userEntity);

        assertNotNull(user);
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getName(), userEntity.getName());
        assertEquals(user.getSurname(), userEntity.getSurname());
        assertEquals(user.getPhone(), userEntity.getPhone());
    }

    @Test
    void toEntityNull() {
        final UserEntity userEntity = UserConverter.toEntity(null);
        assertNull(userEntity);
    }

    @Test
    void toEntityNotNull() {
        final User user = new User(1L, "user", "2", "2");

        final UserEntity userEntity = UserConverter.toEntity(user);

        assertNotNull(userEntity);
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getName(), userEntity.getName());
        assertEquals(user.getSurname(), userEntity.getSurname());
        assertEquals(user.getPhone(), userEntity.getPhone());
    }
}
