package com.shoppingcart1.demo.Service;

import com.shoppingcart1.demo.model.Cart_Category;
import com.shoppingcart1.demo.repository.Cart_CategoryRepo;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cart_CategoryService {

    @Autowired
    Cart_CategoryRepo cart_categoryRepo;

    public void createCategory(Cart_Category cart_category){
      cart_categoryRepo.save(cart_category);
    }

    //gets all categories
    public List<Cart_Category> listOutCategories(){
        return cart_categoryRepo.findAll();
    }

    public Cart_Category getCategoryById(int id){
        return cart_categoryRepo.findById(id).orElse(null);
    }

    public String deleteCategory(int id){
         cart_categoryRepo.deleteById(id);
        return "category removed"+id;
    }

    public Cart_Category updateCategory(Cart_Category cart_category){
        Cart_Category existingCategory=cart_categoryRepo.findById(cart_category.getId()).orElse(null);
        existingCategory.setId(cart_category.getId());
        existingCategory.setCategory_type(cart_category.getCategory_type());
        existingCategory.setDescription(cart_category.getDescription());
        return cart_categoryRepo.save(existingCategory);

    }
}
