package com.duocuc.motopapis.service;

import com.duocuc.motopapis.dto.UserDto;
import com.duocuc.motopapis.entity.UserEntity;
import com.duocuc.motopapis.repository.UserRepository;
import com.duocuc.motopapis.service.iface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.duocuc.motopapis.util.Letters.upperFirstLetter;
import static com.duocuc.motopapis.util.Letters.lowerAllLetter;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDto createUser(UserDto userDto) {
    UserEntity user =
        new UserEntity(
            null,
            upperFirstLetter(userDto.name()),
            upperFirstLetter(userDto.lastName()),
            lowerAllLetter(userDto.email()),
            userDto.password());
    user = this.userRepository.save(user);
    return new UserDto(
        user.getId(), user.getUsername(), user.getLastName(), user.getEmail(), user.getPassword());
  }

  @Override
  public Optional<UserDto> getUserById(Long id) {
    return this.userRepository.findById(id).map(UserDto::toUserDto);
  } // Get the user by id and return the userDto

  @Override
  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream().map(UserDto::toUserDto).toList();
    // return  new ArrayList<UserDto>();
  }
}
