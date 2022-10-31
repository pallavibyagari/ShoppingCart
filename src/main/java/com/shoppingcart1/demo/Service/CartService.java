package com.shoppingcart1.demo.Service;

import com.shoppingcart1.demo.dto.AddToCartDto;
import com.shoppingcart1.demo.dto.CartDto;
import com.shoppingcart1.demo.dto.CartItemDto;
import com.shoppingcart1.demo.model.Cart;
import com.shoppingcart1.demo.model.Cart_Product;
import com.shoppingcart1.demo.model.User;
import com.shoppingcart1.demo.repository.CartRepo;
import com.shoppingcart1.demo.repository.Cart_ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    Cart_ProductService cart_productService;
    @Autowired
    Cart_ProductRepo cart_productRepo;

    public void addToCart(AddToCartDto addToCartDto, User user){
        Cart_Product cart_product= cart_productService.getProductById(addToCartDto.getProductId());
        Cart cart=new Cart();
        cart.setProduct(cart_product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepo.save(cart);
        cart_product.setProductquantity(cart_product.getProductquantity()- addToCartDto.getQuantity());
        cart_productRepo.save(cart_product);
    }
    public CartDto listCartItems(User user){
        final List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getProductprice();
            cartItems.add(cartItemDto);
        }
        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return cartDto;
    }

    public void deleteCartItem(int cartItemId, User user)  {
        Optional<Cart> optionalCart = cartRepo.findById(cartItemId);
        if (optionalCart.isEmpty()) {
            System.out.println("Cart item is invalid : " + cartItemId);
        }
        Cart cart = optionalCart.get();
        if (cart.getUser() != user) {
            System.out.println("Cart item does not belong to user: " + user.getId());
        }
        Cart_Product cart_product = cart_productService.getProductById(cart.getProduct().getProductid());
        cart_product.setProductquantity(cart_product.getProductquantity() + cart.getQuantity());
        cartRepo.delete(cart);
        cart_productRepo.save(cart_product);
    }
    public void deleteUserCart(User user) {
        cartRepo.deleteByUser(user);
    }
}
