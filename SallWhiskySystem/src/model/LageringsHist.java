package model;

import java.io.Serializable;
import java.time.LocalDate;

public class LageringsHist implements Serializable {
    private LocalDate startDato;
    private LocalDate slutDato;

    // Links
    private Fad fad;
    private Hylde hylde;

    public LageringsHist(LocalDate startDato, LocalDate slutDato) {
        this.startDato = startDato;
        this.slutDato = slutDato;
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
