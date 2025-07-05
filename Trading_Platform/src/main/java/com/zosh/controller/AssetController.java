package com.zosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.modal.Asset;
import com.zosh.modal.User;
import com.zosh.repository.UserRepository;
import com.zosh.service.AssetService;
import com.zosh.service.UserService;

@RestController("/api/asset")
public class AssetController {
	
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{assetId}")
	public ResponseEntity<Asset> getUserById(@PathVariable Long assetId) throws Exception{
		Asset asset = assetService.getAssetById(assetId);
		return ResponseEntity.ok().body(asset);
	}
	
	@GetMapping("/coin/{coinId}/user")
	public ResponseEntity<Asset> getAssetByUserIdAndCoinId(@PathVariable String coinId, @RequestHeader("Authorization") String jwt ) throws Exception{
		
		User user=userService.findUserProfileByJwt(jwt);
		Asset asset= assetService.findAssetByUserIdAndCoinId(user.getId(), coinId);
		return ResponseEntity.ok().body(asset);
	}
	
	@GetMapping()
	public ResponseEntity<List<Asset>> getAssetsForUser(@RequestHeader("Authorization")String jwt) throws Exception{
		
		User user=userService.findUserProfileByJwt(jwt);
		List<Asset> assets=assetService.getUserAssets(user.getId());
		return ResponseEntity.ok().body(assets);
	}
}
