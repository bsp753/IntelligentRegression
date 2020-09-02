package com.insta.application.data;

import org.springframework.data.domain.Page;

import com.insta.application.model.Quote;

public class FilteredQuoteData {
	

	private Page<Quote> quoteDataList;
	private String filterAttEnum;
	
	public Page<Quote> getQuoteDataList() {
		return quoteDataList;
	}
	public void setQuoteDataList(Page<Quote> quoteDataList) {
		this.quoteDataList = quoteDataList;
	}
	public String getFilterAttEnum() {
		return filterAttEnum;
	}
	public void setFilterAttEnum(String filterAttEnum) {
		this.filterAttEnum = filterAttEnum;
	}
}
