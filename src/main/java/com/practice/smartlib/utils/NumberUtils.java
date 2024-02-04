package com.practice.smartlib.utils;

public class NumberUtils {

	public static boolean isEmpty(Number number) {
		if( number == null ) {
			return true;
		}
		
		return number.intValue() == 0;
	}
	
}
