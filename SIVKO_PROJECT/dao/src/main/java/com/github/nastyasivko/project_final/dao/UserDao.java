package com.github.nastyasivko.project_final.dao;

import com.github.nastyasivko.project_final.model.AnswerUserOrder;
import com.github.nastyasivko.project_final.model.LoginUser;
import com.github.nastyasivko.project_final.model.User;
import com.github.nastyasivko.project_final.model.UserOrder;

import java.util.List;

public interface UserDao {
    Long saveLoginUser(User user, LoginUser loginUser);

    List<AnswerUserOrder> getUserOrders(String login);

    User get(Long id);

    void updatePayAnswerOrder(AnswerUserOrder answerUserOrder);

    List<UserOrder> getAllUserOrder(String login);
}
