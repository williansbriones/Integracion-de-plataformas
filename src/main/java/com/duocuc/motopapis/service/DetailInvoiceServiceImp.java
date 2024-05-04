package com.duocuc.motopapis.service;

import com.duocuc.motopapis.dto.ProductDto;
import com.duocuc.motopapis.entity.DetailInvoiceEntity;
import com.duocuc.motopapis.entity.InvoiceEntity;
import com.duocuc.motopapis.entity.ProductEntity;
import com.duocuc.motopapis.repository.DetailInvoiceRepository;
import com.duocuc.motopapis.service.iface.DetailInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailInvoiceServiceImp implements DetailInvoiceService {

  private final DetailInvoiceRepository detailInvoiceRepository;

  @Transactional
  @Override
  public void SaveDetailInvoice(List<ProductDto> products, Long idInvoice) {
      List<DetailInvoiceEntity> listProductEntity = products.stream().map(productDto -> new DetailInvoiceEntity(
            null,
            ProductEntity.builder().id(productDto.id()).build(),
            productDto.count(),
            InvoiceEntity.builder().id(idInvoice).build()
    )).toList();
    detailInvoiceRepository.saveAll(listProductEntity);
  }
}
