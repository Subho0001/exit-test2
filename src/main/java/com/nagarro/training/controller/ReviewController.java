package com.nagarro.training.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.training.model.Review;
import com.nagarro.training.service.ProductService;
import com.nagarro.training.service.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService productService;
	
	private final String KEY = "Exit_Test_Subhradip";
	
	@GetMapping
	public ResponseEntity<List<Review>> getAllApprovedReviews(@RequestHeader("API_KEY")String API_KEY){
		if(API_KEY.equalsIgnoreCase(KEY)) {
		List<Review> reviews= reviewService.getAllReviews();
		List<Review> ans = new ArrayList<Review>();
		for(Review it:reviews) {
			if(it.isApproval()==true && it.isDisable()==false) {
				ans.add(it);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(ans);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Review> addReview(@RequestBody Review review,@RequestHeader("API_KEY")String API_KEY) {
		if(API_KEY.equalsIgnoreCase(KEY)) {
		if(productService.iscodeAlreadyexists(review.getCode())) {
			return ResponseEntity.status(HttpStatus.OK).body(reviewService.addReview(review));
		}
		return ResponseEntity.ok(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Review> approveReview(@PathVariable int id,@RequestHeader("API_KEY")String API_KEY) {
		if(API_KEY.equalsIgnoreCase(KEY)) {
		return ResponseEntity.status(HttpStatus.OK).body( reviewService.updateReview(reviewService.getReviewById(id)));
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	
	@PutMapping("/notApprove/{id}")
	public ResponseEntity<Review> disableReview(@PathVariable int id,@RequestHeader("API_KEY")String API_KEY) {
		if(API_KEY.equalsIgnoreCase(KEY)) {
			return ResponseEntity.status(HttpStatus.OK).body( reviewService.disableReview(reviewService.getReviewById(id)));
			}
			else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
	}
	
	@PutMapping("/deApprove/{id}")
	public ResponseEntity<Review> deApproveReview(@PathVariable int id,@RequestHeader("API_KEY")String API_KEY) {
		if(API_KEY.equalsIgnoreCase(KEY)) {
		return ResponseEntity.status(HttpStatus.OK).body(reviewService.deApproveReview(reviewService.getReviewById(id))) ;
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	
	  @DeleteMapping("/{id}") 
	  public void deleteReview(@PathVariable int id) {
		  reviewService.deleteReview(id);
	  }
	  
	  @GetMapping("/code/{code}")
	  public ResponseEntity<List<Review>> findBycode(@PathVariable String code,@RequestHeader("API_KEY")String API_KEY){
		  
		  if(API_KEY.equalsIgnoreCase(KEY)) {
		  List<Review> review =reviewService.findByCode(code);
		  List<Review> ans = new ArrayList<Review>();
		  for(Review it : review) {
			  if(it.isApproval()==true) {
				  ans.add(it);
			  }
		  }
		  return ResponseEntity.status(HttpStatus.OK).body(ans);
		  }else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	  }
	 
		
	  
	  @GetMapping("/notApproved")
	  public ResponseEntity<List<Review>> getAllUnapprovedReviews(@RequestHeader("API_KEY")String API_KEY){
		  if(API_KEY.equalsIgnoreCase(KEY)) {
		  List<Review> list=reviewService.getAllReviews();
		  List<Review> notApprovedList=new ArrayList<Review>();
		  for(Review it:list) {
			  if(it.isApproval()==false && it.isDisable()==false) {
				  notApprovedList.add(it);
			  }
		  }
		  return ResponseEntity.status(HttpStatus.OK).body(notApprovedList);
		  }
		  else {
			  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		  }
	  }
	  
	
	
}
