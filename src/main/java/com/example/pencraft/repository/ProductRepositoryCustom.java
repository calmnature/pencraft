package com.example.pencraft.repository;

import com.example.pencraft.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> bulkInsert(List<Product> list);
}
