package model;

import java.io.Serializable;
import java.time.LocalDate;

public class LageringsHist implements Serializable {
    private LocalDate startDato;
    private LocalDate slutDato;

    public LageringsHist(LocalDate startDato, LocalDate slutDato) {
        this.startDato = startDato;
        this.slutDato = slutDato;
    }
}
