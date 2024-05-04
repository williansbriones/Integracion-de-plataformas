package com.duocuc.motopapis.controller;

import com.duocuc.motopapis.dto.ProductExternalDto;
import com.duocuc.motopapis.service.iface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ProductExternalDto getProduct(@RequestParam int id) {
        return productService.getProductById(id);
    }




}
