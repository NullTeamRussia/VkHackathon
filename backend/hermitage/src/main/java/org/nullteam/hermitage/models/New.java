package org.nullteam.hermitage.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class New {

    private String marker;

    private String id;

    @Id
    public String getId() {
        return id;
    }

    private String title;

    private String pathLink;

    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathLink() {
        return pathLink;
    }

    public void setPathLink(String pathLink) {
        this.pathLink = pathLink;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
}
