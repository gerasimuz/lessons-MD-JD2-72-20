package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.UserOrderDao;
import com.github.nastyasivko.project_final.dao.converter.UserOrderConverter;
import com.github.nastyasivko.project_final.dao.entity.UserOrderEntity;
import com.github.nastyasivko.project_final.dao.repository.UserOrderRepository;
import com.github.nastyasivko.project_final.model.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultUserOrderDao implements UserOrderDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultLoginUserDao.class);


    private final UserOrderRepository repository;

    public DefaultUserOrderDao(UserOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long saveUserOrder(UserOrder userOrder) {

        UserOrderEntity userOrderEntity = UserOrderConverter.toEntity(userOrder);

        repository.save(userOrderEntity);
        log.info("order user's {} save", userOrder.getUserLogin());

        return userOrderEntity.getId();
    }

    @Override
    public UserOrder get(Long id) {
        UserOrderEntity userOrderEntity = repository.getOne(id);
        return UserOrderConverter.fromEntity(userOrderEntity);
    }

    @Override
    public UserOrder getUserOrder(UserOrder userOrder) {
        List<UserOrderEntity> userOrderEntity = repository.findByUserLoginAndNameRoomAndNumberOfBedsAndDateStartAndDateEnd(userOrder.getUserLogin(), userOrder.getNameRoom(), userOrder.getBeds(), userOrder.getDateStart(), userOrder.getDateEnd());
        return UserOrderConverter.fromEntity(userOrderEntity.get(0));
    }
}
