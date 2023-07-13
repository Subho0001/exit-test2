package com.nagarro.training.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.training.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
