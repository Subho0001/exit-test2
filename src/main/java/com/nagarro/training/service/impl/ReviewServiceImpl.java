package com.nagarro.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.training.model.Review;
import com.nagarro.training.repository.ReviewRepository;
import com.nagarro.training.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	@Override
	public Review getReviewById(int id) {
		Optional<Review> optional=reviewRepository.findById(id);
		return optional.isPresent()?optional.get():null;
	}

	@Override
	public Review addReview(Review review) {
		return reviewRepository.save(review);
	}

	@Override
	public Review updateReview(Review review) {
		review.setApproval(true);
		return reviewRepository.save(review);
	}

	@Override
	public void deleteReview(int id) {
		Optional<Review> optional=reviewRepository.findById(id);
		if(optional.isPresent()) {
			reviewRepository.delete(optional.get());
		}
	}
	
	public List<Review> findByCode(String code){
		return reviewRepository.findByCode(code);
	}

	@Override
	public boolean iscodeAlreadyexists(String code) {
		return reviewRepository.findByCode(code) != null;
	}

	@Override
	public Review disableReview(Review review) {
		review.setDisable(true);
		return reviewRepository.save(review);
	}
	
	@Override
	public Review deApproveReview(Review review) {
		review.setApproval(false);
		return reviewRepository.save(review);
	}

	@Override
	public float findAverageRating(String code) {
		List<Review> reviews=this.findByCode(code);
		float avg=0.0F;
		int c=0;
		for(Review it:reviews) {
			if(it.isApproval()==true)
			{
				avg+=it.getRating();
				c++;
			}
			
		}
		
		return c==0? avg: (avg/c);
	}

	@Override
	public int countReviews(String code) {
		List<Review> reviews=this.findByCode(code);
		
		int c=0;
		for(Review it:reviews) {
			if(it.isApproval()==true)
			{
				c++;
			}
		}
		return c;
	}

}
