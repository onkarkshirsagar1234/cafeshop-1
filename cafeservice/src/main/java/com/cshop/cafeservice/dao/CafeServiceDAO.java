package com.cshop.cafeservice.dao;

import org.springframework.stereotype.Service;

import com.cshop.cafeservice.bean.Cafe;

@Service
public interface CafeServiceDAO {

	public Boolean createCafeShop(Cafe cafe, String requestID);

}
