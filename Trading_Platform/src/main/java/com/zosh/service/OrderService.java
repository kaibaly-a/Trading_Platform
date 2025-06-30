package com.zosh.service;

import java.util.List;

import com.zosh.domain.OrderType;
import com.zosh.modal.Coin;
import com.zosh.modal.Order;
import com.zosh.modal.OrderItem;
import com.zosh.modal.User;

public interface OrderService {
	
	Order createOrder(User user, OrderItem orderItem,OrderType orderType);
	
	Order getOrderById(Long orderId);
	
	List<Order> getAllOrdersOfUser(Long userId, OrderType orderType,String assetSymbol);
	
	Order processOrder(Coin coin,double quantity,OrderType orderType,User user);
}
