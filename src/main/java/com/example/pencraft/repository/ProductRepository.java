package com.example.pencraft.repository;

import com.example.pencraft.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findAll_products();
    @Query(value = "SELECT * FROM product WHERE error_code != 0", nativeQuery = true)
    Page<Product> findErrorProductAll(Pageable pageable);
    @Query(value = "SELECT * FROM product where product_id = :id", nativeQuery = true)
    Product findByProduct_id(@Param("id") Long id);
    @Query(value = "select count(*) from product where lot_id = :id and error_code=1;", nativeQuery = true)
    int findError100(@Param("id") Long id);
    @Query(value = "select count(*) from product where lot_id = :id and error_code=2;", nativeQuery = true)
    int findError200(@Param("id") Long id);
    @Query(value = "select count(*) from product where lot_id = :id and error_code=3;", nativeQuery = true)
    int findError300(@Param("id") Long id);
    @Query(value = "select count(*) from product where lot_id = :id and error_code=4 ;", nativeQuery = true)
    int findError400(@Param("id") Long id);

}


