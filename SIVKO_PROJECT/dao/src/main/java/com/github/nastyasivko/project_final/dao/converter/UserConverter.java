package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.UserEntity;
import com.github.nastyasivko.project_final.model.User;

public class UserConverter {
    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setPhone(user.getPhone());
        return userEntity;
    }

    public static User fromEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getPhone());
    }
}
