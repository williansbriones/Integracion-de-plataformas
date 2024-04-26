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

  @GetMapping("exist/user")
  public ResponseEntity<Boolean> existUser(Long id) {
    Boolean existUser = userService.existUser(id);
    return new ResponseEntity<>(existUser, HttpStatus.OK);
  }
}
