package com.fxml.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.pojo.*;


/**
 * The persistent class for the channel database table.
 * 
 */
public class ChannelItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String objectHex;
	private League league;
	private long block_height;
	private Set<Block> blocks = new HashSet<>();
	private Set<Chaincode> chaincodes = new HashSet<>();
	private Set<Peer> peers = new HashSet<>();

    private String leagueName;
	private int blockCount;
	private int chainCodeCount;
	private int peerCount;
	private Channel channel;

	public ChannelItem() {
	}
	
	public ChannelItem(Channel c) {
		this.channel = c;
		this.id = c.getId();
		this.name = c.getName();
		this.objectHex = c.getObjectHex();
		this.league = c.getLeague();
		this.block_height = c.getBlock_height();
		this.blocks = c.getBlocks();
		this.chaincodes = c.getChaincodes();
		this.peers = c.getPeers();
		this.leagueName = c.getLeague().getName();
		this.blockCount = c.getBlocks().size();
		this.chainCodeCount = c.getChaincodes().size();
		this.peerCount = c.getPeers().size();
	}

	
	
	public int getChainCodeCount() {
		return chainCodeCount;
	}

	public void setChainCodeCount(int chainCodeCount) {
		this.chainCodeCount = chainCodeCount;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public void setPeerCount(int peerCount) {
		this.peerCount = peerCount;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public int getChaincodecount() {
		return chainCodeCount;
	}

	public int getPeerCount() {
		return peerCount;
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
		this.leagueName=league.getName();
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