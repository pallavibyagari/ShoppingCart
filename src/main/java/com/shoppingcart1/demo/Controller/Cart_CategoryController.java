package com.shoppingcart1.demo.Controller;

import com.shoppingcart1.demo.Service.Cart_CategoryService;
import com.shoppingcart1.demo.common.ApiResponse;
import com.shoppingcart1.demo.model.Cart_Category;
import com.shoppingcart1.demo.repository.Cart_CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart_category")
public class Cart_CategoryController {

    @Autowired
    Cart_CategoryService cart_categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> saveCategory(@RequestBody Cart_Category cart_category){
        cart_categoryService.createCategory(cart_category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"created a new category"), HttpStatus.CREATED);
    }

    @GetMapping("/List")
    public List<Cart_Category> listOutCategories(){
        return cart_categoryService.listOutCategories();
    }

    @GetMapping("/cart_categoryById/{id}")
    public Cart_Category getCategoryById(@PathVariable int id){
        return cart_categoryService.getCategoryById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        return cart_categoryService.deleteCategory(id);
    }

    @PutMapping("/update")
    public  Cart_Category updateCategory(@RequestBody Cart_Category cart_category){
        return cart_categoryService.updateCategory(cart_category);
    }
}
