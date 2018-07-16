package com.ebebek.emre.dao;

import com.ebebek.emre.model.User;

public interface IUserDao {

    public void addUser(User user);

    public User findUser(String userName);

    public boolean userExists(String userName);
}
