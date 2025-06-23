package com.zosh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zosh.modal.Coin;

@Service
public class CoinServiceImpl implements CoinService {

	@Override
	public List<Coin> getCoinList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMarketChart(String coinId, int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCoinDetails(String coinId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coin findById(String coinId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchCoin(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTop50CoinsByMarketCapRank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTradingCoins() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
