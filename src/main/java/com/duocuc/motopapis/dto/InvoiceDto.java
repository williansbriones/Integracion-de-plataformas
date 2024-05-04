package com.duocuc.motopapis.dto;

import com.duocuc.motopapis.entity.InvoiceEntity;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.List;

public record InvoiceDto(
    Long id,
    List<ProductDto> productos,
    Timestamp fecha,
    Long estado,
    Integer total,
    Long idCaja,
    UserDto user,
    Integer token) {}
