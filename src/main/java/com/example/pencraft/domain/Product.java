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
    private Long error_code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "standard_id")
    private Standard standard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id")
    private Lot lot;
}
