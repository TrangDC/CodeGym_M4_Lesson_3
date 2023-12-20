package com.example.productmanagementthymeleaf.controller;

import com.example.productmanagementthymeleaf.model.Product;
import com.example.productmanagementthymeleaf.service.IProductService;
import com.example.productmanagementthymeleaf.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService = new ProductService();

    //hiển thị trang home
    @GetMapping("")
    public String home(Model model) {
        List<Product> productLists = productService.findAll();
        model.addAttribute("products", productLists);
        return "home";
    }

    //sang trang add new product
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "add";
    }

    //thêm product và trả về home
    @PostMapping("/save")
    public String save(Product product) {
        product.setId((int) (Math.random() * 10000));
        productService.save(product);
        return "redirect:/products";
    }

    //sang trang edit product
    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id,
                         Model model) {
        model.addAttribute("product", productService.findById(id));
        return "update";
    }

    //thực hiện edit và trả về home
    @PostMapping("/update")
    public String update(Product product) {
        productService.update(product.getId(), product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id,
                         Model model) {
        model.addAttribute("product", productService.findById(id));
        return "delete";
    }
    @PostMapping("/delete")
    public String delete (Product product) {
        productService.remove(product.getId());
        return "redirect:/products";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "view";
    }
    @GetMapping ("/search")
    public String search(@RequestParam String key, Model model) {
        ArrayList<Product>products= productService.findByKeyword(key);
        model.addAttribute("products", products);
        return "home";
    }
}
