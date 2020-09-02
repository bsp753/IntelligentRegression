package com.insta.application.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insta.application.model.Agent;
import com.insta.application.model.Customer;
import com.insta.application.model.Partner;
import com.insta.application.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
	@Query(value="SELECT c FROM User c WHERE c.userName like :userName")
    public ArrayList<User> findUserByUsername(String userName);
	
}
