package com.duocuc.motopapis.dto;

import com.duocuc.motopapis.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record UserDto(
    Long id,
    @NotNull @Size(min = 1, max = 50) String name,
    @NotNull @Size(min = 1, max = 50) String lastName,
    @NotNull
        @Email(
            message = "Email is not valid",
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        String email,
    @NotNull @Size(min = 1, max = 50) String password,
    Long idUserType,
    List<InvoiceDto> invoices) {

  public static UserDto toUserDto(UserEntity userEntity) {
    return new UserDto(
        userEntity.getId(),
        userEntity.getUsername(),
        userEntity.getLastName(),
        userEntity.getEmail(),
        userEntity.getPassword(),
        userEntity.getUserType().getId(),
        null);
  }
}
