package com.ebebek.emre.test.service;

import com.ebebek.emre.config.web.SpringWebConfig;
import com.ebebek.emre.dao.IUserDao;
import com.ebebek.emre.model.User;
import com.ebebek.emre.service.impl.UserServiceImpl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import com.ebebek.emre.validator.UserValidator;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {SpringWebConfig.class})
@WebAppConfiguration
public class UserServiceTest {
    @Mock
    IUserDao dao;

    @InjectMocks
    UserServiceImpl userService;

    @Spy
    List<User> users = new ArrayList<User>();

    @Captor
    ArgumentCaptor<User> captor;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);//init mockito
        users = getUserList();//load temp users
        validator = new UserValidator();
    }

    private UserValidator validator;
    private Errors errors;

    @Test
    public void addUser() {
        doNothing().when(dao).addUser(any(User.class)); //do nothing when user added
        userService.addUser(users.get(0));

        verify(dao, times(1)).addUser(captor.capture()); // dao.addUser called once

        Assert.assertEquals(captor.getValue().getUserName(), "test1"); //dao.addUser was called with User "test1"

        Assert.assertEquals(2, users.size());
        verify(users, times(2)).add(any(User.class));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void saveExistingEmployee() {
        doThrow(RuntimeException.class).when(dao).addUser(users.get(0)); // throw an exception when dao.addUser will be called.
        userService.addUser(any(User.class));
    }

    @Test
    public void testValidations() {
        errors = new BeanPropertyBindingResult(users.get(0), users.get(0).getClass().getName());
        validator.validate(users.get(0), errors); // validate user 1
        Assert.assertEquals(errors.getFieldErrorCount(), 2); // email and password is not valid

        errors = new BeanPropertyBindingResult(users.get(1), users.get(1).getClass().getName());
        validator.validate(users.get(1), errors);// validate user 2
        Assert.assertEquals(errors.getFieldErrorCount(), 0); // all inputs valid
    }

    public List<User> getUserList() { //create temp users

        User u = new User();
        u.setUserName("test1");
        u.setEmail("gecerisz@asd");
        u.setPassword("123ar");
        u.setConfirmPassword("123ar");
        users.add(u);

        u = new User();
        u.setUserName("test2");
        u.setEmail("gecerli@asd.com");
        u.setPassword("aAst123");
        u.setConfirmPassword("aAst123");
        users.add(u);

        return users;
    }
}
