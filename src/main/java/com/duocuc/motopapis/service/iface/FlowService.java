package com.duocuc.motopapis.service.iface;

import com.duocuc.motopapis.dto.FlowResponseDto;
import org.springframework.util.LinkedMultiValueMap;


public interface FlowService {

    FlowResponseDto CreateOrder(int amount, String email);


    LinkedMultiValueMap<String, String> param();

}
