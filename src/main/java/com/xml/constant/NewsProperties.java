package com.xml.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewsProperties {

	private static final Log LOGGER = LogFactory.getLog(NewsProperties.class); 
	private static NewsProperties instance = null;
	static Properties properties = null;

	private NewsProperties() {
		//do nothing
	}

	private static synchronized NewsProperties getInstance() {
		if (instance == null) {
			synchronized (NewsProperties.class) {
				if (instance == null) {
					instance = new NewsProperties(); 
					properties = new Properties();
					try {
						InputStream in = instance.getClass().getResourceAsStream(Constants.PROPERTY_FILE_PATH);
						properties.load(in);
					} catch (IOException e) {
						LOGGER.error("could not initialize properties : " +e.getMessage());
					}
				}
			}
		}
		return instance;
	}
	
	public static String get(String key) {
		if (properties == null) {
			instance = getInstance();
		}
		return properties.getProperty(key);
	}
	
}
