package com.xml.schedular;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

import com.xml.connection.ConnectionPool;
import com.xml.constant.Constants;

public class Cron extends TimerTask{
	
	private static final List<String> requestStack;
	
	static {
		requestStack = new ArrayList<String>();
		List<String> uriList = Arrays.asList(StringUtils.split(Constants.NEWS_API_CALL_URI, Constants.PIPE));
		if (null != uriList) {
			requestStack.addAll(uriList);
		}
	}

	@Override
	public void run() {
		ConnectionPool repositoryRefreshPool = new ConnectionPool();
		for (String uri : requestStack) {
			repositoryRefreshPool.executeRequest(uri);
		}
	}
	
	public void startCron() {
		Timer timer = new Timer();
		timer.schedule(this, Constants.PERIODIC_DELAY);
	}
	

}
