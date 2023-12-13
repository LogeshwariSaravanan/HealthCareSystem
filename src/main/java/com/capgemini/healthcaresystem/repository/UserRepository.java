package com.capgemini.healthcaresystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.healthcaresystem.entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, String>{

	@Transactional
	@Modifying
	@Query("SELECT u.userId FROM User u")
	List<String> FindUserId();
}
