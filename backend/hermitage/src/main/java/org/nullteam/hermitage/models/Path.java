package org.nullteam.hermitage.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Path {

    private String id;

    private String description;

    private ArrayList<Integer> path;
    @Id
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public void setPath(ArrayList<Integer> path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}






