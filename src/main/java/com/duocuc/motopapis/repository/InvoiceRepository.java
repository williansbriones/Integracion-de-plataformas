package com.duocuc.motopapis.repository;

import com.duocuc.motopapis.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

  Optional<InvoiceEntity> findByToken(String token);
}
