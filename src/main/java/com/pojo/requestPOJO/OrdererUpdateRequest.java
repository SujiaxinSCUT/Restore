package com.pojo.requestPOJO;

public class OrdererUpdateRequest {

    String domain_suffix;
    Integer id;
    String location;
    String name;

    public String getDomain_suffix() {
        return domain_suffix;
    }

    public void setDomain_suffix(String domain_suffix) {
        this.domain_suffix = domain_suffix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
