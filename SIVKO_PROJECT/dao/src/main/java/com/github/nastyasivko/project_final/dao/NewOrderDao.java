package com.github.nastyasivko.project_final.dao;

import com.github.nastyasivko.project_final.model.UserOrder;

public interface NewOrderDao {
    Long saveNewOrder(UserOrder userOrder);

    UserOrder get(Long id);
}
