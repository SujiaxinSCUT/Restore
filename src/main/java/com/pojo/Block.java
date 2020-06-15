package com.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;




public class Block implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String blockHash;
	private int blockNumber;
	private String dataHash;
	private String previousHash;
	private int txCount;
	private Channel channel;
	private Date date;
	private Set<Transaction> transactions = new HashSet<>();

	public Block() {
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getBlockHash() {
		return this.blockHash;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}



	public int getBlockNumber() {
		return this.blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}



	public String getDataHash() {
		return this.dataHash;
	}

	public void setDataHash(String dataHash) {
		this.dataHash = dataHash;
	}



	public String getPreviousHash() {
		return this.previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}



	public int getTxCount() {
		return this.txCount;
	}

	public void setTxCount(int txCount) {
		this.txCount = txCount;
	}




	public Channel getChannel() {
		return this.channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Set<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setBlock(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setBlock(null);

		return transaction;
	}

}