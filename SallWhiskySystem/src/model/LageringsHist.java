package model;

import java.io.Serializable;
import java.time.LocalDate;

public class LageringsHist implements Serializable {
    private LocalDate startDato;
    private LocalDate slutDato;

    // Links
    private Fad fad;
    private Hylde hylde;

    public LageringsHist(LocalDate startDato,Hylde hylde) {
        this.startDato = startDato;
        this.hylde = hylde;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public Fad getFad() {
        return fad;
    }

    public Hylde getHylde() {
        return hylde;
    }
}
