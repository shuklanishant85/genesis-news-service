package com.xml.connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xml.data.Repository;
import com.xml.model.News;

/**
 * @author nisshukl0
 *
 */
public class ResponseHandler {
	private static final Log LOGGER = LogFactory.getLog(ResponseHandler.class);

	
	/**
	 * @param response
	 * @throws IOException
	 */
	public void processResponse(HttpResponse response) throws IOException {
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			LOGGER.info("Response Header Key : " + header.getName() 
			      + " ,Value : " + header.getValue());
		}
		
		StatusLine status = response.getStatusLine();
		if (status.getStatusCode() == 200) {
			LOGGER.info("valid response received");
			LOGGER.info("response json: "+EntityUtils.toString(response.getEntity(),StandardCharsets.UTF_8));
			News news = new ObjectMapper().readValue(response.getEntity().getContent(), News.class);
			if (news != null) {
				Repository.getNewsRepository().add(news);
			}else {
				LOGGER.error("Response JSON could not be parsed into News Object");
			}
		}
		EntityUtils.consume(response.getEntity());
	}
	
}
