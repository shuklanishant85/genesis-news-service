package com.xml.cache;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xml.constant.Constants;
import com.xml.model.News;

public class CacheServiceImpl implements CacheService{
	
	private static final Log LOGGER = LogFactory.getLog(CacheServiceImpl.class);
	private CacheAccess<String, Object> cache = null;
	
	public CacheServiceImpl() {
		try {
			cache = JCS.getInstance(Constants.CACHE_REGION_NEWS);
		} catch (CacheException e) {
			LOGGER.error("exception occured while initializing cache: " + e.getMessage());
		}
	}
	
	@Override
	public void put(String key, Object value) {
		if (value instanceof News) {
			try {
				cache.put(key, value);
			} catch (Exception e) {
				LOGGER.error("exception occured while initializing cache: " + e.getMessage());
			}
		}
	}
	
	@Override
	public Object get(String key) {
		return cache.get(key);
	}
	
}
