package com.shoppingcart1.demo.Controller;

import com.shoppingcart1.demo.Service.CartService;
import com.shoppingcart1.demo.Service.Cart_ProductService;
import com.shoppingcart1.demo.Service.UserService;
import com.shoppingcart1.demo.common.ApiResponse;
import com.shoppingcart1.demo.dto.AddToCartDto;
import com.shoppingcart1.demo.dto.CartDto;
import com.shoppingcart1.demo.dto.CartItemDto;
import com.shoppingcart1.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class Cart_Controller {
    @Autowired
    private CartService cartService;

    @Autowired
    private Cart_ProductService cart_productService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto) {
        Optional<User> optionalUser = userService.findById(addToCartDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Invalid user id : " + addToCartDto.getUserId()), HttpStatus.CONFLICT);
        }
        if (cart_productService.outOfStock(addToCartDto)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "This product is out of stock"), HttpStatus.CONFLICT);
        }
        if (cart_productService.checkStockAvailability(addToCartDto)) {
            int availableStock = cart_productService.getAvailableStock(addToCartDto);
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Only " + availableStock + " available in stock"), HttpStatus.CONFLICT);
        }
        User user = optionalUser.get();
        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/List")
    public ResponseEntity<CartDto>getCartItem(@RequestParam("userId") int userId){
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
           System.out.println("User id is invalid");
        }
        User user = optionalUser.get();
        CartDto cartDto = cartService.listCartItems(user);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemId,
                                                      @RequestParam("userId") int userId) {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            System.out.println("User id is invalid");
        }
        User user = optionalUser.get();
        cartService.deleteCartItem(itemId, user);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item is deleted from cart"), HttpStatus.OK);
    }






}
