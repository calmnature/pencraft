package com.example.pencraft.domain;

import com.example.pencraft.form.ProductForm;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(
        name = "product_seq",
        sequenceName = "product_seq",
        initialValue = 1, allocationSize = 1)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_seq")
    private Long productId;

    private Double volume;
    private Double nib;
    private String assembly_body;
    private String assembly_cap;
    private String acceptance;
    private String comment;

    //에러코드 컬럼 추가(03.05)
    private Long error_code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "standard_id")
    private Standard standard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id")
    private Lot lot;


    public static Product toEntity(ProductForm productForm){
        Product product = new Product();
        product.setProductId(productForm.getProduct_id());
        product.setVolume(productForm.getVolume());
        product.setNib(productForm.getNib());
        product.setAssembly_body(productForm.getAssembly_body());
        product.setAssembly_cap(productForm.getAssembly_cap());
        product.setAcceptance(productForm.getAcceptance());
        product.setComment(productForm.getComment());
        product.setError_code(productForm.getError_code());
        product.setLot(Lot.toEntity(productForm.getLot()));
        return product;
    }

}
