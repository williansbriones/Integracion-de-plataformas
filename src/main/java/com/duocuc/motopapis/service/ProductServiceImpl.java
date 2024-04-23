package com.duocuc.motopapis.service;

import com.duocuc.motopapis.dto.ProductDto;
import com.duocuc.motopapis.exeption.UserException;
import com.duocuc.motopapis.service.iface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final RestClient restClient = RestClient.create();
    @Override
    public ProductDto getProductById(int id) {
        return restClient.get()
                .uri("https://api.escuelajs.co/api/v1/products/{id}", id)
                .accept(APPLICATION_JSON)
                .exchange((request, response) -> {
                    if (response.getStatusCode().is4xxClientError()) {
                        throw new UserException("error automatico si encuentra un error 400", HttpStatus.BAD_REQUEST, "Error en el servidor");
                    }
                    return convertResponse(response);
                });

    }

    private ProductDto convertResponse(RestClient.RequestHeadersSpec.ConvertibleClientHttpResponse response) {
        return response.bodyTo(ProductDto.class);
    }
}
