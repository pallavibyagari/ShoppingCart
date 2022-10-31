package com.shoppingcart1.demo.Service;

import com.shoppingcart1.demo.dto.CartDto;
import com.shoppingcart1.demo.dto.CartItemDto;
import com.shoppingcart1.demo.model.Order;
import com.shoppingcart1.demo.model.OrderItem;
import com.shoppingcart1.demo.model.User;
import com.shoppingcart1.demo.repository.OrderItemRepo;
import com.shoppingcart1.demo.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderItemRepo orderItemsRepo;
    @Autowired
    private CartService cartService;

    public void placeOrder(User user) {
        CartDto cartDto = cartService.listCartItems(user);
        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
        if (cartDto.getTotalCost() == 0) {
            System.out.println("Cart is empty");
        }
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
        newOrder.setUser(user);
        newOrder.setTotalPrice(cartDto.getTotalCost());
        orderRepo.save(newOrder);
        for (CartItemDto cartItemDto : cartItemDtoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemDto.getProduct().getProductprice());
            orderItem.setProduct(cartItemDto.getProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            orderItemsRepo.save(orderItem);
        }
         cartService.deleteUserCart(user);
    }

    public List<Order> listOrders(User user) {
        return orderRepo.findAllByUserOrderByCreatedDateDesc(user);
    }
}