package com.duocuc.motopapis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class StockEntity {
  @Id
  @ColumnDefault("nextval('stock_id_seq'")
  @Column(name = "id", nullable = false)
  private Long id;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @ColumnDefault("nextval('stock_id_seq'")
  @JoinColumn(name = "id", nullable = false)
  private ProductEntity productEntity;

  @NotNull
  @Column(name = "count", nullable = false)
  private Integer count;
}
