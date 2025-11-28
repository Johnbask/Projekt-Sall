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

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
