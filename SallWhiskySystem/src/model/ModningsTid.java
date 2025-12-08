package model;

import java.io.Serializable;
import java.time.LocalDate;

public class ModningsTid implements Serializable {

    private LocalDate startDato;
    private double Forventespild;
    private boolean isModen;

    public ModningsTid(LocalDate startDato) {
        this.startDato = startDato;

    }

    public LocalDate getStartDato() {
        return startDato;
    }


    public boolean isModen(){
        return LocalDate.now().isAfter(startDato.plusYears(3));
    }
}
