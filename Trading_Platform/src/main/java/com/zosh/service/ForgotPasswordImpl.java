package com.zosh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.domain.VerificationType;
import com.zosh.modal.ForgotPasswordToken;
import com.zosh.modal.User;
import com.zosh.repository.ForgotPasswordRespository;

@Service
public class ForgotPasswordImpl implements ForgotPasswordService{
	
	@Autowired
	ForgotPasswordRespository forgotPasswordRespository;

	@Override
	public ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType,String sendTo) {
		
		ForgotPasswordToken token = new ForgotPasswordToken();
		token.setUser(user);
		token.setSendTo(sendTo);
		token.setVerificationType(verificationType);
		token.setOtp(otp);
		token.setId(id);
		return forgotPasswordRespository.save(token);
	}

	@Override
	public ForgotPasswordToken findById(String id) {
		Optional<ForgotPasswordToken> token= forgotPasswordRespository.findById(id);
		return token.orElse(null);
	}

	@Override
	public ForgotPasswordToken findByUser(Long userId) {
		return forgotPasswordRespository.findByUserId(userId);
	}

	@Override
	public void deleteToken(ForgotPasswordToken token) {
		forgotPasswordRespository.delete(token);
	}
	
}
