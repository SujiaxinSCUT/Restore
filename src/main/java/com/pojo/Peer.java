package com.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the peer database table.
 * 
 */
public class Peer implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String eventhub_location;
	private String eventhub_name;
	private String location;
	private String name;
	private Set<Chaincode> chaincodes = new HashSet<>();
	private Set<Channel> channels = new HashSet<>();
	private Org org;

	
	

	public Peer() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventhub_location() {
		return eventhub_location;
	}

	public void setEventhub_location(String eventhub_location) {
		this.eventhub_location = eventhub_location;
	}

	public String getEventhub_name() {
		return eventhub_name;
	}

	public void setEventhub_name(String eventhub_name) {
		this.eventhub_name = eventhub_name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Chaincode> getChaincodes() {
		return this.chaincodes;
	}

	public void setChaincodes(Set<Chaincode> chaincodes) {
		this.chaincodes = chaincodes;
	}

	public Set<Channel> getChannels() {
		return this.channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}

	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

}