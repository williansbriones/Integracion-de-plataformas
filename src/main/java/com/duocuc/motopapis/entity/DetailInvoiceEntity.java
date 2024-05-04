package com.duocuc.motopapis.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "detail_invoice")
public class DetailInvoiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @JoinColumn(name = "id_product")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private ProductEntity Product;

  @Column(name = "count")
  private Integer count;

  @JoinColumn(name = "id_invoice")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private InvoiceEntity invoiceEntity;

}
