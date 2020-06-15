package com.pojo.requestPOJO;

public class OrdererCreateRequest {

    String domain_suffix;
    Integer league_id;
    String location;
    String name;

    public String getDomain_suffix() {
        return domain_suffix;
    }

    public void setDomain_suffix(String domain_suffix) {
        this.domain_suffix = domain_suffix;
    }

    public Integer getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Integer league_id) {
        this.league_id = league_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
