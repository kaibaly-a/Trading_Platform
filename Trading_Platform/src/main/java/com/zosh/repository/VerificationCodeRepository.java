package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>{
	public VerificationCode findByUserId(Long userId);
}
