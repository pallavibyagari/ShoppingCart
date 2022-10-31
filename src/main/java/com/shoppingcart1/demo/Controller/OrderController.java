package com.shoppingcart1.demo.Controller;

import com.shoppingcart1.demo.Service.OrderService;
import com.shoppingcart1.demo.Service.UserService;
import com.shoppingcart1.demo.common.ApiResponse;
import com.shoppingcart1.demo.model.Order;
import com.shoppingcart1.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private  UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("userId") int userId){

        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
           System.out.println("User id is invalid");
        }
        User user = optionalUser.get();
        orderService.placeOrder(user);

        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    @GetMapping("/getOrderItems")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("userId") int userId){
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            System.out.println("User id is invalid");
        }
        User user = optionalUser.get();
        List<Order> orderDtoList = orderService.listOrders(user);

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }
}
