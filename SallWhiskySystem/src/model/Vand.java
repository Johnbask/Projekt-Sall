package model;

import java.io.Serializable;

public class Vand implements Serializable {

    private String kilde;

    public Vand(String kilde) {
        this.kilde = kilde;
    }

    public String getKilde() {
        return kilde;
    }

    @Override
    public String toString() {
        return kilde ;
    }
}
