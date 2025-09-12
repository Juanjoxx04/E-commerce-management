package com.E_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.E_commerce.model.Product;
import com.E_commerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProduct() {
		return productService.getAllProduct();
	}

	@GetMapping("/{id}")
	public Product getProductsById(@PathVariable Long id) {
		return productService.getProductsById(id);
	}

	@PostMapping
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		product.setId(id);
		return productService.updateProduct(product);
	};
	
	@DeleteMapping ("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		 productService.deleteProduct(id);
	}
}