package com.insta.application.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insta.application.model.Customer;
import com.insta.application.model.Quote;
import com.insta.application.model.RefererPayments;

@Repository
public interface RefererPaymentRepo  extends JpaRepository<RefererPayments, Long> 
{

	@Query(value="SELECT rp FROM RefererPayments rp WHERE rp.referrerName LIKE %:name%")
    public ArrayList<RefererPayments> findRefPayByName(String name);

}
