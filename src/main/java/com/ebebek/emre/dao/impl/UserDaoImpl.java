package com.ebebek.emre.dao.impl;

import com.ebebek.emre.model.User;
import com.ebebek.emre.dao.IUserDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements IUserDao {


    /*
        store users in application context
        this map will be destroyed when tomcat stops.
     */
    private static final Map<String, User> USERS_MAP = new HashMap<>(); // map to store Users

    @Override
    public User findUser(String userName) { // find user from username
        User user = USERS_MAP.values()
                .stream()
                .filter(x -> x.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
        return user;
    }

    @Override
    public boolean userExists(String userName) {//check if user exists
        return USERS_MAP.values()
                .stream()
                .anyMatch(x -> x.getUserName().contains(userName));
    }

    @Override
    public void addUser(User user) { //add user to map
        USERS_MAP.put(user.getUserName(), user);
    }
}
