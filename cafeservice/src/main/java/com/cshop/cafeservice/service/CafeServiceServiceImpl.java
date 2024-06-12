package com.cshop.cafeservice.service;

import java.lang.reflect.Type;
import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cshop.cafeservice.bd.CafeServiceBD;
import com.cshop.cafeservice.bean.ServiceRequest;
import com.cshop.cshophelper.bean.ServiceResponse;
import com.cshop.cshophelper.constants.CommonConstants;
import com.cshop.cshophelper.helper.JsonStringToObjectConverter;
import com.cshop.cshophelper.helper.LogException;
import com.google.gson.reflect.TypeToken;

@Service
public class CafeServiceServiceImpl implements CafeServiceService {

	public static final Logger LOG = LogManager.getFormatterLogger();

	@Autowired
	CafeServiceBD cafeServiceBD;

	@Override
	public ServiceResponse createCafeShop(String json, String requestID) {

		long startTime = System.currentTimeMillis();
		ServiceResponse<Object> serviceResponse = new ServiceResponse<Object>();
		Type requestType = new TypeToken<ServiceRequest>() {
		}.getType();

		try {

			ServiceRequest serviceRequest = (ServiceRequest) JsonStringToObjectConverter.jsonStringToObject(json,
					ServiceRequest.class, requestType, requestID);
			if (serviceRequest != null) {

				serviceResponse = cafeServiceBD.createCafeShop(serviceResponse, serviceRequest, requestID);
			} else {
				serviceResponse.setRequestID(requestID);
				serviceResponse.setStatus(CommonConstants.FAIL);
				serviceResponse.setTimestamp(String.valueOf(Instant.now().getEpochSecond()));
			}
		} catch (Exception e) {
			LogException.logException(e, requestID);
		}
		long endTime = System.currentTimeMillis();
		LOG.info(requestID + CommonConstants.EXEC_TIME + (endTime - startTime));
		return serviceResponse;
	}

}
