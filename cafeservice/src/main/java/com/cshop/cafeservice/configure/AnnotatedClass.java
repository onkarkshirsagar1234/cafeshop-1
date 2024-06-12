package com.cshop.cafeservice.configure;

import java.util.ArrayList;
import java.util.List;

import com.cshop.cafeservice.bean.CafeAddress;
import com.cshop.cafeservice.bean.Cafe;

public class AnnotatedClass {

	public static List<Class> getAnnotaClasses() {
		List<Class> listOfClasses = new ArrayList<Class>();
		listOfClasses.add(Cafe.class);
		listOfClasses.add(CafeAddress.class);

		return listOfClasses;
	}
}
