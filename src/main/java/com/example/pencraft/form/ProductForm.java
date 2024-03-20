package com.example.pencraft.form;

import com.example.pencraft.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {

    private Long product_id;
    private Double volume;
    private Double nib;
    private String assembly_body;
    private String assembly_cap;
    private String acceptance;
    private String comment;

    //에러코드 컬럼 추가(03.05)
    private Long error_code;

    //로트번호를 받위 위한 컬럼 추가
    private LotForm lot;

    public static ProductForm toDto(Product product) {
        return new ProductForm(
                product.getProductId(),
                product.getVolume(),
                product.getNib(),
                product.getAssembly_body(),
                product.getAssembly_cap(),
                product.getAcceptance(),
                product.getComment(),
                product.getError_code(),
                LotForm.toDto(product.getLot())
        );
    }
}
