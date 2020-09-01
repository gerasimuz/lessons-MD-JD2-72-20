package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.NewOrderEntity;
import com.github.nastyasivko.project_final.model.UserOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNewOrderConverter {
    @Test
    void fromEntityNull() {
        final UserOrder userOrder = NewOrderConverter.fromEntity(null);
        assertNull(userOrder);
    }

    @Test
    void fromEntityNotNull() {
        final NewOrderEntity newOrderEntity = new NewOrderEntity();
        newOrderEntity.setId(1L);
        newOrderEntity.setUserlogin("user");
        newOrderEntity.setNameRoom("standart");
        newOrderEntity.setNumberOfBeds("1");
        newOrderEntity.setDateStart("2020-10-07");
        newOrderEntity.setDateStart("2020-10-10");

        final UserOrder userOrder = NewOrderConverter.fromEntity(newOrderEntity);

        assertNotNull(userOrder);
        assertEquals(userOrder.getId(), newOrderEntity.getId());
        assertEquals(userOrder.getUserLogin(), newOrderEntity.getUserlogin());
        assertEquals(userOrder.getNameRoom(), newOrderEntity.getNameRoom());
        assertEquals(userOrder.getBeds(), newOrderEntity.getNumberOfBeds());
        assertEquals(userOrder.getDateStart(), newOrderEntity.getDateStart());
        assertEquals(userOrder.getDateEnd(), newOrderEntity.getDateEnd());
    }

    @Test
    void toEntityNull() {
        final NewOrderEntity newOrderEntity = NewOrderConverter.toEntity(null);
        assertNull(newOrderEntity);
    }

    @Test
    void toEntityNotNull() {
        final UserOrder userOrder = new UserOrder(1L, "user", "standart", "3", "2020-10-07", "2020-10-10");

        final NewOrderEntity newOrderEntity = NewOrderConverter.toEntity(userOrder);

        assertNotNull(newOrderEntity);
        assertEquals(userOrder.getId(), newOrderEntity.getId());
        assertEquals(userOrder.getUserLogin(), newOrderEntity.getUserlogin());
        assertEquals(userOrder.getNameRoom(), newOrderEntity.getNameRoom());
        assertEquals(userOrder.getBeds(), newOrderEntity.getNumberOfBeds());
        assertEquals(userOrder.getDateStart(), newOrderEntity.getDateStart());
        assertEquals(userOrder.getDateEnd(), newOrderEntity.getDateEnd());
    }
}
