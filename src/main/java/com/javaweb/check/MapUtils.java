package com.javaweb.check;

import java.util.Map;

public class MapUtils {
	public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass) {
		Object obj = params.getOrDefault(key, null);
		if (obj != null) {
			if (tClass.getTypeName().equals("java.lang.long")) {
				if (obj != "") {
					obj = Long.valueOf(obj.toString());
				} else {
					obj = null;
				}

			} else if (tClass.getTypeName().equals("java.lang.Integer")) {
				if (obj != "") {
					obj = Integer.valueOf(obj.toString());
			
				} else {
					obj = null;
				}

			}
			if (tClass.getTypeName().equals("java.lang.String")) {
				if (obj != "") {
					obj = obj.toString();
					x = 3;
				} else {
					obj = null;
				}
				

			}
			return tClass.cast(obj);

		}
		return null;
	}
}
