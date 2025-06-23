package com.zosh.service;

import com.zosh.domain.VerificationType;
import com.zosh.modal.User;
import com.zosh.modal.VerificationCode;

public interface VerificationCodeService {
	VerificationCode sendVerificationCode(User user,VerificationType verificationType);
	
	VerificationCode getVerificationCodeById(Long id) throws Exception;
	
	VerificationCode getVerificationCodeByUser(Long userId);
	
	void deleteVerificationById(VerificationCode verificationCode);
}
