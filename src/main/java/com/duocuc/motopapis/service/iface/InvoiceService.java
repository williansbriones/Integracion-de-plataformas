package com.duocuc.motopapis.service.iface;

import com.duocuc.motopapis.dto.PreInvoice;

public interface InvoiceService {

  String createInvoice(PreInvoice preInvoice);

  void confirmInvoice(String token);
}
