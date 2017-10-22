package org.nullteam.hermitage.models;

import javax.persistence.Id;

public class User {

    private String pushToken;

    private String id;

    private boolean notifyThroughPushes;

    private boolean notifyThroughVk;
    
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private Integer vk;

    public Integer getVk() {
        return vk;
    }

    public void setVk(Integer vk) {
        this.vk = vk;
    }

    public boolean isNotifyThroughVk() {
        return notifyThroughVk;
    }

    public void setNotifyThroughVk(boolean notifyThroughVk) {
        this.notifyThroughVk = notifyThroughVk;
    }

    public boolean isNotifyThroughPushes() {
        return notifyThroughPushes;
    }

    public void setNotifyThroughPushes(boolean notifyThroughPushes) {
        this.notifyThroughPushes = notifyThroughPushes;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}

