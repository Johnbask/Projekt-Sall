package model;

import java.util.ArrayList;
import java.util.List;

public class Medarbejder {

    private String navn;
    private String Stilling;

    // link attributter
    private List<Omhældning> omhældningList = new ArrayList<>();
    private List<Destillering> destilleringlist = new ArrayList<>();

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

    public List<Destillering> getDestilleringlist() {
        return destilleringlist;
    }

    public List<Omhældning> getOmhældningList() {
        return omhældningList;
    }
}
