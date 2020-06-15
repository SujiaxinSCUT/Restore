package com.pojo;

import java.io.Serializable;
import java.util.Date;



/**
 * The persistent class for the write set database table.
 * 
 */

public class WriteSet implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String nameSpace;
	private int writeSetIndex;
	private String key;
	private Date date;
	private RWSet rwSet;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
    
	public int getWriteSetIndex() {
		return writeSetIndex;
	}
	public void setWriteSetIndex(int writeSetIndex) {
		this.writeSetIndex = writeSetIndex;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public RWSet getRwSet() {
		return rwSet;
	}
	public void setRwSet(RWSet rwSet) {
		this.rwSet = rwSet;
	}

	
	
}
