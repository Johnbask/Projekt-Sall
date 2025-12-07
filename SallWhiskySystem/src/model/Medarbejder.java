package model;

import java.util.ArrayList;
import java.util.List;

public class Medarbejder implements java.io.Serializable {

    private String navn;
    private String Stilling;



    public Medarbejder(String navn, String stilling) {
        this.navn = navn;
        Stilling = stilling;
    }

    public String getNavn() {
        return navn;
    }

    public String getStilling() {
        return Stilling;
    }

}
