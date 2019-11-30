package com.xml.connection;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.xml.constant.Constants;

/**
 * @author nisshukl0
 *
 */
public class ConnectionManager {

	private static final Log LOGGER = LogFactory.getLog(ConnectionManager.class);

	/**
	 * @param uri
	 * @return
	 */
	public HttpClientConnection getSingleConnection(String uri) {
		try (BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager();) {
			HttpHost host = new HttpHost(uri);
			HttpRoute route = new HttpRoute(host);
			ConnectionRequest newRequest = connectionManager.requestConnection(route, null);
			return newRequest.get(Constants.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
		} catch (IOException | ExecutionException e) {
			LOGGER.error("error occured while creating request: " + e.getMessage());
		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException occured while creating request: " + e.getMessage());
			Thread.currentThread().interrupt();
		} 
		return null;
	}

	/**
	 * @return
	 */
	public PoolingHttpClientConnectionManager getConnectionManager() {
		PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();
		poolingConnectionManager.setMaxTotal(Constants.TOTAL_CONNECTIONS);
		poolingConnectionManager.setDefaultMaxPerRoute(Constants.MAX_CONNECTIONS_PER_ROUTE);
		return poolingConnectionManager;
	}

}
