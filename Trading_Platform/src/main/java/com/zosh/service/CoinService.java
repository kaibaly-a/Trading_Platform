package com.zosh.service;

import java.util.List;

import com.zosh.modal.Coin;

public interface CoinService {
	List<Coin> getCoinList(int page);
	
	String getMarketChart(String coinId, int days);
	
	String getCoinDetails(String coinId);
	
	Coin findById(String coinId);
	
	String searchCoin(String keyword);
	
	String getTop50CoinsByMarketCapRank();
	
	String getTradingCoins();
}