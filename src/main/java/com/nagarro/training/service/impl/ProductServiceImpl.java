package com.nagarro.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.training.model.Product;
import com.nagarro.training.repository.ProductRepository;
import com.nagarro.training.service.ProductService;
import com.nagarro.training.service.ReviewService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ReviewService reviewService;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> optional=productRepository.findById(id);
		return optional.isPresent()?optional.get():null;
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(int id) {
		Optional<Product> optional=productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.delete(optional.get());
		}
	}

	@Override
	public boolean iscodeAlreadyexists(String code) {
		return productRepository.findByCode(code)!=null;
	}
	@Override
    public List<Product> fetchByKeyword(String keyword) {

        List<Product> products = productRepository.findByKeyword(keyword);
        for (Product product : products) {
        	float avg=reviewService.findAverageRating(product.getCode());
        	int numberReviews=reviewService.countReviews(product.getCode());
        	product.setAvgRating(avg);
        	product.setReviews(numberReviews);
            this.addProduct(product);
        }
        return products;
    }
	

}
