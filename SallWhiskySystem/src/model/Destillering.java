package model;

import java.time.LocalDate;

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
    private  Vand vand;
    private Medarbejder medarbejder;

    public Destillering(
            LocalDate startDato, LocalDate slutDato,
            double mængdeProduceret, double alkoholProcent,
            Medarbejder medarbejder,String råvare, String røg,
            String kommentar,Vand vand
    ) {
        if (startDato == null || slutDato == null) {
            throw new NullPointerException("startDato/slutDato må ikke være null");
        }

        if (startDato.isAfter(slutDato)) {
            throw new IllegalArgumentException("StartDato må ikke være efter slutDato");
        }

        if (mængdeProduceret <= 0) {
            throw new IllegalArgumentException("Mængde Produceret skal være større end 0");
        }

        if (alkoholProcent <= 0 || alkoholProcent > 100) {
            throw new IllegalArgumentException("Alkohol Procenten skal være > 0 og må ikke være 100");
        }

        if (medarbejder == null) {
            throw new NullPointerException("Medarbejder må ikke være null eller tom");
        }

        if (råvare == null) {
            throw new NullPointerException("Råvare må ikke være null eller tom");
        }

        if (røg == null || kommentar == null) {
            throw new NullPointerException("røg/kommentar må ikke være null");
        }

        if (vand == null) {
            throw new NullPointerException("Vand må ikke være null");
        }

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

    public static void setIdMaker(int idMaker) {
        Destillering.idMaker = idMaker;
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

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public Vand getVand() {
        return vand;
    }

    public String getRøg(){
        return røg;
    }

    public String getRåvare(){
        return råvare;
    }

    @Override
    public String toString() {
        return newMakeId +"  "+ startDato +
                " - " + slutDato +
                "  " + mængdeProduceret +
                "L  " + alkoholProcent +
                "%  " + råvare +
                "  " + røg +
                " vand  " + vand +
                " medarbejder : " + medarbejder.getNavn() +
                " note : " + kommentar;
    }
}
