package com.zosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.config.JwtProvider;
import com.zosh.modal.TwoFactorOTP;
import com.zosh.modal.User;
import com.zosh.repository.UserRepository;
import com.zosh.response.AuthResponse;
import com.zosh.service.CustomeUserDetailsService;
import com.zosh.service.EmailService;
import com.zosh.service.TwoFactorOtpService;
import com.zosh.utils.OtpUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
	private TwoFactorOtpService twoFactorOtpService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception{
		
		User isEmailExist=userRepository.findByEmail(user.getEmail());
		
		if(isEmailExist != null) {
		throw new Exception("email is already used with another account");	
		}
		
		User newUser =new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setEmail(user.getEmail());
		newUser.setFullName(user.getFullName());
		
		User savedUser= userRepository.save(newUser);
		
		Authentication auth= new UsernamePasswordAuthenticationToken(
				user.getEmail(),
				user.getPassword()
				);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String jwt=JwtProvider.generateToken(auth);
		
		User authUser = userRepository.findByEmail(user.getEmail());
		
		if(user.getTwoFactorAuth().isEnabled()) {
			AuthResponse res= new AuthResponse();
			res.setMessage("TwoFactor  auth is enabled");
			res.setTwoFactorAuthEnabled(true);
			String otp = OtpUtils.generateOTP();
			
			TwoFactorOTP oldTwoFactorOTP = twoFactorOtpService.findByUser(authUser.getId());
			
			if( oldTwoFactorOTP != null) {
				twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
			}
			TwoFactorOTP newTwoFactorOTP= twoFactorOtpService.createTwoFactorOtp(authUser, otp, jwt);
			
			
			emailService.sendVerificationOtpEmail(user.getEmail(), otp);
			
			res.setSession(newTwoFactorOTP.getId());
			return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		}
		
		AuthResponse res=new AuthResponse();
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("Register Success");
		
		
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception{
		
		String username=user.getEmail();
		String password=user.getPassword();
		
		Authentication auth= authenticate(username,password);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String jwt=JwtProvider.generateToken(auth);
		
		AuthResponse res=new AuthResponse();
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("Login Success");
		
		
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}


	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customeUserDetailsService.loadUserByUsername(username);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid Username");
		}
		if(!password.equals(userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
	}
	
	@PostMapping("/two-factor/otp/{otp}")
	public ResponseEntity<AuthResponse> verifysignInOtp(
			@PathVariable String otp,
			@RequestParam String id) throws Exception{
		
		TwoFactorOTP twoFactorOTP =twoFactorOtpService.findById(id);
		
		if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP,otp)) {
			AuthResponse res= new AuthResponse();
			res.setMessage("Two Factor Authentication Verified");
			res.setTwoFactorAuthEnabled(true);
			res.setJwt(twoFactorOTP.getJwt());
			
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		throw new  Exception("Invalid OTP");
		
		
	}
}
