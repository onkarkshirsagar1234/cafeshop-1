package com.cshop.login.bd;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cshop.cs_helper.bean.ServiceResponse;
import com.cshop.login.bean.AppUser;
import com.cshop.login.bean.Cafe;
import com.cshop.login.bean.ServiceRequest;
import com.cshop.login.constant.MessageConstant;
import com.cshop.login.dao.LoginDao;

@Service
public class LoginBDImpl implements LoginBD {

	@Autowired
	LoginDao loginDao;

	@Override
	public ServiceResponse loginUser(ServiceRequest serviceRequest, String requestID) {
		ServiceResponse serviceResponse = new ServiceResponse();
	
		AppUser user = new AppUser();
		Date pwdExpiryDate = null;

		List<AppUser> results = loginDao.getUserData(serviceRequest, requestID);
		serviceResponse.setStatus(ServiceResponse.SUCCESS);
		if (results != null && !results.isEmpty()) {
			user = results.get(0);
			if (user != null) {
				if (user.getPassword().equals(serviceRequest.getFormData().getPassword())) {
//					pwdExpiryDate = new SimpleDateFormat(ServiceConstant.DATE_FORMAT).parse(cafeUser.getPwdExpiry());
//					Date currentDate = Calendar.getInstance().getTime();
//					if(!DateUtils.isSameDay(currentDate, pwdExpiryDate)) {
//						serviceResponse.setStatus(ServiceResponse.FAILURE);
//						serviceResponse.setRequestID(requestID);
//						serviceResponse.setMessage(MessageConstant.PASSWORD_IS_EXPIRY);
					
//					}
					serviceResponse.setMessage(MessageConstant.LOGIN_SUCCESS);
					serviceResponse.setRequestID(requestID);
					serviceResponse.setStatus(ServiceResponse.SUCCESS);
				}
				else {
					serviceResponse.setMessage(MessageConstant.LOGIN_FAIL);
					serviceResponse.setMessage(MessageConstant.PASSWORD_IS_NOT_MATCHED);
					serviceResponse.setRequestID(requestID);
					serviceResponse.setStatus(ServiceResponse.FAILURE);
				}
			}

		} else {
			serviceResponse.setMessage(MessageConstant.USER_IS_NOT_EXITS);
			serviceResponse.setRequestID(requestID);
			serviceResponse.setStatus(ServiceResponse.FAILURE);
		}
		return serviceResponse;
	}

}
