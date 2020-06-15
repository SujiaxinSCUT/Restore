package com.fxml.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.pojo.*;


/**
 * The persistent class for the org database table.
 * 
 */
public class OrgItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String msp_id;
	private String name;
	private String org_admin;
	private String org_domain_name;
	private String peer_type;
	private Set<Ca> cas = new HashSet<>();
	private Set<FabricUser> fabricUsers = new HashSet<>();
	private League league;
	private Set<Peer> peers = new HashSet<>();


	private String leagueName;
	private int peerCount;
	private int userCount;
	private Org org;



	public OrgItem() {
	}
	
	public OrgItem(Org o) {
		this.org = o;
		this.id = o.getId();
		this.msp_id = o.getMsp_id();
		this.name = o.getName();
		this.org_admin = o.getOrg_admin();
		this.org_domain_name = o.getOrg_domain_name();
		this.peer_type = o.getPeer_type();
		this.cas = o.getCas();
		this.fabricUsers = o.getFabricUsers();
		this.league = o.getLeague();
		this.peers = o.getPeers();
		this.leagueName = o.getLeague().getName();
		this.peerCount = o.getPeers().size();
		this.userCount = o.getFabricUsers().size();
	}
	
	

    public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public void setPeerCount(int peerCount) {
		this.peerCount = peerCount;
	}

	public String getLeagueName() {
		return leagueName;
	}



	public int getPeerCount() {
		return peerCount;
	}



	public int getUserCount() {
		return userCount;
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

	public String getMsp_id() {
		return msp_id;
	}

	public void setMsp_id(String msp_id) {
		this.msp_id = msp_id;
	}

	public String getOrg_admin() {
		return org_admin;
	}

	public void setOrg_admin(String org_admin) {
		this.org_admin = org_admin;
	}

	public String getOrg_domain_name() {
		return org_domain_name;
	}

	public void setOrg_domain_name(String org_domain_name) {
		this.org_domain_name = org_domain_name;
	}

	public String getPeer_type() {
		return peer_type;
	}

	public void setPeer_type(String peer_type) {
		this.peer_type = peer_type;
	}

	public Set<Ca> getCas() {
		return this.cas;
	}

	public void setCas(Set<Ca> cas) {
		this.cas = cas;
	}


	public Set<FabricUser> getFabricUsers() {
		return this.fabricUsers;
	}

	public void setFabricUsers(Set<FabricUser> fabricUsers) {
		this.fabricUsers = fabricUsers;
		this.userCount=fabricUsers.size();
	}


	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Set<Peer> getPeers() {
		return this.peers;
	}

	public void setPeers(Set<Peer> peers) {
		this.peers = peers;
	}


}