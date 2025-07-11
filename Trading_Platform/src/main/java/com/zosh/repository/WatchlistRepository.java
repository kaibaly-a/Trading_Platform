package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Watchlist;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long>{
	Watchlist findByUserId(Long userId);
}
