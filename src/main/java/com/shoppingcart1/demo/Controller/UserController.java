package com.shoppingcart1.demo.Controller;

import com.shoppingcart1.demo.Service.UserService;
import com.shoppingcart1.demo.dto.Response;
import com.shoppingcart1.demo.dto.Signin;
import com.shoppingcart1.demo.dto.SigninResponse;
import com.shoppingcart1.demo.dto.Signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
        @Autowired
        UserService userService;

        @PostMapping("/signup")
        public Response signup(@RequestBody Signup signupDto)  {
            return userService.signup(signupDto);
        }

        @PostMapping("/signin")
        public SigninResponse signin(@RequestBody Signin signinDto){
            return userService.signin(signinDto);
        }
}
