package com.pojo.requestPOJO;

public class OrgCreateRequest {

    Integer league_id;
    String msp_id;
    String name;
    String org_admin;
    String org_domain_name;
    String peer_type;

    public Integer getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Integer league_id) {
        this.league_id = league_id;
    }

    public String getMsp_id() {
        return msp_id;
    }

    public void setMsp_id(String msp_id) {
        this.msp_id = msp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
