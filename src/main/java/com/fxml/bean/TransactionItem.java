package com.fxml.bean;

import java.io.Serializable;
import java.util.Date;

import com.pojo.Block;
import com.pojo.Transaction;


/**
 * The persistent class for the transaction database table.
 * 
 */
public class TransactionItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String channel_id;
	private String create_mspid;
	private String creator_cert;
	private Date date;
	private String nonce;
	private String transaction_id;
	private String type;
	private boolean valid;
	private Integer validation_code;
	private Block block;
	private Transaction transaction;

	
	private String blockHash;

	public TransactionItem() {
	}
	
	public TransactionItem(Transaction t) {
		this.transaction = t;
		this.id = t.getId();
		this.channel_id = t.getChannel_id();
		this.create_mspid = t.getCreate_mspid();
		this.creator_cert = t.getCreator_cert();
		this.date = t.getDate();
		this.nonce = t.getNonce();
		this.transaction_id = t.getTransaction_id();
		this.type = t.getType();
		this.valid = t.getValid();
		this.validation_code = t.getValidation_code();
		this.block = t.getBlock();
		this.blockHash = t.getBlock().getBlockHash();
	}
 
	
   
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}

	public String getBlockHash() {
		return blockHash;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNonce() {
		return this.nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public boolean getValid() {
		return this.valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getCreate_mspid() {
		return create_mspid;
	}

	public void setCreate_mspid(String create_mspid) {
		this.create_mspid = create_mspid;
	}

	public String getCreator_cert() {
		return creator_cert;
	}

	public void setCreator_cert(String creator_cert) {
		this.creator_cert = creator_cert;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public boolean isValid() {
		return valid;
	}

	public Integer getValidation_code() {
		return validation_code;
	}

	public void setValidation_code(Integer validation_code) {
		this.validation_code = validation_code;
	}

	public Block getBlock() {
		return this.block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

}