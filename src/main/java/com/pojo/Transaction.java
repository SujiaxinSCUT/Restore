package com.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the transaction database table.
 * 
 */
public class Transaction implements Serializable {
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


	public Transaction() {
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