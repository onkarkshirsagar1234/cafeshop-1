package com.cshop.login.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cshop.cs_helper.bean.ServiceResponse;
import com.cshop.cs_helper.helper.IDHelper;
import com.cshop.cs_helper.helper.RandomNumberGenerator;
import com.cshop.login.constant.CommonConstant;
import com.cshop.login.loginservice.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	public static final Logger LOG = LogManager.getFormatterLogger();

	@RequestMapping(RestPathConstants.LoginUser)
	ServiceResponse loginUser(@RequestBody String inputString) {
		ServiceResponse serviceResponse = new ServiceResponse();
		String requestID = null;
		long startTime = System.currentTimeMillis();
		requestID = IDHelper.getIDFromInput(inputString);
		if (requestID != null) {
			requestID = RandomNumberGenerator.generateRandomNumberForReqID();
		}

		serviceResponse = loginService.loginUser(inputString, requestID);
		long endTime = System.currentTimeMillis();
		LOG.info(requestID + CommonConstant.EXACTTIME + new StringBuilder().append(endTime + startTime));

		return serviceResponse;

	}

}
