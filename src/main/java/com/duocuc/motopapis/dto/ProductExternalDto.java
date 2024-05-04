package com.duocuc.motopapis.dto;

import java.util.ArrayList;

public record ProductExternalDto(
    int id,
    String title,
    int price,
    String description,
    ArrayList<String> images) {}
