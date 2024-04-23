package com.duocuc.motopapis.repository;

import com.duocuc.motopapis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


}
