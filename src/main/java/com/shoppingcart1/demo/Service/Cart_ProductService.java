package com.shoppingcart1.demo.Service;

import com.shoppingcart1.demo.dto.AddToCartDto;
import com.shoppingcart1.demo.model.Cart_Product;
import com.shoppingcart1.demo.repository.Cart_ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Cart_ProductService {

    @Autowired
    Cart_ProductRepo cart_productRepo;

    public void addProduct(Cart_Product cart_product){
        cart_productRepo.save(cart_product);
    }
    public List<Cart_Product> getAllProducts(){
        return cart_productRepo.findAll();
    }
    public void updateProduct(Cart_Product cart_product,int productid){
        Optional<Cart_Product>optionalCartProduct=cart_productRepo.findById(productid);
        if(!optionalCartProduct.isPresent()){
            System.out.println("product not available");
        }

        Cart_Product existingProduct= optionalCartProduct.get();
        existingProduct.setProductname(cart_product.getProductname());
        existingProduct.setImageURL(cart_product.getImageURL());
        existingProduct.setDescription(cart_product.getDescription());
        existingProduct.setProductquantity(cart_product.getProductquantity());
        existingProduct.setProductprice(cart_product.getProductprice());
        cart_productRepo.save(existingProduct);

    }
    public Cart_Product getProductById(int productid){
        return cart_productRepo.findById(productid).orElse(null);
    }

    public String deleteProduct(int productid){
        cart_productRepo.deleteById(productid);
        return "product removed"+productid;
    }

    public boolean outOfStock(AddToCartDto addToCart){
        Cart_Product cart_product=getProductById(addToCart.getProductId());
        return (cart_product.getProductquantity()==0);
    }
    public boolean checkStockAvailability(AddToCartDto addToCart){
        Cart_Product cart_product = getProductById(addToCart.getProductId());
        return (cart_product.getProductquantity() < addToCart.getQuantity());
    }


    public int getAvailableStock(AddToCartDto addToCart) {
        Cart_Product cart_product = getProductById(addToCart.getProductId());
        return cart_product.getProductquantity();
    }


}
