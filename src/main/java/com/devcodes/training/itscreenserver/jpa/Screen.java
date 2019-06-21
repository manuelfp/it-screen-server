package com.devcodes.training.itscreenserver.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Screen {

    @Id
    private String id;
    private String topic;
    private Date update;

    public Screen(){

    }

    public Screen(String id, String topic, Date update) {
        this.id = id;
        this.topic = topic;
        this.update = update;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

}
