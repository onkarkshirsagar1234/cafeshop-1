package com.cshop.login.loginservice;

import org.springframework.stereotype.Service;

import com.cshop.cs_helper.bean.ServiceResponse;


@Service
public interface LoginService {

	ServiceResponse loginUser(String inputString, String requestID);


}
