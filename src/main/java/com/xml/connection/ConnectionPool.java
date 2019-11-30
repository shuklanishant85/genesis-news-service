package com.xml.connection;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author nisshukl0
 *
 */
public class ConnectionPool {
	
	CloseableHttpClient client;
	HttpGet httpGet;
	
	/**
	 * 
	 */
	public ConnectionPool() {
		ConnectionManager manager = new ConnectionManager();
		client = HttpClients.custom().setConnectionManager(manager.getConnectionManager()).build();
	}
	
	/**
	 * @param uri
	 */
	public void executeRequest(String uri) {
		httpGet = new HttpGet(uri);
		new HttpClientConnectionExecutor(httpGet, client).start();
	}
	
}
