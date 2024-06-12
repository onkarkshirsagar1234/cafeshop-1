package com.cshop.cafeservice.controller;

import javax.ws.rs.core.Context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cshop.cafeservice.service.CafeServiceService;
import com.cshop.cshophelper.bean.ServiceResponse;
import com.cshop.cshophelper.constants.CommonConstants;
import com.cshop.cshophelper.helper.IDHelper;
import com.cshop.cshophelper.helper.RandomNumberGenerator;

@RestController
public class CafeServiceController {

	public static final Logger LOG = LogManager.getFormatterLogger();

	@Autowired
	CafeServiceService cafeServiceService;

	@RequestMapping(RestPathConstants.CREATE_CAFE)
	public ServiceResponse createCafeShop(@RequestBody String json) {
		long startTime = System.currentTimeMillis();

		String requestID = IDHelper.getIDFromInput(json);
		if (requestID != null) {
			requestID = RandomNumberGenerator.generateRandomNumberForReqID();
		}
		@SuppressWarnings("unchecked")
		ServiceResponse<Object> serviceResponse = cafeServiceService.createCafeShop(json, requestID);
		long endTime = System.currentTimeMillis();
		LOG.info(requestID + CommonConstants.EXEC_TIME + (endTime - startTime));
		return serviceResponse;

	}

}
