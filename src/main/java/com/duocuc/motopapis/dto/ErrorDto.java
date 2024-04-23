package com.duocuc.motopapis.dto;


import java.time.LocalDate;

public record ErrorDto(String code, String message, LocalDate Date) {}
