package com.E_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.E_commerce.DTO.createOrderRequestDTO;
import com.E_commerce.model.Orders;
import com.E_commerce.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@GetMapping
	public ResponseEntity<List<Orders>> getAllOrders() {
		List<Orders> orders = ordersService.getAllOrders();
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
		Orders order = ordersService.getOrderById(id);
		if (order != null) {
			return ResponseEntity.ok(order);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Orders> createOrder(@RequestBody createOrderRequestDTO request) {
		try {
			Orders newOrder = ordersService.createOrder(request);
			return ResponseEntity.ok(newOrder);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Orders> updateOrders(@PathVariable Long id, @RequestBody Orders order) {
		try {
			order.setId(id);
			Orders updateOrders = ordersService.updateOrders(order);
			return ResponseEntity.ok(updateOrders);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Orders> deleteOrders(@PathVariable Long id) {
		ordersService.deleteOrders(id);
		return ResponseEntity.noContent().build();
	}

}
