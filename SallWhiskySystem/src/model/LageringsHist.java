package model;

import java.io.Serializable;
import java.time.LocalDate;

public class LageringsHist implements Serializable {
    private LocalDate startDato;
    private Fad fad;
    private Hylde hylde;

    public LageringsHist(LocalDate startDato,Hylde hylde,Fad fad) {
        this.startDato = startDato;
        this.hylde = hylde;
        this.fad = fad;
    }

    public Fad getFad() {
        return fad;
    }

    public Hylde getHylde() {
        return hylde;
    }
}
