package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.*;
import com.github.nastyasivko.project_final.dao.config.DaoConfig;
import com.github.nastyasivko.project_final.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class TestDefaultUserDao {
    @Autowired
    private UserDao dao;

    @Autowired
    private CostDao costDao;

    @Autowired
    private UserAdministratorDao administratorDao;

    @Autowired
    private UserOrderDao userOrderDao;

    @Autowired
    private LoginUserDao loginDao;

    @Test
    void testSaveUser() {
        User user = new User(null, "Nastya", "Test", "1234567");
        LoginUser loginUser = new LoginUser(null, "testlog", "testpas", null);

        long id = dao.saveLoginUser(user, loginUser);
        long idLoginUser = loginDao.getLoginUser(id).getId();


        User userEntity = dao.get(id);
        LoginUser loginUserEntity = loginDao.get(idLoginUser);

        assertNotNull(userEntity);
        assertNotNull(loginUserEntity);
        assertEquals(userEntity.getName(), user.getName());
        assertEquals(userEntity.getSurname(), user.getSurname());
        assertEquals(loginUserEntity.getLogin(), loginUser.getLogin());
        assertEquals(loginUserEntity.getPassword(), loginUser.getPassword());
    }

    @BeforeEach
    public void init() {
        UserOrder userOrderA = new UserOrder(null, "usertestorder", "standart", "4", "2020-10-07", "2020-11-11");
        UserOrder userOrderD = new UserOrder(null, "usertestorder", "standartroom", "5", "2020-10-07", "2020-10-11");
        userOrderDao.saveUserOrder(userOrderA);
        userOrderDao.saveUserOrder(userOrderD);
        costDao.saveCost(new Cost(null, 2000));

        UserOrder userOrderFromDbA = userOrderDao.getUserOrder(new UserOrder(null, userOrderA.getUserLogin(), userOrderA.getNameRoom(), userOrderA.getBeds(), userOrderA.getDateStart(), userOrderA.getDateEnd()));
        UserOrder userOrderFromDbD = userOrderDao.getUserOrder(new UserOrder(null, userOrderD.getUserLogin(), userOrderD.getNameRoom(), userOrderD.getBeds(), userOrderD.getDateStart(), userOrderD.getDateEnd()));

        administratorDao.saveApprovedOrder(userOrderFromDbA, 101, new Cost(null, 2000));
        administratorDao.saveDeniedOrder(userOrderFromDbD);
    }

    @Test
    void testGetAllUserOrder() {
        List<UserOrder> orderList = dao.getAllUserOrder("usertestorder");

        assertNotNull(orderList);
    }

    @Test
    void testGetUserOrders() {
        List<AnswerUserOrder> userOrders = dao.getUserOrders("usertestorder");

        assertNotNull(userOrders);
    }

    @Test
    void testUpdateApprovedOrder() {
        List<AnswerUserOrder> userOrders = dao.getUserOrders("usertestorder");

        dao.updatePayAnswerOrder(userOrders.get(0));

        List<AnswerUserOrder> userOrdersNew = dao.getUserOrders("usertestorder");
        assertEquals(userOrdersNew.get(0).getPayAnswer(), "Pay");
    }
}
