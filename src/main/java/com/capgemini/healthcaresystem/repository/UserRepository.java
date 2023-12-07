package com.capgemini.healthcaresystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.healthcaresystem.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
