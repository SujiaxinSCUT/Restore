package com.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the chaincode database table.
 * 
 */
public class Chaincode implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String path;
	private String sourcePath;
	private String version;
	private Set<Channel> channels = new HashSet<>();
	private Set<Peer> peers = new HashSet<>();

	public Chaincode() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSourcePath() {
		return this.sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Set<Channel> getChannels() {
		return this.channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}

	public Set<Peer> getPeers() {
		return this.peers;
	}

	public void setPeers(Set<Peer> peers) {
		this.peers = peers;
	}

}