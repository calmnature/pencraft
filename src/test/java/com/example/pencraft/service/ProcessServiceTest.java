package com.example.pencraft.service;

import com.example.pencraft.domain.Lot;
import com.example.pencraft.domain.Product;
import com.example.pencraft.domain.Standard;
import com.example.pencraft.repository.LotRepository;
import com.example.pencraft.repository.ProductRepository;
import com.example.pencraft.repository.StandardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProcessServiceTest {


    @Autowired
    public ProcessService processService;
    @Autowired
    public LotRepository lotRepository;
    @Autowired
    public ProductRepository productRepository;

    @Test
    public void test1() {
//        processService.startProcess(100);

        List<Product> allProducts = productRepository.findAll_products();
        System.out.println(allProducts.size());

    }



}