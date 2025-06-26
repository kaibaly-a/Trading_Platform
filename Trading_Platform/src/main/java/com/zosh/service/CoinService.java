package com.zosh.service;

import java.util.List;

import com.zosh.modal.Coin;

public interface CoinService {
	List<Coin> getCoinList(int page) throws Exception;
	
	String getMarketChart(String coinId, int days) throws Exception;
	
	String getCoinDetails(String coinId) throws Exception;
	
	Coin findById(String coinId);
	
	String searchCoin(String keyword);
	
	String getTop50CoinsByMarketCapRank();
	
	String getTradingCoins();
}