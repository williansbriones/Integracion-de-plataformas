package com.duocuc.motopapis.controller;

import com.duocuc.motopapis.dto.Divide;
import com.duocuc.motopapis.dto.UserDto;
import com.duocuc.motopapis.exeption.UserException;
import com.duocuc.motopapis.service.iface.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @PostMapping("/create")
  public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
    UserDto user = userService.createUser(userDto);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/get")
  public ResponseEntity<UserDto> getUser(@RequestParam Long id) {
    if (id == null || id == 0) {
      throw new UserException("302", HttpStatus.BAD_REQUEST, "Id is required", LocalDate.now());
    }

    return userService
        .getUserById(id)
        .map(ResponseEntity::ok)
        .orElseThrow(
            () ->
                new UserException(
                    "302", HttpStatus.BAD_REQUEST, "Id is required", LocalDate.now()));
  }


  @GetMapping("/get/all")
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<UserDto> users = userService.getAllUsers();
    if (users.isEmpty()) {
      throw new UserException("303", HttpStatus.NOT_FOUND, "No users found", LocalDate.now());
    }
    return ResponseEntity.ok(users);
  }

  @PostMapping("/divide")
  public ResponseEntity<Divide> divide(@RequestBody Divide divide) {
    Divide result = dividirEnteros(divide);
     //return ResponseEntity.ok(result);
    return ResponseEntity.ok(new Divide(divide.dividendo(), divide.divisor(), result.result()));
  }

  public Divide dividirEnteros(Divide divide) {

    int contador = 0;
    List<Float> integers = new ArrayList<>(Stream.of(divide.result()).toList());

    while (divide.dividendo() > integers.get(contador)) {
      integers.add(divide.divisor() + integers.get(contador));
      contador++;
    }
    float dividendo = divide.dividendo();
    float resultado = (integers.size() - 1);
    if (divide.dividendo() != integers.get(contador)) {
      resultado = (integers.size() - 2);
      dividendo = concatenarfloats(dividendo - integers.get(contador - 1), 0, false);
      Integer resultdecimal = dividirDecimales(new Divide(dividendo, divide.divisor(), 0));
      resultado = concatenarfloats(resultado, resultdecimal, true);
    }
    return new Divide(dividendo, divide.divisor(), resultado);
  }

  public Integer dividirDecimales(Divide divide) {

    int contador = 0;
    List<Float> integers = new ArrayList<>(Stream.of(divide.result()).toList());

    while (divide.dividendo() > integers.get(contador)) {
      integers.add(divide.divisor() + integers.get(contador));
      contador++;
    }
    return integers.size() - 1;
  }

  public float concatenarfloats(Float a, Integer b, boolean isdecimal) {
    String aStr;

    Integer aInt = a.intValue();
    aStr = String.valueOf(aInt);

    if (isdecimal) {
      return Float.parseFloat(aStr + "." + b.toString());
    } else {
      return Float.parseFloat(aStr + b.toString());
    }
  }
}
