package model;

import java.time.LocalDate;
import java.util.List;

public class Destillering {

    private int newMakeId;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double mængdeProduceret;
    private double alkoholProcent;
    private String kommentar;

    // link attributter
    private List<Råvare> råvareList;
    private  Vand vand;
    private Medarbejder medarbejder;

    //tror den her forbindelse ikke skal være her men better safe than sorry
    private  List<Destilat> destilatList;

    public Destillering(int newMakeId, LocalDate startDato, LocalDate slutDato, double mængdeProduceret, double alkoholProcent, String kommentar) {
        this.newMakeId = newMakeId;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.mængdeProduceret = mængdeProduceret;
        this.alkoholProcent = alkoholProcent;
        this.kommentar = kommentar;
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
}
