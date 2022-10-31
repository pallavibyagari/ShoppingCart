package com.shoppingcart1.demo.Service;

import com.shoppingcart1.demo.dto.Response;
import com.shoppingcart1.demo.dto.Signin;
import com.shoppingcart1.demo.dto.SigninResponse;
import com.shoppingcart1.demo.dto.Signup;
import com.shoppingcart1.demo.model.User;
import com.shoppingcart1.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public Response signup(Signup signupDto){
        if (Objects.nonNull(userRepo.findByEmail(signupDto.getEmail()))) {
         System.out.println("User with this email already exists");
        }
        String encryptedpassword = signupDto.getPassword();
        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(), encryptedpassword);
        userRepo.save(user);
        Response responseDto = new Response("success", "Account is created");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.
                printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SigninResponse signin(Signin signinDto)  {
        User user = userRepo.findByEmail(signinDto.getEmail());
        if (Objects.isNull(user)) {
            System.out.println("Invalid user");
        }
        try {
            if (!user.getPassword().equals(hashPassword(signinDto.getPassword()))) {
              System.out.println("Wrong Password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SigninResponse("success", "Logged In");
    }

    public Optional<User> findById(Integer userId) {
        return userRepo.findById(userId);
    }

}
