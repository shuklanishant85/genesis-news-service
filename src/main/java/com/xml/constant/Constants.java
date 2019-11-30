package com.xml.constant;

public class Constants {

	public static final String HTTP_HOST = "https://newsapi.org/v2";
	public static final int TOTAL_CONNECTIONS = 5;
	public static final int MAX_CONNECTIONS_PER_ROUTE = 4;
	public static final long CONNECTION_TIMEOUT = 3000;
	public static final String CACHE_REGION_NEWS = "news";
	public static final long PERIODIC_DELAY = 1000;
	public static final String NEWS_API_CALL_URI = NewsProperties.get("news.api.uri.list");
	public static final String PROPERTY_FILE_PATH = "resources/config.properties";
	public static final String PIPE = "|";
	
	
}
