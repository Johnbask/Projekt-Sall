package model;

import java.time.LocalDate;
import java.util.List;

public class Destillering implements java.io.Serializable {

    private int newMakeId;
    public static int idMaker = 1;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double mængdeProduceret;
    private double alkoholProcent;
    private String råvare;
    private String røg;
    private String kommentar;

    // link attributter
    private List<Råvare> råvareList;
    private  Vand vand;
    private Medarbejder medarbejder;

    //tror den her forbindelse ikke skal være her men better safe than sorry
    private  List<Destilat> destilatList;

    public Destillering(LocalDate startDato, LocalDate slutDato, double mængdeProduceret, double alkoholProcent, Medarbejder medarbejder,String råvare, String røg,String kommentar,Vand vand) {
        newMakeId = idMaker;
        idMaker++;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.mængdeProduceret = mængdeProduceret;
        this.alkoholProcent = alkoholProcent;
        this.medarbejder = medarbejder;
        this.råvare = råvare;
        this.røg = røg;
        this.kommentar = kommentar;
        this.vand = vand;
    }

    public int getNewMakeId() {
        return newMakeId;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getMængdeProduceret() {
        return mængdeProduceret;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public Vand getVand() {
        return vand;
    }

    public static int getIdMaker() {
        return idMaker;
    }

    public List<Destilat> getDestilatList() {
        return destilatList;
    }

    public List<Råvare> getRåvareList() {
        return råvareList;
    }

}
