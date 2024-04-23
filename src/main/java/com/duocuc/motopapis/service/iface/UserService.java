package com.duocuc.motopapis.service.iface;

import com.duocuc.motopapis.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

  UserDto createUser(UserDto userDto);

  Optional<UserDto> getUserById(Long id);

  List<UserDto> getAllUsers();
}
