package com.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the channel database table.
 * 
 */
public class Channel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String objectHex;
	private League league;
	private long block_height;
	private Set<Block> blocks = new HashSet<>();
	private Set<Chaincode> chaincodes = new HashSet<>();
	private Set<Peer> peers = new HashSet<>();

	public Channel() {
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

	public String getObjectHex() {
		return objectHex;
	}
	
	public void setObjectHex(String objectHex) {
		this.objectHex = objectHex;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public long getBlock_height() {
		return block_height;
	}

	public void setBlock_height(long block_height) {
		this.block_height = block_height;
	}

	public Set<Block> getBlocks() {
		return this.blocks;
	}

	public void setBlocks(Set<Block> blocks) {
		this.blocks = blocks;
	}

	public Block addBlock(Block block) {
		getBlocks().add(block);
		block.setChannel(this);
		return block;
	}

	public Block removeBlock(Block block) {
		getBlocks().remove(block);

		block.setChannel(null);

		return block;
	}

	public Set<Chaincode> getChaincodes() {
		return this.chaincodes;
	}

	public void setChaincodes(Set<Chaincode> chaincodes) {
		this.chaincodes = chaincodes;
	}

	public Set<Peer> getPeers() {
		return this.peers;
	}

	public void setPeers(Set<Peer> peers) {
		this.peers = peers;
	}

}