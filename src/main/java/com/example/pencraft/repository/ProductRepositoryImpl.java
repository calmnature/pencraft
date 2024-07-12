package com.example.pencraft.repository;

import com.example.pencraft.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Product> bulkInsert(List<Product> products) {
        if(products.isEmpty()) return new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO product (lot_id, standard_id, error_code, volume, nib, assembly_body, assembly_cap, acceptance) VALUES ");
        for(int i = 0; i < products.size(); i++){
            sb.append("(?,?,?,?,?,?,?,?)");
            if(i < products.size()-1)
                sb.append(",");
        }
        System.out.println("sb = " + sb);
        Query query = entityManager.createNativeQuery(sb.toString());
        for(int i = 0; i < products.size(); i++){
            Product product = products.get(i);
            query.setParameter(i * 8 + 1, product.getLot().getLotId());
            query.setParameter(i * 8 + 2, product.getStandard().getStandard_id());
            query.setParameter(i * 8 + 3, product.getError_code());
            query.setParameter(i * 8 + 4, product.getVolume());
            query.setParameter(i * 8 + 5, product.getNib());
            query.setParameter(i * 8 + 6, product.getAssembly_body());
            query.setParameter(i * 8 + 7, product.getAssembly_cap());
            query.setParameter(i * 8 + 8, product.getAcceptance());
        }
        int tmp = query.executeUpdate();
        System.out.println("query 결과 = " + tmp);

        return products;
    }
}
