package Repositories;

import models.User;

import java.util.List;

public interface UsersRepository{
    List<User> findAll();
    void save (User user);
    boolean isExist(String name, String password);
}

