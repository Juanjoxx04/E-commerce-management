package com.E_commerce.service;

import java.util.List;

import com.E_commerce.DTO.createOrderRequestDTO;
import com.E_commerce.model.Orders;

public interface OrdersService {

	public List<Orders> getAllOrders();

	public Orders getOrderById(Long id);

	public Orders createOrder(createOrderRequestDTO orderRequest);

	public Orders updateOrders(Orders order);

	public void deleteOrders(Long id);

}
