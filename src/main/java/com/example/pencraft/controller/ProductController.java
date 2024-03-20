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
    // 품질 관리 컨트롤러
    // 생산된 제품들을 확인할 수 있는 컨트롤러
    private final ProductService productService;

    //에러 내용을 가져올수 있도록 서비스와 연결(03.05)
    private final ErrorService errorService;

    //@GetMapping("/products")
    //public String showProducts(Model model) {
    //    List<Product> products = productService.findAll();
    //    model.addAttribute("products", products);
    //   return "products/showProducts";
    //}


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
//        log.info("수정 전 products : " + products);
        
        //제품을 모두 찾는다.
//        List<Product> products = productService.findAll();
//        log.info("수정 후 products : " + products);

        //에러 테이블의 페이지네이션을 위한 페이지 데이터를 가져온다.
//        Page<Error> page_error = errorService.findAllPage(pageNo, pageSize);
        
        //에러 디테일 데이터를 가져오기위한 서비스
        List<Error> errors = errorService.findAllErrors();

        log.info("에러정보 : " + errors);

        model.addAttribute("errors", errors);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());

        return "products/error_products";
    }

}
