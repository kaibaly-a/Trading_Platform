package com.zosh.service;

import com.zosh.modal.TwoFactorOTP;
import com.zosh.modal.User;

public interface TwoFactorOtpService {
	
	TwoFactorOTP createTwoFactorOtp(User user,String otp, String jwt);
	
	TwoFactorOTP findByUser(Long userId);
	
	TwoFactorOTP findById(String id);
	
	boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOtp, String otp);
	
	void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP);
}
