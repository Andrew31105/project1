package com.javaweb.check;

public class Check_Null {
	public static boolean check_Null(String value) {
		if(value != null && !value.equals("")) {
			return true;
		}
		return false;
	}
}
