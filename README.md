# e-bebek Programming Assignment

This assignment is consist of 3 web pages , 1 controller class, 1 service layer, 1 dao layer, 1 validator, spring configuration classes and a test class.
- `web pages`
    - login 
    ```
        - consist of simple login form with 2 inputs { userName, password } and a submit button
    ```
    - register
    ```
        - consist of simple registration form with 4 inputs { userName, email, password, confirmationPassword } and a submit button
        
        - inputs have validations, and validation errors are shown in bottom of every input field, validation details are shown in validation class
    ```
    - profile
    ``` 
        - consists of the user informations { userName, email, encrypted Password } and a logout button
    ```

-  `controller`
    ```
        - consist of 1 POST for registration/validation operations and 3 GET methods for { login , profile, register } pages
        - @injects userService
    ```
-  `service`
    ```
        - consist of 3 methods { addUser, userExists, findUser }
        - @injected in mainController
        - @injects userDao
        - calls the function provided by userDao
    ```
-  `dao`
    ```
        - consist of 3 methods {addUser, userExists, findUser }
        - @injected in userService
        - holds the userMap object, registered users are stored in this map
        - user userExists and findUser methods filters the map with java 8 features
    ```
-  `validator`
    ```
        validation for registration form
        - checks userName { size > 3 }
        - checks password { size > 7, 1 Digit, 1 Uppercase, 1 Lowercase}
        - checks email { asd@123.com }
        - checks confirm Password { password == confirmPassword }
        - check user if exists 
    ```
-  `configuration`
    ```
        consist of 2 classes for spring mvc configuration and initialization
        also consist of 2 class for spring security configuration and initialization
        both configuration are made with java , not xml
    ```
-  `test`
    ```
        testing with 3 different scenarios
        - test if adding User works
        - test if adding existing User throws exception
        - test if input validations work
    ```

-  `resources`
    ```
        contains validation explanations for error messages in file :
        - validation.properties
    ```

