package com.zosh.service;

import java.util.List;

import com.zosh.modal.User;
import com.zosh.modal.Withdrawal;

public interface WithdrawalService {
	
	Withdrawal requestWithdrawal(Long amount,User user);
	
	Withdrawal proceedWithdrawal(Long withdrawalId,boolean accept) throws Exception;
	
	List<Withdrawal> getUsersWithdrawalHistory(User user);
	
	List<Withdrawal> getAllWithdrawalRequest();
}
