package com.shoppingcart1.demo.Controller;

import com.shoppingcart1.demo.Service.Cart_ProductService;
import com.shoppingcart1.demo.model.Cart_Category;
import com.shoppingcart1.demo.model.Cart_Product;
import com.shoppingcart1.demo.repository.Cart_CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart_product")
public class Cart_ProductController {
    @Autowired
    Cart_ProductService cart_productService;

    @Autowired
    Cart_CategoryRepo cart_categoryRepo;

    @PostMapping("/addproduct")
    public String addProduct(@RequestBody Cart_Product cart_product) {
        cart_productService.addProduct(cart_product);
        return "Successfully product added";
    }

    @GetMapping("/listproducts")
    public List<Cart_Product> getAllProducts() {
        return cart_productService.getAllProducts();
    }

    @PutMapping("/update/{productId}")
    public void updateProduct(@PathVariable("productId") int productId, @RequestBody Cart_Product cart_product) {
        Optional<Cart_Category> optionalCartCategory = cart_categoryRepo.findById(cart_product.getCategoryId());
        if (!optionalCartCategory.isPresent()) {
            System.out.println("category type product not available");
        }
        cart_productService.updateProduct(cart_product, productId);
        System.out.println("successfuly present");
    }

    @GetMapping("/cart_productById/{productId}")
    public Cart_Product getProductById(@PathVariable int productId){
        return cart_productService.getProductById(productId);
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteCategory(@PathVariable int productid){
        return cart_productService.deleteProduct(productid);
    }
}