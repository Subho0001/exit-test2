package com.nagarro.training.service;

import java.util.List;

import com.nagarro.training.model.Product;


public interface ProductService {
	public List<Product> getAllProducts();
		
	public List<Product> fetchByKeyword(String keyword);

	public Product getProductById(int id) ;

	public Product addProduct(Product product) ;

	public Product updateProduct(Product product) ;
	public void deleteProduct(int id);
	public boolean iscodeAlreadyexists(String code);
}
