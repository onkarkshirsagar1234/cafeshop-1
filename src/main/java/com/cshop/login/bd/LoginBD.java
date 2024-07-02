package com.cshop.login.bd;

import org.springframework.stereotype.Service;

import com.cshop.cs_helper.bean.ServiceResponse;
import com.cshop.login.bean.ServiceRequest;

@Service
public interface LoginBD {

	ServiceResponse loginUser(ServiceRequest serviceRequest, String requestID);

}
