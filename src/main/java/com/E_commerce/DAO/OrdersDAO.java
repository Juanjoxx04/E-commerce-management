package com.E_commerce.DAO;

import java.util.List;

import com.E_commerce.model.Orders;

public interface OrdersDAO {

	public List<Orders> getAllOrders();

	public Orders getOrderById(Long id);
	
	public Orders createOrder(Orders order);

	public Orders updateOrders(Orders order);

	public void deleteOrders(Long id);

}
