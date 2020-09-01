package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.NewOrderEntity;
import com.github.nastyasivko.project_final.model.UserOrder;

public class NewOrderConverter {
    public static NewOrderEntity toEntity(UserOrder userNewOrder) {
        if (userNewOrder == null) {
            return null;
        }
        final NewOrderEntity userOrderEntity = new NewOrderEntity();
        userOrderEntity.setId(userNewOrder.getId());
        userOrderEntity.setUserlogin(userNewOrder.getUserLogin());
        userOrderEntity.setNameRoom(userNewOrder.getNameRoom());
        userOrderEntity.setNumberOfBeds(userNewOrder.getBeds());
        userOrderEntity.setDateStart(userNewOrder.getDateStart());
        userOrderEntity.setDateEnd(userNewOrder.getDateEnd());
        return userOrderEntity;
    }

    public static UserOrder fromEntity(NewOrderEntity userOrderEntity) {
        if (userOrderEntity == null) {
            return null;
        }
        return new UserOrder(
                userOrderEntity.getId(),
                userOrderEntity.getUserlogin(),
                userOrderEntity.getNameRoom(),
                userOrderEntity.getNumberOfBeds(),
                userOrderEntity.getDateStart(),
                userOrderEntity.getDateEnd());
    }
}
