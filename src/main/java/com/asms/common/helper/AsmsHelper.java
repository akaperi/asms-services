package com.asms.common.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class AsmsHelper {
	
	public static final ResourceBundle getMessageFromBundle(){
		Locale currentLocale = new Locale("en", "US");
		
		ResourceBundle messages = ResourceBundle.getBundle("spring.MessageBundles.ErrorMessages", currentLocale);
		return messages;
		
	}

}
