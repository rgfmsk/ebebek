package com.ebebek.emre.service;

import com.ebebek.emre.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {

    public boolean userExists(String username) throws UsernameNotFoundException;

    public void addUser(User user);

    public User findUser(String username);
}
