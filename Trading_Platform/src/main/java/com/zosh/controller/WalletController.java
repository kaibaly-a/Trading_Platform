package com.zosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.modal.User;
import com.zosh.modal.Wallet;
import com.zosh.service.UserService;
import com.zosh.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/api/wallet")
	public ResponseEntity<Wallet> getUserWallet(@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Wallet wallet = walletService.getUserWallet(user);
		
		return new ResponseEntity<>(wallet,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<Wallet> walletToWalletTransfer(@RequestHeader("Authorization") String jwt , @PathVariable Long walletId){
		
		
		return null;
	}
}
