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
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findAll_products();

    //Page형식으로 데이터를 받아옴
//    Page<Product> findAll(Pageable pageable);


    //에러코드를 가지고 있는 데이터만 가져오기위한 쿼리문 작성
    @Query(value = "SELECT * FROM product WHERE error_code != 0", nativeQuery = true)
    Page<Product> findErrorProductAll(Pageable pageable);


    //아이디로 찾아올 때 반환현을 Product로 할수 있게 새로 쿼리문을 작성
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


