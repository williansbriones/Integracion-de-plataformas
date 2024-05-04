package com.duocuc.motopapis.repository;

import com.duocuc.motopapis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query("select u from user u where u.userType.id = ?1 and u.username = ?2")
  Optional<UserEntity> testJPQL(Long idUsertype, String name);

  @Query(value = "select * from users u where u.id = ?", nativeQuery = true)
  List<UserEntity> testNativeQuery(long id);

  @Query(
      "select COUNT(u) from user u where upper(u.username) = upper(?1) or upper(u.email) = upper(?2)")
  Long checkRepeated(String username, String email);
}
