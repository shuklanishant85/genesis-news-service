package com.xml.data;

import java.util.ArrayList;
import java.util.List;

import com.xml.model.News;

public class Repository {
	private static List<News> newsStore;

	private Repository() {
		// do nothing
	}

	public static synchronized List<News> getNewsRepository() {

		if (newsStore == null) {
			synchronized (Repository.class) {
				if (newsStore == null) {
					return new ArrayList<>();
				}
			}
		}
		return newsStore;
	}

}
