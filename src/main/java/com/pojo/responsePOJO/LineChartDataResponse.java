package com.pojo.responsePOJO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LineChartDataResponse<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private Map<T,Integer> contents;

	public LineChartDataResponse() {
		contents = new HashMap<>();
	}
	public Map<T, Integer> getContents() {
		return contents;
	}

	public void setContents(Map<T, Integer> contents) {
		this.contents = contents;
	} 
	
	public void put(T key,Integer value) {
		contents.put(key, value);
	}
}
