package com.cshop.cafeservice.bd;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cshop.cafeservice.bean.Cafe;
import com.cshop.cafeservice.bean.ServiceRequest;
import com.cshop.cafeservice.constant.ServiceConstants;
import com.cshop.cafeservice.dao.CafeServiceDAO;
import com.cshop.cshophelper.bean.ServiceResponse;
import com.cshop.cshophelper.constants.CommonConstants;
import com.cshop.cshophelper.helper.LogException;

@Service
public class CafeServiceBDImpl implements CafeServiceBD {

	public static final Logger LOG = LogManager.getFormatterLogger();

	@Autowired
	CafeServiceDAO cafeServiceDAO;

	@Override
	public ServiceResponse createCafeShop(ServiceResponse<Object> serviceResponse, ServiceRequest serviceRequest,
			String requestID) {
		final long startTime = System.currentTimeMillis();
		Cafe cafe = null;
		Boolean isError = false;
		Boolean isUpdated = false;
		SimpleDateFormat sdf = new SimpleDateFormat(ServiceConstants.DATE_TIME_FORMAT);
		Date curretDate = new Date();
		String timeStamp = CommonConstants.BLANK;
		try {
			serviceResponse.setRequestID(requestID);
			timeStamp = sdf.format(curretDate);
			if (serviceRequest.getFormData() != null) {
				cafe = serviceRequest.getFormData();
				isUpdated = cafeServiceDAO.createCafeShop(cafe, requestID);

				if (isUpdated) {

					serviceResponse.setTimestamp(timeStamp);
					serviceResponse.setStatus(CommonConstants.SUCCESS);
//					serviceResponse.setError(CommonConstants.BLANK);
					serviceResponse.setMessage(CommonConstants.BLANK);
				} else {
					serviceResponse.setTimestamp(timeStamp);
					serviceResponse.setStatus(CommonConstants.FAIL);
//					serviceResponse.setError(CommonConstants.BLANK);
					serviceResponse.setMessage(ServiceConstants.FAIL_TO_CREATE_SHOP);
				}
			}

		} catch (Exception e) {
			LogException.logException(e, requestID);
		} finally {
			isUpdated = null;
			cafe = null;
			timeStamp = null;
			isError = null;
			curretDate = null;
			sdf = null;
		}
		long endTime = System.currentTimeMillis();
		LOG.info(requestID + CommonConstants.EXEC_TIME + (endTime - startTime));
		return serviceResponse;
	}

}
