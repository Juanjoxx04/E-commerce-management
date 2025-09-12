package com.E_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.E_commerce.DAO.ProductDAO;
import com.E_commerce.model.Product;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<Product> getAllProduct() {
		return productDAO.getAllProduct();
	}

	@Override
	public Product getProductsById(Long id) {
		return productDAO.getProductsById(id);
	}

	@Override
	public Product saveProduct(Product product) {
		return productDAO.saveProduct(product);
	}

	@Override
	@Transactional
	public Product updateProduct(Product product) {
		return productDAO.updateProduct(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productDAO.deleteProduct(id);
	}
}
