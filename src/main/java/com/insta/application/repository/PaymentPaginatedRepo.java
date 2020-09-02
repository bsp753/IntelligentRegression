package com.insta.application.repository;

import java.awt.print.Pageable;

import org.jboss.logging.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.insta.application.model.PaymentInfo;

@Repository
public interface PaymentPaginatedRepo extends PagingAndSortingRepository<PaymentInfo, Long>
{
    
    //Page<PaymentInfo> findBySomethingElseId(Pageable pageable);

    @Query("Select p from  PaymentInfo p")
	Page<PaymentInfo> findPaginatedPayments(org.springframework.data.domain.Pageable pageValue);
}
