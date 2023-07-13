package com.nagarro.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.model.Product;
import com.nagarro.training.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	private final String KEY = "Exit_Test_Subhradip";
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	
	
	@GetMapping("/ByKeyword")
	public ResponseEntity<List<Product>>  getKeyWordProducts(@RequestParam("keyword") String keyword,@RequestHeader("API_KEY")String API_KEY){
		if(API_KEY.equalsIgnoreCase(KEY)) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.fetchByKeyword(keyword)) ;
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Object> addProduct(@RequestBody Product product,@RequestHeader("API_KEY")String API_KEY){
		if(API_KEY.equalsIgnoreCase(KEY)) {
		if (productService.iscodeAlreadyexists(product.getCode())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		Product addedProduct=productService.addProduct(product);
		
		return ResponseEntity.ok(addedProduct);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	
}
