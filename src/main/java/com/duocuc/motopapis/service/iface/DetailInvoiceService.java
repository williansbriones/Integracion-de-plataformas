package com.duocuc.motopapis.service.iface;

import com.duocuc.motopapis.dto.ProductDto;

import java.util.List;

public interface DetailInvoiceService {

    void SaveDetailInvoice(List<ProductDto> products, Long idInvoice);

}
