package org.nullteam.hermitage.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coordinate {
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String id;
    private Integer x;
    private Integer y;

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }
}
