package model;

import java.io.Serializable;

public class Vand implements Serializable {

    private String kilde;

    // Links
    private Destillering destillering;
    private Flaske flaske;

    public Vand(String kilde) {
        this.kilde = kilde;
    }

    public String getKilde() {
        return kilde;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    public Flaske getFlaske() {
        return flaske;
    }
}
