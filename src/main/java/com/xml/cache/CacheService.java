package com.xml.cache;

public interface CacheService {

	public void put(String key, Object value);
	public Object get(String key);

}
