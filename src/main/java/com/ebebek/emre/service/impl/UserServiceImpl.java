package com.ebebek.emre.service.impl;

import com.ebebek.emre.dao.IUserDao;
import com.ebebek.emre.model.User;
import com.ebebek.emre.service.IUserService;
import com.ebebek.emre.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public boolean userExists(String username) throws UsernameNotFoundException {
        User user = userDao.findUser(username);
        return (user != null ? true : false);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    public User findUser(String username) {
        return userDao.findUser(username);
    }

}
