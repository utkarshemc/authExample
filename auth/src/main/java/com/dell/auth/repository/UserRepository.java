package com.dell.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dell.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUsername(String Username);

}
