package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academy.model.Product;
import rikkei.academy.service.product.IProductService;

import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping({"/", "/products"})
    public String showList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/product/list";
    }

    @GetMapping("/create-product")
    public String formCreate(Model model) {
        model.addAttribute("product", new Product());
        return "/product/create";
    }

    @PostMapping("/create-product")
    public String create(Product product, RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Create success!");
        return "redirect:/create-product";
    }

    @GetMapping("/edit-product/{id}")
    public String formEdit(@PathVariable("id") Optional<Product> product, Model model) {
        if (!product.isPresent()) {
            return "/error.404";
        }
        model.addAttribute("product", product.get());
        return "/product/edit";
    }

    @PostMapping("/update")
    public String update(Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete-product/{id}")
    public String formDelete(@PathVariable("id") Optional<Product> product, Model model) {
        if (!product.isPresent()) {
            return "/error.404";
        }
        model.addAttribute("product", product.get());
        return "/product/delete";
    }

    @PostMapping("/remove")
    public String remove(Product product) {
        productService.remove(product.getId());
        return "redirect:/products";
    }
}
