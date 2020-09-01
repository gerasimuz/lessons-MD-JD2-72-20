package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.HotelRoomDao;
import com.github.nastyasivko.project_final.dao.UserDao;
import com.github.nastyasivko.project_final.dao.converter.LoginUserConverter;
import com.github.nastyasivko.project_final.dao.converter.NewOrderConverter;
import com.github.nastyasivko.project_final.dao.converter.UserConverter;
import com.github.nastyasivko.project_final.dao.converter.UserOrderConverter;
import com.github.nastyasivko.project_final.dao.entity.*;
import com.github.nastyasivko.project_final.dao.repository.ApprovedOrderRepository;
import com.github.nastyasivko.project_final.dao.repository.DeniedOrderRepository;
import com.github.nastyasivko.project_final.dao.repository.UserOrderRepository;
import com.github.nastyasivko.project_final.dao.repository.UserRepository;
import com.github.nastyasivko.project_final.model.AnswerUserOrder;
import com.github.nastyasivko.project_final.model.LoginUser;
import com.github.nastyasivko.project_final.model.User;
import com.github.nastyasivko.project_final.model.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.github.nastyasivko.project_final.dao.impl.DefaultHotelRoomDao.*;
import static java.lang.Integer.parseInt;

public class DefaultUserDao implements UserDao {

    private final UserRepository repository;
    private final UserOrderRepository repositoryOrder;
    private final ApprovedOrderRepository repositoryApproved;
    private final DeniedOrderRepository repositoryDenied;

    public DefaultUserDao(UserRepository repository, UserOrderRepository repositoryOrder, ApprovedOrderRepository repositoryApproved, DeniedOrderRepository repositoryDenied) {
        this.repository = repository;
        this.repositoryOrder = repositoryOrder;
        this.repositoryApproved = repositoryApproved;
        this.repositoryDenied = repositoryDenied;
    }

    @Autowired
    private HotelRoomDao hotelRoomDao;

    @Override
    public Long saveLoginUser(User user, LoginUser loginUser) {
        UserEntity userEntity = UserConverter.toEntity(user);
        LoginUserEntity loginUserEntity = LoginUserConverter.toEntity(loginUser);

        loginUserEntity.setUserEntity(userEntity);
        userEntity.setLoginUserEntity(loginUserEntity);
        repository.save(userEntity);

        return userEntity.getId();
    }

    @Override
    public User get(Long id) {
        final UserEntity userEntity = repository.getOne(id);
        return UserConverter.fromEntity(userEntity);
    }

    @Override
    public List<UserOrder> getAllUserOrder(String login) {
        List<UserOrderEntity> userOrderEntity = repositoryOrder.findByUserLogin(login);
        List<UserOrder> userOrders = new ArrayList<>();
        for (int i = 0; i < userOrderEntity.size(); i++) {
            userOrders.add(UserOrderConverter.fromEntity(userOrderEntity.get(i)));
        }
        return userOrders;
    }

    @Override
    public List<AnswerUserOrder> getUserOrders(String login) {
        List<UserOrderEntity> userOrderEntity = repositoryOrder.findByUserLogin(login);
        List<AnswerUserOrder> userOrders = new ArrayList<>();
        for (UserOrderEntity userOrder : userOrderEntity) {
            Long id = userOrder.getId();
            List<ApprovedOrderEntity> userApprovedOrderEntity = repositoryApproved.findByIdUserOrder(id);
            int days = 0;
            if (userApprovedOrderEntity.size() != 0) {
                String[] strStart = hotelRoomDao.getArrayYearMonthDate(userApprovedOrderEntity.get(0).getDateStart());
                String[] strEnd = hotelRoomDao.getArrayYearMonthDate(userApprovedOrderEntity.get(0).getDateEnd());
                if (strStart[1].equals(strEnd[1])) {
                    days = parseInt(strEnd[2]) - parseInt(strStart[2]);
                } else if (strStart[1].equals("12") || strStart[1].equals("01") || strStart[1].equals("03") || strStart[1].equals("05") || strStart[1].equals("07") || strStart[1].equals("08") || strStart[1].equals("10")) {
                    int dayStart = DAYS_MONTH_NOT_THIRTY - parseInt(strStart[2]);
                    int dayEnd = parseInt(strEnd[2]) - 1;
                    days = dayStart + dayEnd;
                } else if (strStart[1].equals("04") || strStart[1].equals("06") || strStart[1].equals("09") || strStart[1].equals("11")) {
                    int dayStart = DAYS_MONTH_THIRTY - parseInt(strStart[2]);
                    int dayEnd = parseInt(strEnd[2]) - 1;
                    days = dayStart + dayEnd;
                } else if (strStart[1].equals("02")) {
                    if (parseInt(strStart[0]) % 4 == 0 && (parseInt(strStart[0]) % NUMBER_HUNDRED != 0 || parseInt(strStart[0]) % NUMBER_FOUR_HUNDRED == 0)) {
                        int dayStart = DAYS_FEB_LEAP_YEAR - parseInt(strStart[2]);
                        int dayEnd = parseInt(strEnd[2]) - 1;
                        days = dayStart + dayEnd;
                    } else {
                        int dayStart = DAYS_FEB_NOT_LEAP_YEAR - parseInt(strStart[2]);
                        int dayEnd = parseInt(strEnd[2]) - 1;
                        days = dayStart + dayEnd;
                    }
                }
            }
            List<DeniedOrderEntity> userDeniedOrderEntity = repositoryDenied.findByIdUserOrder(id);
            if (userApprovedOrderEntity.size() != 0) {
                for (int i = 0; i < userApprovedOrderEntity.size(); i++) {
                    userOrders.add(new AnswerUserOrder(userOrder.getId(), userOrder.getUserLogin(), userOrder.getNameRoom(), userOrder.getNumberOfBeds(), userOrder.getDateStart(), userOrder.getDateEnd(), userApprovedOrderEntity.get(i).getAnswer(), userApprovedOrderEntity.get(i).getNumberRoom(), (userApprovedOrderEntity.get(i).getCostRoomEntity().getCost() * days), userApprovedOrderEntity.get(i).getPayAnswer()));
                }
            }
            if (userDeniedOrderEntity.size() != 0) {
                for (int i = 0; i < userDeniedOrderEntity.size(); i++) {
                    userOrders.add(new AnswerUserOrder(userOrder.getUserLogin(), userOrder.getNameRoom(), userOrder.getNumberOfBeds(), userOrder.getDateStart(), userOrder.getDateEnd(), userDeniedOrderEntity.get(i).getAnswer()));
                }
            }
        }
        return userOrders;
    }

    @Override
    public void updatePayAnswerOrder(AnswerUserOrder answerUserOrder) {
        repositoryApproved.updatePayAnswer(answerUserOrder.getIdUserOrder(), "Pay");
    }
}
