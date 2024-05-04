package com.duocuc.motopapis.controller;

import com.duocuc.motopapis.dto.PreInvoice;
import com.duocuc.motopapis.service.iface.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

  private final InvoiceService invoiceService;

  @PostMapping("/create")
  public String createInvoice(@RequestBody PreInvoice PreInvoiceLessTotal) {
    PreInvoice preInvoice = PreInvoice.sumTotal(PreInvoiceLessTotal);
    return invoiceService.createInvoice(preInvoice);
  }

  @PostMapping("/confirm")
  public void confirmInvoice(@RequestParam("token") String token) {
    invoiceService.confirmInvoice(token);
  }
}
