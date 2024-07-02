package com.cshop.login.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cshop.login.bean.AppUser;
import com.cshop.login.bean.ServiceRequest;

@Service
public interface LoginDao {

	List<AppUser> getUserData(ServiceRequest serviceRequest, String requestID);

}
