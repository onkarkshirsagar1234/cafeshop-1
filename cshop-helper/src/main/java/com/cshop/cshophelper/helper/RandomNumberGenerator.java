package com.cshop.cshophelper.helper;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class RandomNumberGenerator {

	private static final int DEF_COUNT = 5;

	public static String generateRandomNumberForReqID() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String timestamp = now.format(dtf);
		long epochSeconds = now.atZone(ZoneId.systemDefault()).toEpochSecond();
		String randomAlphanumeric = UUID.randomUUID().toString().replaceAll("-", "").substring(0, DEF_COUNT);

		return "CS" + epochSeconds + "_" + randomAlphanumeric;
	}
}
