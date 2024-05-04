package com.duocuc.motopapis.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record FlowResponseDto(String url, String token, Integer flowOrder) {}
