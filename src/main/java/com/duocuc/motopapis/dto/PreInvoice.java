package com.duocuc.motopapis.dto;

import com.duocuc.motopapis.exeption.UserException;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record PreInvoice(
    Instant DateShopping, List<ProductDto> products, Long idUser, Integer total) {

  public static PreInvoice sumTotal(PreInvoice preInvoice) {
    if (preInvoice.idUser() == null || preInvoice.idUser() <= 0) {
      throw new UserException("400", HttpStatus.BAD_REQUEST,"idUser is required");
    }
    int total =
        preInvoice.products().stream().mapToInt(product -> product.price() * product.count()).sum();

    return new PreInvoice(Instant.now(), preInvoice.products(), preInvoice.idUser(), total);
  }
}
