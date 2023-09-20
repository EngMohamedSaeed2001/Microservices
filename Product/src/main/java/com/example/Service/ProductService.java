package com.example.Service;

import com.example.DB.Product;
import com.example.DB.Request.ProductRequest;
import com.example.DB.Response.ProductResponse;
import com.example.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepo.save(product);
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepo.findAll();

        return products.stream().map(this::mapToProduct).toList();
    }

    public ProductResponse mapToProduct(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
