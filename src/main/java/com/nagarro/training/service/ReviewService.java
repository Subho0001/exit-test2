package com.nagarro.training.service;

import java.util.List;

import com.nagarro.training.model.Review;




public interface ReviewService {
public List<Review> getAllReviews();
		
	

	public Review getReviewById(int id) ;

	public Review addReview(Review review) ;

	public Review updateReview(Review review) ;
	public void deleteReview(int id);
	public List<Review> findByCode(String code);
	public boolean iscodeAlreadyexists(String code);
	public Review disableReview(Review review);
	public Review deApproveReview(Review review) ;
	public float findAverageRating(String code);

	public int countReviews(String code);
}
