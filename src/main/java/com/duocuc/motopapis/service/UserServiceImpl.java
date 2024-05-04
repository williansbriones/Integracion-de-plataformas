package com.duocuc.motopapis.service;

import com.duocuc.motopapis.dto.UserDto;
import com.duocuc.motopapis.entity.UserEntity;
import com.duocuc.motopapis.entity.UserTypeEntity;
import com.duocuc.motopapis.exeption.UserException;
import com.duocuc.motopapis.repository.UserRepository;
import com.duocuc.motopapis.repository.UserTypeRepository;
import com.duocuc.motopapis.service.iface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserTypeRepository userTypeRepository;

  @Override
  public UserDto createUser(UserDto userDto) {
    UserTypeEntity userTypeEntity =
        userTypeRepository
            .findById(1L)
            .orElseThrow(
                () -> new UserException("403", HttpStatus.BAD_REQUEST, "User type not found"));
    UserEntity userEntity =
        UserEntity.builder()
            .username(userDto.name())
            .lastName(userDto.lastName())
            .email(userDto.email())
            .password(userDto.password())
            .userType(userTypeEntity)
            .build();
    userRepository.save(userEntity);
    return UserDto.toUserDto(userEntity);
  }

  @Override
  public Optional<UserDto> getUserById(Long id) {
    return this.userRepository.findById(id).map(UserDto::toUserDto);
  }

  @Override
  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream().map(UserDto::toUserDto).toList();
    // return  new ArrayList<UserDto>();
  }

  @Override
  public Boolean existUser(Long id) {
    return userRepository.existsById(id);
  }
}
