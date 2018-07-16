package com.ebebek.emre.validator;

import com.ebebek.emre.model.User;
import com.ebebek.emre.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserServiceImpl userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        Pattern upper = Pattern.compile("[A-Z ]"); //uppercase letter pattern
        Pattern lower = Pattern.compile("[a-z ]"); //lowercase letter pattern
        Pattern digit = Pattern.compile("[0-9 ]"); //digit pattern
        Pattern mail = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b"); //regular expression for e-mail

        /*
            null field validations
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "validation.user.username.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "validation.user.email.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validation.user.password.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "validation.user.confirmpassword.null");

        /*
            pattern validations
         */
        if (!upper.matcher(user.getPassword()).find()) {
            errors.rejectValue("password", "validation.user.password.upper");
        }
        if (!lower.matcher(user.getPassword()).find()) {
            errors.rejectValue("password", "validation.user.password.lower");
        }
        if (!digit.matcher(user.getPassword()).find()) {
            errors.rejectValue("password", "validation.user.password.digit");
        }
        if (!mail.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "validation.user.email.valid");
        }

        /*
            Size validations
         */
        if (user.getUserName().length() < 3) {
            errors.rejectValue("userName", "validation.user.username.size");
        }
        if (user.getPassword().length() < 7) {
            errors.rejectValue("password", "validation.user.password.size");
        }
        /*
            password confirmation
         */
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "validation.user.password.match");
        }
        /*
            existance validation for user
         */
        if (userService != null && userService.userExists(user.getUserName())){
            errors.rejectValue("userName", "validation.user.username.exists");
        }

    }

}
