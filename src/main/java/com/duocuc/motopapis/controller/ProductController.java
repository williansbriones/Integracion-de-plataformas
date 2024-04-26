package com.duocuc.motopapis.controller;

import com.duocuc.motopapis.dto.ProductDto;
import com.duocuc.motopapis.service.iface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ProductDto getProduct(@RequestParam int id) {
        return productService.getProductById(id);
    }



}
