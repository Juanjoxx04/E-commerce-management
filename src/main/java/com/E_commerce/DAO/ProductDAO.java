package com.E_commerce.DAO;

import java.util.List;

import com.E_commerce.model.Product;

public interface ProductDAO {

	public List<Product> getAllProduct();
	
	public Product getProductsById(Long id);

	public Product saveProduct(Product product);

	public Product updateProduct(Product product);

	public void deleteProduct(Long id);

}
