package org.nullteam.hermitage.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.ArrayList;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private BigInteger id;

        private ArrayList<ArrayList<String>> path;

    public ArrayList<ArrayList<String>> getPath() {
        return path;
    }

    public void setPath(ArrayList<ArrayList<String>> path) {
        this.path = path;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
