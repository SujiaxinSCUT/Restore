package com.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;





/**
 * The persistent class for the RWSet database table.
 * 
 */
public class RWSet implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private Date date;
	private Set<ReadSet> readSet;
	private Set<WriteSet> writeSet;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<ReadSet> getReadSet() {
		return readSet;
	}
	public void setReadSet(Set<ReadSet> readSet) {
		this.readSet = readSet;
	}
	public ReadSet addReadSet(ReadSet readSet) {
		getReadSet().add(readSet);
		readSet.setRwSet(this);
		
		return readSet;
	}
	public Set<WriteSet> getWriteSet() {
		return writeSet;
	}
	public void setWriteSet(Set<WriteSet> writeSet) {
		this.writeSet = writeSet;
	}
	public WriteSet addWriteSet(WriteSet writeSet) {
		getWriteSet().add(writeSet);
		writeSet.setRwSet(this);
		
		return writeSet;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
