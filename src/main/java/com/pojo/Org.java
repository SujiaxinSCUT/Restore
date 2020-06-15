package com.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the org database table.
 * 
 */
public class Org implements Serializable {
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



	public Org() {
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

	public Ca addCa(Ca ca) {
		getCas().add(ca);
		ca.setOrg(this);

		return ca;
	}

	public Ca removeCa(Ca ca) {
		getCas().remove(ca);
		ca.setOrg(null);

		return ca;
	}

	public Set<FabricUser> getFabricUsers() {
		return this.fabricUsers;
	}

	public void setFabricUsers(Set<FabricUser> fabricUsers) {
		this.fabricUsers = fabricUsers;
	}

	public FabricUser addFabricUser(FabricUser fabricUser) {
		getFabricUsers().add(fabricUser);
		fabricUser.setOrg(this);

		return fabricUser;
	}

	public FabricUser removeFabricUser(FabricUser fabricUser) {
		getFabricUsers().remove(fabricUser);
		fabricUser.setOrg(null);

		return fabricUser;
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

	public Peer addPeer(Peer peer) {
		getPeers().add(peer);
		peer.setOrg(this);

		return peer;
	}

	public Peer removePeer(Peer peer) {
		getPeers().remove(peer);
		peer.setOrg(null);

		return peer;
	}

}