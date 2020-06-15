package com.pojo;

import java.io.Serializable;
import java.util.Date;




/**
 * The persistent class for the read set database table.
 * 
 */

public class ReadSet implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String nameSpace;
	private int readSetIndex;
	private String key;
	private int readVersionBlockNum;
	private int readVersionTxNum;
	private String chaincode_version;
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
	public int getReadSetIndex() {
		return readSetIndex;
	}
	public void setReadSetIndex(int readSetIndex) {
		this.readSetIndex = readSetIndex;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getReadVersionBlockNum() {
		return readVersionBlockNum;
	}
	public void setReadVersionBlockNum(int readVersionBlockNum) {
		this.readVersionBlockNum = readVersionBlockNum;
	}
	public int getReadVersionTxNum() {
		return readVersionTxNum;
	}
	public void setReadVersionTxNum(int readVersionTxNum) {
		this.readVersionTxNum = readVersionTxNum;
	}
	public String getChaincode_version() {
		return chaincode_version;
	}
	public void setChaincode_version(String chaincode_version) {
		this.chaincode_version = chaincode_version;
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
