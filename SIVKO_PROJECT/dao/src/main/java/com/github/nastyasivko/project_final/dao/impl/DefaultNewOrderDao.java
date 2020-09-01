package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.NewOrderDao;
import com.github.nastyasivko.project_final.dao.converter.NewOrderConverter;
import com.github.nastyasivko.project_final.dao.entity.NewOrderEntity;
import com.github.nastyasivko.project_final.dao.repository.NewOrderRepository;
import com.github.nastyasivko.project_final.model.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultNewOrderDao implements NewOrderDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultLoginUserDao.class);

    private final NewOrderRepository repository;

    public DefaultNewOrderDao(NewOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long saveNewOrder(UserOrder userOrder) {
        NewOrderEntity newOrderEntity = NewOrderConverter.toEntity(userOrder);

        repository.save(newOrderEntity);
        log.info("new order user's {} save", userOrder.getUserLogin());

        return newOrderEntity.getId();
    }

    @Override
    public UserOrder get(Long id) {
        NewOrderEntity newOrderEntity = repository.getOne(id);
        return NewOrderConverter.fromEntity(newOrderEntity);
    }
}
