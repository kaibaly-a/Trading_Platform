package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.ForgotPasswordToken;

public interface ForgotPasswordRespository extends JpaRepository<ForgotPasswordToken, String> {
	ForgotPasswordToken findByUserId(Long userId);
}
