package com.E_commerce.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.E_commerce.DAO.OrdersDAO;
import com.E_commerce.DAO.ProductDAO;
import com.E_commerce.DTO.createOrderRequestDTO;
import com.E_commerce.model.Orders;
import com.E_commerce.model.Product;

import jakarta.transaction.Transactional;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDAO ordersDAO;

	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<Orders> getAllOrders() {
		return ordersDAO.getAllOrders();
	}

	@Override
	public Orders getOrderById(Long id) {
		return ordersDAO.getOrderById(id);
	}

	@Override
	@Transactional
	public Orders createOrder(createOrderRequestDTO orderRequest) {
		Product product = productDAO.getProductsById(orderRequest.getProductId());

		if (product == null || product.getStock() < orderRequest.getQuantity())
			;
		productDAO.updateProduct(product);

		Orders order = new Orders();
		order.setClient(orderRequest.getClientName());
		order.setProduct(product);
		order.setStock(orderRequest.getQuantity());
		order.setPrice(product.getPrice().multiply(new BigDecimal(orderRequest.getQuantity())));

		return ordersDAO.createOrder(order);

	}

	@Override
	public Orders updateOrders(Orders order) {
		return ordersDAO.updateOrders(order);
	}

	@Override
	public void deleteOrders(Long id) {
		ordersDAO.deleteOrders(id);
	}

}
