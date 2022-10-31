package com.shoppingcart1.demo.dto;

import com.shoppingcart1.demo.model.Cart;
import com.shoppingcart1.demo.model.Cart_Product;

public class CartItemDto {

    private Integer id;
    private Integer quantity;
    private Cart_Product cart_product;

    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.setProduct(cart.getProduct());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart_Product getProduct() {
        return cart_product;
    }

    public void setProduct(Cart_Product cart_product) {
        this.cart_product = cart_product;
    }

}
