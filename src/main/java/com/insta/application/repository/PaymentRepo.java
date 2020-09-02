package com.insta.application.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.insta.application.model.PaymentInfo;
import com.insta.application.model.Quote;

@Repository
public interface PaymentRepo extends PagingAndSortingRepository<PaymentInfo, Long> 
{

	public List<PaymentInfo> findPaymentInfoByPolicyno(String policyno);
	
	@Query(value="SELECT p FROM PaymentInfo p")
    public ArrayList<PaymentInfo> findQuotesByStatus(String status);
	
	@Query(value="SELECT p FROM PaymentInfo p WHERE p.payment_status=:status")
    public ArrayList<PaymentInfo> findPaymentsByStatus(String status);
	
	@Query(value="SELECT p FROM PaymentInfo p WHERE p.policyno=:policyno")
    public ArrayList<PaymentInfo> findPaymentsByPolicyNo(String policyno);
	
	@Query("Select p from  PaymentInfo p")
	Page<PaymentInfo> findPaginatedPayments(org.springframework.data.domain.Pageable pageValue);

	@Query("Select p from  PaymentInfo p")
	public ArrayList<PaymentInfo> findAllRecords();

	@Query("Select p FROM PaymentInfo p where vehicleid=:vehicleid")
	public List<PaymentInfo> findByVehicleId(Long vehicleid);
}