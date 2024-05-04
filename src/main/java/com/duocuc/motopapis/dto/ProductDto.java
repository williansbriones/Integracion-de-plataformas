package com.duocuc.motopapis.dto;


public record ProductDto(
        Long id,
        String name,
        String description,
        Long idCategory,
        Integer price,
        Integer count,
        Integer total
) {}
