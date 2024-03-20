package com.example.pencraft.service;

import com.example.pencraft.domain.Lot;
import com.example.pencraft.domain.Product;
import com.example.pencraft.form.PassFailProductForm;
import com.example.pencraft.form.ProductForm;
import com.example.pencraft.repository.LotRepository;
import com.example.pencraft.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final LotRepository lotRepository;
    
    public List<Product> findAll() {
        return productRepository.findAll_products();
    }

    public Page<Product> findAllPage(int no, int size, String criteria) {
        Pageable pageable = PageRequest.of(no, size, Sort.by(Sort.Direction.DESC, criteria));
        return productRepository.findAll(pageable);
    }

    public Page<Product> findAllPageForError(int no, int size) {
        Pageable pageable = PageRequest.of(no, size, Sort.by(Sort.Direction.DESC, "product_id"));
        return productRepository.findErrorProductAll(pageable);
    }

    public Product findById(Long id) {
        return productRepository.findByProduct_id(id);
    }


    public void save(Product product) {
        log.info("받아온 product_id : " + product.getProductId());
        log.info("받아온 product_commnet : " + product.getComment());
        productRepository.save(product);
    }

    public List<ProductForm> entityToDto(List<Product> content) {
        List<ProductForm> formList = new ArrayList<>();
        for(Product p : content){
            formList.add(ProductForm.toDto(p));
        }
        return formList;
    }


    public PassFailProductForm findProductHasErrors(Long lotId) {
        Lot byLotId = lotRepository.findByLot_id(lotId);

        int error100Count = productRepository.findError100(lotId);
        int error200Count = productRepository.findError200(lotId);
        int error300Count = productRepository.findError300(lotId);
        int error400Count = productRepository.findError400(lotId);

        int output = byLotId.getOutput();

        PassFailProductForm pfForm = new PassFailProductForm();

        pfForm.setFirst_f_count(error100Count);
        int firsts = output-error100Count;
        pfForm.setFirst_s_count(firsts);

        pfForm.setSecond_f_count(error200Count);
        int seconds = firsts-error200Count;
        pfForm.setSecond_s_count(seconds);

        pfForm.setThird_f_count(error300Count);
        int thirds = seconds-error300Count;
        pfForm.setThird_s_count(thirds);

        pfForm.setFourth_f_count(error400Count);
        int fourths = thirds-error400Count;
        pfForm.setFourth_s_count(fourths);

        return pfForm;
    }


}
