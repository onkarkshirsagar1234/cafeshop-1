//package com.cshop.cshophelper.db;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import com.cshop.cshophelper.constants.CommonConstants;
//import com.cshop.cshophelper.constants.MessageConstants;
//import com.cshop.cshophelper.helper.LogException;
//
//public class DBHelper {
//
//	private static final Logger LOG = LogManager.getFormatterLogger();
//
//	/**
//	 * This method is used to save or update record to database if record not
//	 * available to database it will save record or update the corresponding record
//	 * 
//	 * @param objectToSave    an object which is save to database
//	 * @param saveDescription an description what is save
//	 * @param requestID       an requestID used to trace service call
//	 * @return String[2] Array of String. index 0-success/fail 1-reason in case fail
//	 */
//	public static DatabaseResult saveOrUpdate(Object objectToSave, String description, String requestID) {
//		long startTime = System.currentTimeMillis();
//		DatabaseResult databaseResult = new DatabaseResult();
//		Transaction transaction = null;
//		Session session = null;
//
//		try {
//			session = HibernateUtil.getSessionFactory().openSession();
//			transaction = session.beginTransaction();
//			if (objectToSave instanceof List<?>) {
//				List<Object> listOfObjects = (List<Object>) objectToSave;
//				for (Object object : listOfObjects) {
//					session.saveOrUpdate(object);
//				}
//			} else {
//				session.save(objectToSave);
//			}
//		} catch (Exception e) {
//			LOG.error(requestID + MessageConstants.DATABASE_EXCEPTION + description);
//			databaseResult.setStatus(CommonConstants.FAIL);
//			databaseResult.setExceptionStr(LogException.logException(e, requestID));
//			if (transaction != null) {
//				transaction.rollback();
//			}
//		} finally {
//			transaction = null;
//			if (session != null)
//				session.close();
//			LOG.debug(requestID + MessageConstants.SESSION_CLOSED_SAVEORUPDATE);
//		}
//
//		long endTime = System.currentTimeMillis();
//		LOG.debug(requestID + CommonConstants.EXEC_TIME + description + CommonConstants.COLON + (endTime - startTime));
//		return databaseResult;
//
//	}
//
//	/**
//	 * This method is used to save or update list of record to database if record
//	 * not available to database it will save record or update the corresponding
//	 * record
//	 * 
//	 * @param objectToSave an list object which is save to database
//	 * @param description  an description what to save
//	 * @param requestID    an requestID used to trace service call
//	 * @return String[2] Array of String. index 0-success/fail 1-reason in case fail
//	 */
//	public static DatabaseResult saveOrUpdateRecord(List<Object> objectToSave, String description, String requestID) {
//
//		long startTime = System.currentTimeMillis();
//		DatabaseResult databaseResult = new DatabaseResult();
//		Transaction transaction = null;
//		Session session = null;
//
//		try {
//			session = HibernateUtilDemo.getSessionFactory(HibernateUtilDemo.WRITE).openSession();
//			transaction = session.beginTransaction();
//			for (Object object : objectToSave) {
//				session.saveOrUpdate(object);
//			}
//			transaction.commit();
//			databaseResult.setStatus(CommonConstants.SUCCESS);
//		} catch (Exception e) {
//			LOG.error(requestID + MessageConstants.DATABASE_EXCEPTION + description);
//			databaseResult.setStatus(CommonConstants.FAIL);
//			databaseResult.setExceptionStr(LogException.logException(e, requestID));
//			if (transaction != null) {
//				transaction.rollback();
//			}
//		} finally {
//			transaction = null;
//			if (session != null)
//				session.close();
//			LOG.debug(requestID + MessageConstants.SESSION_CLOSED_SAVEORUPDATE);
//		}
//
//		long endTime = System.currentTimeMillis();
//		LOG.debug(requestID + CommonConstants.EXEC_TIME + description + CommonConstants.COLON + (endTime - startTime));
//
//		return databaseResult;
//	}
//
//	/**
//	 * This method is used to get records from database
//	 * 
//	 * @param <T>
//	 * 
//	 * @param persistentClass an persistent Class
//	 * @param criteriaMap     an criteria map pass key value pair e.g
//	 *                        "OrderId":"12584". 1=for not equal criteria append !
//	 *                        before key. 2=for pagination pass variables
//	 *                        1.START_INDEX & 2.END_INDEX
//	 * @param projections     an projection object
//	 * @param description     an get description
//	 * @param requestID       an requestID used to trace service call
//	 * @return list of records from database
//	 */
//	@SuppressWarnings("rawtypes")
//	public static DatabaseResult getRecordFromDB(Class persistantClass, HashMap<String, Object> criteriaMap,
//			String description, String requestID) {
//		long startTime = System.currentTimeMillis();
//		DatabaseResult databaseResult = new DatabaseResult();
//		List records = null;
//		Session session = null;
//		Criteria criteria = null;
//		try {
//
//			// as of now we are using single database
//			session = HibernateUtilDemo.getSessionFactory(HibernateUtilDemo.WRITE).openSession();
//			LOG.debug(requestID + MessageConstants.SESSION_CRETAED_FOR_QUERY);
//			criteria = session.createCriteria(persistantClass);
//			for (Entry<String, Object> entry : criteriaMap.entrySet()) {
//
//				if (entry.getKey() != null && entry.getValue() != null) {
//
//					if (entry.getKey().equals(CommonConstants.START_INDEX)) {
//						criteria.setFirstResult(Integer.parseInt(entry.getValue() + CommonConstants.BLANK));
//					} else if (entry.getKey().equals(CommonConstants.END_INDEX)) {
//						criteria.setMaxResults(Integer.parseInt(entry.getValue() + CommonConstants.BLANK));
//					} else if (entry.getKey().startsWith(CommonConstants.RESTRICTION_NOT_IN)) {
//						/* Restriction not equal */
//						criteria.add(Restrictions.ne(entry.getKey().substring(1, entry.getKey().length()),
//								entry.getValue()));
//					} else if (entry.getKey().startsWith(CommonConstants.RESTRICTION_LE)) {
//						/* Restriction less then equals to */
//						criteria.add(Restrictions.le(entry.getKey().substring(1, entry.getKey().length()),
//								entry.getValue()));
//					} else if (entry.getKey().startsWith(CommonConstants.RESTRICTION_GT)) {
//						/* Restriction grater then equals to */
//						criteria.add(Restrictions.ge(entry.getKey().substring(1, entry.getKey().length()),
//								entry.getValue()));
//					} else if (entry.getKey().equals(CommonConstants.ORDER_BY_DESC)) {
//						criteria.addOrder(Order.desc((String) entry.getValue()));
//					} else if (entry.getKey().equals(CommonConstants.ORDER_BY_ASC)) {
//						criteria.addOrder(Order.asc((String) entry.getValue()));
//					} else {
//						/* Restriction equal */
//						criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
//					}
//
//					LOG.debug(requestID + MessageConstants.CRITERIA + criteria.toString());
//					// fetch records from database
//					records = criteria.list();
//					LOG.debug(requestID + MessageConstants.RECORDS_FETCH_FROM_DB);
//
//					if (records != null) {
//						databaseResult.setNoRowGet(records.size());
//						LOG.debug(requestID + MessageConstants.SETTING_NO_OF_ROW_GET);
//					}
//					if (records != null && records.isEmpty()) {
//						records = new ArrayList();
//					}
//
//					databaseResult.setList(records);
//					databaseResult.setStatus(CommonConstants.SUCCESS);
//					LOG.debug(requestID + MessageConstants.RETURNING_DATA_FROM_DB);
//				}
//			}
//		} catch (Exception e) {
//			LOG.error(requestID + MessageConstants.DATABASE_EXCEPTION + description);
//			databaseResult.setStatus(CommonConstants.FAIL);
//			databaseResult.setExceptionStr(LogException.logException(e, requestID));
//		} finally {
//			if (session != null)
//				session.close();
//			LOG.debug(requestID + "session closed getRecordFromDB");
//		}
//
//		long endTime = System.currentTimeMillis();
//		LOG.debug(requestID + CommonConstants.EXEC_TIME + description + CommonConstants.COLON + (endTime - startTime));
//		return databaseResult;
//	}
//
//}
