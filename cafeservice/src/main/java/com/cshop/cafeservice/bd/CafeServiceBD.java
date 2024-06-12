package com.cshop.cafeservice.bd;

import org.springframework.stereotype.Service;

import com.cshop.cafeservice.bean.ServiceRequest;
import com.cshop.cshophelper.bean.ServiceResponse;

@Service
public interface CafeServiceBD {

	public ServiceResponse createCafeShop(ServiceResponse<Object> serviceResponse, ServiceRequest serviceRequest,
			String requestID);
}
