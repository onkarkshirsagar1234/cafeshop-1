package com.cshop.cafeservice.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.cshop.cafeservice.bean.Cafe;
import com.cshop.cafeservice.configure.DBConfiguration;
import com.cshop.cshophelper.constants.CommonConstants;
import com.cshop.cshophelper.helper.LogException;

@Service
public class CafeServiceDAOImpl implements CafeServiceDAO {

	public static final Logger LOG = LogManager.getFormatterLogger();

	@SuppressWarnings("deprecation")
	@Override
	public Boolean createCafeShop(Cafe cafe, String requestID) {
		final long startTime = System.currentTimeMillis();
		Session session = null;
		Transaction transaction = null;
		boolean isUpdate = false;
		try {
			session = DBConfiguration.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(cafe);
			isUpdate = true;
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			LogException.logException(e, requestID);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		long endTime = System.currentTimeMillis();
		LOG.info(requestID + CommonConstants.EXEC_TIME + (endTime - startTime));
		return isUpdate;
	}

}
