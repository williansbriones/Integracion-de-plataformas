package com.duocuc.motopapis.dto;

import java.util.ArrayList;

public record ProductDto(int id, String title, int price, String description, Category category, ArrayList<String> images) {}
