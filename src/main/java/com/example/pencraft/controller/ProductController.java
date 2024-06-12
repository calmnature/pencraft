package com.example.pencraft.controller;

import com.example.pencraft.domain.Error;
import com.example.pencraft.domain.Product;
import com.example.pencraft.form.ProductForm;
import com.example.pencraft.service.ErrorService;
import com.example.pencraft.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ErrorService errorService;

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public String showProduct_page(Model model, @RequestParam(name = "pageNo",defaultValue = "1")int pageNo, @RequestParam(name = "pageSize",defaultValue = "10")int pageSize) {
        log.info("pageNo = " + pageNo);
        int realPage = pageNo - 1;

        Page<Product> page = productService.findAllPage(realPage, pageSize, "productId");
        List<ProductForm> products = productService.entityToDto(page.getContent());

        model.addAttribute("products", products);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());


        return "products/show_products";
    }


    @PostMapping("/products/commentSave")
    public String commentSave(ProductForm productForm) {
        log.info("받아온 acceptance : " + productForm.getAcceptance());
        log.info("받아온 id : " + productForm.getProduct_id());
        log.info("받아온 comment : " + productForm.getComment());

        Product byId = productService.findById(productForm.getProduct_id());
        byId.setAcceptance(productForm.getAcceptance());

        if (Objects.equals(productForm.getComment(),"")) {
            byId.setComment(null);
        } else {
            byId.setComment(productForm.getComment());
        }
        productService.save(byId);
        return "redirect:/products";
    }

    @GetMapping(value = "/products/errors", produces = MediaType.APPLICATION_JSON_VALUE)
    public String showProduct_errors(Model model, @RequestParam(name = "pageNo",defaultValue = "1")int pageNo, @RequestParam(name = "pageSize",defaultValue = "10")int pageSize) {

        int realPage = pageNo - 1;


        Page<Product> page = productService.findAllPageForError(realPage, pageSize);
        List<ProductForm> products = productService.entityToDto(page.getContent());
        List<Error> errors = errorService.findAllErrors();

        log.info("에러정보 : " + errors);

        model.addAttribute("errors", errors);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());

        return "products/error_products";
    }

}
