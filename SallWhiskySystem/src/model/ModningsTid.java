package model;

import java.time.LocalDate;

public class ModningsTid {

    private LocalDate startDato;
    private LocalDate slutDato;
    private double spild;

    // Links
    private Destilat destilat;

    public ModningsTid(LocalDate startDato, LocalDate slutDato, double spild) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.spild = spild;
    }

    public void setSpild(double spild) {
        this.spild = spild;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getSpild() {
        return spild;
    }

    public Destilat getDestilat() {
        return destilat;
    }
}
