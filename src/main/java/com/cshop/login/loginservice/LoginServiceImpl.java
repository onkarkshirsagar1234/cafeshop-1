package com.cshop.login.loginservice;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cshop.cs_helper.bean.ServiceResponse;
import com.cshop.cs_helper.helper.JsonStringToObjectConverter;
import com.cshop.login.bd.LoginBD;
import com.cshop.login.bean.ServiceRequest;
import com.google.gson.reflect.TypeToken;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginBD loginBD;

	@Override
	public ServiceResponse loginUser(String inputString, String requestID) {
		ServiceResponse serviceResponse = new ServiceResponse();
		Type requestType = new TypeToken<ServiceRequest>() {
		}.getType();

		ServiceRequest serviceRequest = (ServiceRequest) JsonStringToObjectConverter.jsonStringToObject(inputString, ServiceRequest.class,
				requestType, requestID);
		
		if(serviceRequest != null) {
			serviceResponse =	loginBD.loginUser(serviceRequest,requestID);
		}
		
		return serviceResponse;
	}

}
