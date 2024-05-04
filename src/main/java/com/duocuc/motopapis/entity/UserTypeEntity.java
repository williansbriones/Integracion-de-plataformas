package com.duocuc.motopapis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "user_type")
@AllArgsConstructor
public class UserTypeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "userType")
  private List<UserEntity> users;
}
