package com.fxml.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.pojo.*;


/**
 * The persistent class for the block database table.
 * 
 */

public class BlockItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String block_hash;
	private int block_number;
	private String data_hash;
	private String previous_hash;
	private int tx_count;
	private Channel channel;
	private Set<Transaction> transactions = new HashSet<>();
	private String channelName;
	private String date;
	private Block block;

	public BlockItem() {
	}
	
	public BlockItem(Block block) {
		this.block = block;
		this.id = block.getId();
		this.block_hash = block.getBlockHash();
		this.block_number = block.getBlockNumber();
		this.data_hash = block.getDataHash();
		this.previous_hash = block.getPreviousHash();
		this.tx_count = block.getTxCount();
		this.channel = block.getChannel();
		this.transactions = block.getTransactions();
		this.channelName = block.getChannel().getName();
		this.date = new SimpleDateFormat("yyyy/MM/dd").format(block.getDate());
	}
	
	

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getBlock_hash() {
		return block_hash;
	}

	public void setBlock_hash(String block_hash) {
		this.block_hash = block_hash;
	}

	public int getBlock_number() {
		return block_number;
	}

	public void setBlock_number(int block_number) {
		this.block_number = block_number;
	}

	public String getData_hash() {
		return data_hash;
	}

	public void setData_hash(String data_hash) {
		this.data_hash = data_hash;
	}

	public String getPrevious_hash() {
		return previous_hash;
	}

	public void setPrevious_hash(String previous_hash) {
		this.previous_hash = previous_hash;
	}

	public int getTx_count() {
		return tx_count;
	}

	public void setTx_count(int tx_count) {
		this.tx_count = tx_count;
	}

	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Channel getChannel() {
		return this.channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
		this.channelName=channel.getName();
	}

	public Set<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}




}