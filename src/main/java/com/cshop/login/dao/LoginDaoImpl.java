package com.cshop.login.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cshop.cs_helper.constants.CommonConstants;
import com.cshop.cs_helper.db.DBHelper;
import com.cshop.cs_helper.db.DatabaseResult;
import com.cshop.cs_helper.helper.LogException;
import com.cshop.login.bean.AppUser;
import com.cshop.login.bean.Cafe;
import com.cshop.login.bean.ServiceRequest;
import com.cshop.login.constant.CommonConstant;
import com.cshop.login.constant.MessageConstant;
import com.cshop.login.constant.ServiceConstant;
import com.cshop.login.constant.ServiceNameConstant;

@Service
public class LoginDaoImpl implements LoginDao {

	public static final Logger LOG = LogManager.getFormatterLogger();

	@Override
	public List<AppUser> getUserData(ServiceRequest serviceRequest, String requestID) {
		LOG.info(CommonConstant.DAO + CommonConstant.DOUBLE_COULEN + CommonConstant.START);
		List<AppUser> result = new ArrayList<AppUser>();
		try {

			if (serviceRequest.getFormData().getEmailID() != null
					&& !serviceRequest.getFormData().getEmailID().isEmpty()) {
				HashMap<String, Object> criteriaMap = new HashMap<String, Object>();
				criteriaMap.put(ServiceConstant.USEREMAILID, serviceRequest.getFormData().getEmailID());
//			Session openSession = DBConfiguration.getSessionFactory().openSession();
				DatabaseResult dataResult = DBHelper.getRecordFromDB(Cafe.class, criteriaMap,
						ServiceNameConstant.CHECK_USER_IS_EXISTS, requestID);
				if (ObjectUtils.isNotEmpty(dataResult)) {
					result.add( (AppUser) dataResult.getList().get(0));
				}
			}
		} catch (Exception e) {
			LOG.info(CommonConstants.DAO + CommonConstants.DOUBLE_COULEN + MessageConstant.USER_IS_NOT_EXITS
					+ requestID);
			LogException.logException(e, requestID);
		}

		return result;
	}

}
