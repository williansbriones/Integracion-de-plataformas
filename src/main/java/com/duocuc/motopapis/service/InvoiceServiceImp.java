package com.duocuc.motopapis.service;

import com.duocuc.motopapis.dto.FlowResponseDto;
import com.duocuc.motopapis.dto.PreInvoice;
import com.duocuc.motopapis.dto.UserDto;
import com.duocuc.motopapis.entity.InvoiceEntity;
import com.duocuc.motopapis.entity.StateInvoiceEntity;
import com.duocuc.motopapis.entity.UserEntity;
import com.duocuc.motopapis.exeption.UserException;
import com.duocuc.motopapis.repository.InvoiceRepository;
import com.duocuc.motopapis.repository.StateInvoiceRepository;
import com.duocuc.motopapis.service.iface.DetailInvoiceService;
import com.duocuc.motopapis.service.iface.FlowService;
import com.duocuc.motopapis.service.iface.InvoiceService;
import com.duocuc.motopapis.service.iface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImp implements InvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final StateInvoiceRepository stateInvoiceRepository;
  private final UserService userService;
  private final FlowService flowService;
  private final DetailInvoiceService detailInvoiceService;

  @Override
  @Transactional
  public String createInvoice(PreInvoice preInvoice) {

    StateInvoiceEntity stateUnPay =
        stateInvoiceRepository
            .findById(1L)
            .orElseThrow(() -> new UserException("403", HttpStatus.BAD_REQUEST, "State not found"));

    UserDto userDto =
        userService
            .getUserById(preInvoice.idUser())
            .orElseThrow(() -> new UserException("403", HttpStatus.BAD_REQUEST, "User not found"));

    FlowResponseDto flowRespose = flowService.CreateOrder(preInvoice.total(), userDto.email());

    InvoiceEntity invoiceEntity =
        InvoiceEntity.builder()
            .id(null)
            .date(preInvoice.DateShopping())
            .store("online")
            .idCashier(new UUID(0, 0).getMostSignificantBits())
            .total(preInvoice.total())
            .user(UserEntity.builder().id(preInvoice.idUser()).build())
            .stateInvoice(stateUnPay)
            .token(flowRespose.token())
            .build();

    invoiceEntity = invoiceRepository.save(invoiceEntity);
    detailInvoiceService.SaveDetailInvoice(preInvoice.products(), invoiceEntity.getId());

    return flowRespose.url() + "?token=" + flowRespose.token();
  }

  @Override
  public void confirmInvoice(String token) {
    InvoiceEntity invoiceEntity =
        invoiceRepository
            .findByToken(token)
            .orElseThrow(
                () -> new UserException("403", HttpStatus.BAD_REQUEST, "Invoice not found"));

    StateInvoiceEntity statePay =
        stateInvoiceRepository
            .findById(2L)
            .orElseThrow(() -> new UserException("403", HttpStatus.BAD_REQUEST, "State not found"));

    invoiceEntity.setStateInvoice(statePay);
    invoiceRepository.save(invoiceEntity);
  }
}
