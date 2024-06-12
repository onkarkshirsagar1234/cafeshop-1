package com.cshop.cafeservice.service;

import org.springframework.stereotype.Service;

import com.cshop.cshophelper.bean.ServiceResponse;

@Service
public interface CafeServiceService {

	public ServiceResponse createCafeShop(String json, String requestID);

}
