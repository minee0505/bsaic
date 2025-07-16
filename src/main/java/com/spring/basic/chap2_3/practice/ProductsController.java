package com.spring.basic.chap2_3.practice;

import com.spring.basic.chap2_3.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/practice/api/v1")
public class ProductsController {

    // 가상의 메모리 상품 저장소
    private Map<String, Product> productMap = new HashMap<>();

    // 상품의 시리얼 넘버를 순차 생성
    private String Id = "1";


    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") String productId) {
        return "Product ID: " + productMap.get(productId);
    }
}
