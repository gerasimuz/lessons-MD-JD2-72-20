package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.UserOrderEntity;
import com.github.nastyasivko.project_final.model.UserOrder;

public class UserOrderConverter {
    public static UserOrderEntity toEntity(UserOrder userOrder) {
        if (userOrder == null) {
            return null;
        }
        final UserOrderEntity userOrderEntity = new UserOrderEntity();
        userOrderEntity.setId(userOrder.getId());
        userOrderEntity.setUserLogin(userOrder.getUserLogin());
        userOrderEntity.setNameRoom(userOrder.getNameRoom());
        userOrderEntity.setNumberOfBeds(userOrder.getBeds());
        userOrderEntity.setDateStart(userOrder.getDateStart());
        userOrderEntity.setDateEnd(userOrder.getDateEnd());
        return userOrderEntity;
    }

    public static UserOrder fromEntity(UserOrderEntity userOrderEntity) {
        if (userOrderEntity == null) {
            return null;
        }
        return new UserOrder(
                userOrderEntity.getId(),
                userOrderEntity.getUserLogin(),
                userOrderEntity.getNameRoom(),
                userOrderEntity.getNumberOfBeds(),
                userOrderEntity.getDateStart(),
                userOrderEntity.getDateEnd());
    }
}
