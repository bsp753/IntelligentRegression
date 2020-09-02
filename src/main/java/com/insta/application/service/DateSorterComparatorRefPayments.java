package com.insta.application.service;

import java.util.Comparator;

import com.insta.application.data.PaymentInfoRefererData;

public class DateSorterComparatorRefPayments implements Comparator<PaymentInfoRefererData>
{
    @Override
    public int compare(PaymentInfoRefererData o1, PaymentInfoRefererData o2) {
        return o2.getPaymentdate().compareTo(o1.getPaymentdate());
    }
}