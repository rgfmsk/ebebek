package com.ebebek.emre.web;

import com.ebebek.emre.model.User;
import com.ebebek.emre.service.impl.UserServiceImpl;
import com.ebebek.emre.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public ModelAndView homePage() {
        SecurityContext context = SecurityContextHolder.getContext();
        ModelAndView model = new ModelAndView();
        model.addObject("user", userService.findUser(context.getAuthentication().getName()));// add logged in user information to model
        model.setViewName("profile");
        return model;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("userForm", new User());//add User instance for form inputs
        model.setViewName("register");
        return model;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        userValidator.validate(user, bindingResult); // validate inputs
        if (bindingResult.hasErrors()) {
            return "register";// if any, return to register page
        }
        userService.addUser(user); //if not, add user to Map

        try {
            request.login(user.getUserName(), user.getConfirmPassword()); // auto login the registered user
        } catch (ServletException e) {
            e.printStackTrace(System.err);
        }

        model.addAttribute("user", user); //add user information to model
        return "redirect:/profile"; //return to profile page
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            model.addObject("message", "Logged out successfully.");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        if (name != null && !name.equals("anonymousUser")) { // if an user logged in, even if request made to "/" or "/login" paths, return to profile page
            model.setViewName("profile");
            model.addObject("user", userService.findUser(name));
        } else // if no user logged in , return to login page
            model.setViewName("login");
        return model;
    }
}
