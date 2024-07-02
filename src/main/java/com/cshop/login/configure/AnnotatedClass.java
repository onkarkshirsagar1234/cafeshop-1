package com.cshop.login.configure;

import java.util.ArrayList;
import java.util.List;

import com.cshop.login.bean.Cafe;
import com.cshop.login.bean.CafeAddress;


public class AnnotatedClass {

	public static List<Class> getAnnotaClasses() {
		List<Class> listOfClasses = new ArrayList<Class>();
		listOfClasses.add(Cafe.class);
		listOfClasses.add(CafeAddress.class);

		return listOfClasses;
	}
}
