package com.duocuc.motopapis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "price")
public class PriceEntity {
  @Id
  @ColumnDefault("nextval('price_id_seq'")
  @Column(name = "id", nullable = false)
  private Long id;

  @NotNull
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @ColumnDefault("nextval('price_product_id_seq'")
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity product;

  @NotNull
  @Column(name = "price", nullable = false)
  private Integer price;
}
