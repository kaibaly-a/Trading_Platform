package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
	
	Wallet findByUserId(Long useId);
}
