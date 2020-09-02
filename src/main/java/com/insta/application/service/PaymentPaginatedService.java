package com.insta.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;
import org.springframework.stereotype.Service;

import com.insta.application.model.PaymentInfo;
import com.insta.application.repository.*;

import org.springframework.data.domain.Pageable;
import java.util.*;
@Service
public class PaymentPaginatedService 
{

    private PaymentPaginatedRepo PaymentPaginatedRepo;

    @Autowired
    public PaymentPaginatedService(PaymentPaginatedRepo PaymentPaginatedRepo)
    {
        this.PaymentPaginatedRepo = PaymentPaginatedRepo;
    }

    public Page<PaymentInfo> getPaymentsPaginated(Integer page, Integer size)
    {
    	Pageable pageObj = PageRequest.of(page, size);
        Page<PaymentInfo> payments = PaymentPaginatedRepo.findPaginatedPayments(pageObj);
        return payments;
    }
}
