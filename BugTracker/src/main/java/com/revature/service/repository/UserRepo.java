package com.revature.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.service.models.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
