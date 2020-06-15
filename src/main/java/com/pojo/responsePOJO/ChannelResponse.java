package com.pojo.responsePOJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.pojo.Block;
import com.pojo.Chaincode;
import com.pojo.League;
import com.pojo.Peer;



public class ChannelResponse implements Serializable {

	private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String objectHex;
	private League league;
	private long blockHeight;
    private Set<Block> blocks = new HashSet<>();
	private Set<Chaincode> chaincodes = new HashSet<>();
	private Set<Peer> peers = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public long getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(long blockHeight) {
		this.blockHeight = blockHeight;
	}

	public Set<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(Set<Block> blocks) {
		this.blocks = blocks;
	}

	public Set<Chaincode> getChaincodes() {
		return chaincodes;
	}

	public void setChaincodes(Set<Chaincode> chaincodes) {
		this.chaincodes = chaincodes;
	}

	public Set<Peer> getPeers() {
		return peers;
	}

	public void setPeers(Set<Peer> peers) {
		this.peers = peers;
	}
    
    
}
