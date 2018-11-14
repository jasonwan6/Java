package Util;

import java.util.UUID;

public class Common {
	 public static String getUuid() {
	        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
	    }
}
