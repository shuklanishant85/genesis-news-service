package com.xml.connection;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author nisshukl0
 *
 */
public class HttpClientConnectionExecutor extends Thread{
	private static final Log LOGGER = LogFactory.getLog(HttpClientConnectionExecutor.class);
	private CloseableHttpClient client;
	private HttpGet get;
	
	/**
	 * @param get
	 * @param client
	 */
	public HttpClientConnectionExecutor(HttpGet get, CloseableHttpClient client) {
		this.get = get;
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			HttpResponse response = client.execute(get);
			new ResponseHandler().processResponse(response);
		} catch (IOException e) {
			LOGGER.error("exception occured while execution : "+e.getMessage());
		}
	}

}
