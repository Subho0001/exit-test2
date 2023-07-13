package com.nagarro.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.training.model.Review;
import com.nagarro.training.service.ProductService;
import com.nagarro.training.service.ReviewService;
import com.nagarro.training.service.UserService;

@RestController
@RequestMapping("/stats")
@CrossOrigin
public class Stats {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/users")
	public int getTotalNumberOfUsers(){
		return userService.getAllUsers().size();
	}
	
	@GetMapping("/products")
	public int getTotalProducts() {
		return productService.getAllProducts().size();
	}
	
	@GetMapping("/reviews")
	public int getTotalReviews() {
		List<Review> list=reviewService.getAllReviews();
		  List<Review> ApprovedList=new ArrayList<Review>();
		  for(Review it:list) {
			  if(it.isApproval()==true) {
				  ApprovedList.add(it);
			  }
		  }
		  return ApprovedList.size();	}
	
}
