package com.github.nastyasivko.project_final.dao;

import com.github.nastyasivko.project_final.model.UserOrder;

public interface UserOrderDao {
    Long saveUserOrder(UserOrder userOrder);

    UserOrder get(Long id);

    UserOrder getUserOrder(UserOrder userOrder);
}
