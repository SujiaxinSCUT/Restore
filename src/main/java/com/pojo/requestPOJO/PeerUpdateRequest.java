package com.pojo.requestPOJO;

public class PeerUpdateRequest {

    String eventhub_location;
    String eventhub_name;
    Integer id;
    String location;
    String name;

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
