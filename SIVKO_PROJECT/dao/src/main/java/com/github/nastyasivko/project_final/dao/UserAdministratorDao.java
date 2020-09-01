package com.github.nastyasivko.project_final.dao;

import com.github.nastyasivko.project_final.model.AnswerUserOrder;
import com.github.nastyasivko.project_final.model.Cost;
import com.github.nastyasivko.project_final.model.UserOrder;

import java.util.List;

public interface UserAdministratorDao {
    List<UserOrder> getUsersOrders();

    boolean deleteNewOrders(UserOrder userOrder);

    List<UserOrder> getNewOrdersForPage(int page);

    Long saveApprovedOrder(UserOrder userOrder, Integer numberRoom, Cost cost);

    Long saveDeniedOrder(UserOrder userOrder);

    List<AnswerUserOrder> getApprovedOrder();
}
