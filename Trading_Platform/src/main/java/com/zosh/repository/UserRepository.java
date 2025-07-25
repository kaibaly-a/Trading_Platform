package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
