package com.github.nastyasivko.project_final.dao.impl;


import com.github.nastyasivko.project_final.dao.UserOrderDao;
import com.github.nastyasivko.project_final.dao.config.DaoConfig;
import com.github.nastyasivko.project_final.model.UserOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class TestDefaultUserOrderDao {
    @Autowired
    private UserOrderDao userOrderDao;

    @Test
    void testSaveNewOrder() {
        UserOrder userOrder = new UserOrder(null, "test", "standart", "4", "2020-10-07", "2020-10-10");

        Long id = userOrderDao.saveUserOrder(userOrder);

        UserOrder orderFromEntity = userOrderDao.get(id);

        assertNotNull(orderFromEntity);
        assertEquals(orderFromEntity.getUserLogin(), userOrder.getUserLogin());
        assertEquals(orderFromEntity.getNameRoom(), userOrder.getNameRoom());
        assertEquals(orderFromEntity.getBeds(), userOrder.getBeds());
        assertEquals(orderFromEntity.getDateStart(), userOrder.getDateStart());
        assertEquals(orderFromEntity.getDateEnd(), userOrder.getDateEnd());
    }

    @Test
    void testGetUserOrder() {
        UserOrder userOrder = new UserOrder(null, "useruser", "standart", "3", "2020-10-07", "2020-10-10");
        long id = userOrderDao.saveUserOrder(userOrder);

        UserOrder userOrderNew = userOrderDao.getUserOrder(userOrder);

        assertEquals(userOrderNew.getId(), id);
        assertEquals(userOrderNew.getUserLogin(), userOrder.getUserLogin());
        assertEquals(userOrderNew.getNameRoom(), userOrder.getNameRoom());
        assertEquals(userOrderNew.getBeds(), userOrder.getBeds());
        assertEquals(userOrderNew.getDateStart(), userOrder.getDateStart());
        assertEquals(userOrderNew.getDateEnd(), userOrder.getDateEnd());
    }
}
