package com.zosh.service;

import com.zosh.modal.Coin;
import com.zosh.modal.User;
import com.zosh.modal.Watchlist;

public interface WatchlistService {
	
	Watchlist findUserWatchlist(Long userId) throws Exception;
	
	Watchlist createWatchlist(User user);
	
	Watchlist findById(Long id) throws Exception;
	
	Coin addCoinToWatchlist(Coin coin,User user) throws Exception;
}
