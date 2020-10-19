package com.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.Entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Serializable> {
	public UserEntity findByEmail(String email);
	
	public UserEntity findByEmailAndPwd(String email,String pwd);

}
