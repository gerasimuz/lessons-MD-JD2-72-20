package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.UserOrderEntity;
import com.github.nastyasivko.project_final.model.UserOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserOrderConverter {

    @Test
    void fromEntityNull() {
        final UserOrder userOrder = UserOrderConverter.fromEntity(null);
        assertNull(userOrder);
    }

    @Test
    void fromEntityNotNull() {
        final UserOrderEntity userOrderEntity = new UserOrderEntity();
        userOrderEntity.setId(1L);
        userOrderEntity.setUserLogin("user");
        userOrderEntity.setNameRoom("standart");
        userOrderEntity.setNumberOfBeds("1");
        userOrderEntity.setDateStart("2020-10-07");
        userOrderEntity.setDateStart("2020-10-10");

        final UserOrder userOrder = UserOrderConverter.fromEntity(userOrderEntity);

        assertNotNull(userOrder);
        assertEquals(userOrder.getId(), userOrderEntity.getId());
        assertEquals(userOrder.getUserLogin(), userOrderEntity.getUserLogin());
        assertEquals(userOrder.getNameRoom(), userOrderEntity.getNameRoom());
        assertEquals(userOrder.getBeds(), userOrderEntity.getNumberOfBeds());
        assertEquals(userOrder.getDateStart(), userOrderEntity.getDateStart());
        assertEquals(userOrder.getDateEnd(), userOrderEntity.getDateEnd());
    }

    @Test
    void toEntityNull() {
        final UserOrderEntity userOrderEntity = UserOrderConverter.toEntity(null);
        assertNull(userOrderEntity);
    }

    @Test
    void toEntityNotNull() {
        final UserOrder userOrder = new UserOrder(1L, "user", "standart", "3", "2020-10-07", "2020-10-10");

        final UserOrderEntity userOrderEntity = UserOrderConverter.toEntity(userOrder);

        assertNotNull(userOrderEntity);
        assertEquals(userOrder.getId(), userOrderEntity.getId());
        assertEquals(userOrder.getUserLogin(), userOrderEntity.getUserLogin());
        assertEquals(userOrder.getNameRoom(), userOrderEntity.getNameRoom());
        assertEquals(userOrder.getBeds(), userOrderEntity.getNumberOfBeds());
        assertEquals(userOrder.getDateStart(), userOrderEntity.getDateStart());
        assertEquals(userOrder.getDateEnd(), userOrderEntity.getDateEnd());
    }
}
