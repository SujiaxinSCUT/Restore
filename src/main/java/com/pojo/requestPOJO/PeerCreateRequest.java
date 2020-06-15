package com.pojo.requestPOJO;

public class PeerCreateRequest {

    String eventhub_location;
    String eventhub_name;
    String location;
    String name;
    Integer org_id;

    public String getEventhub_location() {
        return eventhub_location;
    }

    public void setEventhub_location(String eventhub_location) {
        this.eventhub_location = eventhub_location;
    }

    public String getEventhub_name() {
        return eventhub_name;
    }

    public void setEventhub_name(String eventhub_name) {
        this.eventhub_name = eventhub_name;
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

    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }
}
