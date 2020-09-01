package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.NewOrderDao;
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
public class TestDefaultNewOrderDao {
    @Autowired
    private NewOrderDao newOrderDao;

    @Test
    void testSaveNewOrder() {
        UserOrder userOrder = new UserOrder(null, "user", "standart", "4", "2020-10-07", "2020-10-10");
        Long id = newOrderDao.saveNewOrder(userOrder);

        UserOrder newOrderFromEntity = newOrderDao.get(1L);

        assertNotNull(newOrderFromEntity);
        assertEquals(newOrderFromEntity.getUserLogin(), userOrder.getUserLogin());
        assertEquals(newOrderFromEntity.getNameRoom(), userOrder.getNameRoom());
        assertEquals(newOrderFromEntity.getBeds(), userOrder.getBeds());
        assertEquals(newOrderFromEntity.getDateStart(), userOrder.getDateStart());
        assertEquals(newOrderFromEntity.getDateEnd(), userOrder.getDateEnd());
    }
}
