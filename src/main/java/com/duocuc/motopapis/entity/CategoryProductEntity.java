package com.duocuc.motopapis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "category_product")
public class CategoryProductEntity {
  @Id
  @ColumnDefault("nextval('category_product_id_seq'")
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 250)
  @NotNull
  @Column(name = "name", nullable = false, length = 250)
  private String name;

  @Size(max = 1000)
  @NotNull
  @Column(name = "description", nullable = false, length = 1000)
  private String description;
}
