package com.zosh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
	
	List<Withdrawal> findByUserId(Long userId);
}
