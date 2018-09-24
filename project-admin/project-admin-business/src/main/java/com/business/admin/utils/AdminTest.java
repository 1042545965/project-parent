package com.business.admin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminTest {

public static void main(String[] args) {

String test = "  \r\n" + 
		"\r\n" + 
		"dod oh e w cls tine t te +8\r\n" + 
		"\r\n" + 
		"RR&#%sme 432501199309011514\r\n" + 
		"\r\n" + 
		"";
System.out.println(AdminTest.getVlues(test));
}

public static String getVlues(String values) {
	String str = "\\d{6}((19|20)\\d{2})((0[0-9])|(1[0-2]))(((0|1|2)[0-9])|(3[0,1]))\\d{3}[xX\\d]";
	Pattern pattern = Pattern.compile(str);
	Matcher matcher = pattern.matcher(values);
	if (matcher.find()) {
		return matcher.group();
	}
	return "";
	}
}