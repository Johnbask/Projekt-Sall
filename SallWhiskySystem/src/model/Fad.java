package model;

import java.util.List;

public class Fad implements
        java.io.Serializable {
    private double liter;
    private String materiale;
    private int fadId;
    private List<String> tidligereIndhold;
    private String leverandør;

    // links
    // TODO rettelser til List<Class>, instedet for Class class (Fad 1 -- 0..* (1..*) instedet for 1)
    private Destilat gamleDestillater;
    private Hylde hylde;
    private LageringsHist lageringsHists;
    private Omhældning omhældning;

    public Fad(double liter, String materiale, int fadId, List<String> tidligereIndhold, String leverandør,
               Destilat gamleDestillater,  Hylde hylde, LageringsHist lageringsHists, Omhældning omhældning) {
        this.liter = liter;
        this.materiale = materiale;
        this.fadId = fadId;
        this.tidligereIndhold = tidligereIndhold;
        this.leverandør = leverandør;
        this.gamleDestillater = gamleDestillater;
        this.hylde = hylde;
        this.lageringsHists = lageringsHists;
        this.omhældning = omhældning;
    }

    @Override
    public String toString() {
        return
                "fad"+ fadId + " Hylde" + hylde.getNummer() + " reol" + hylde.getReol().getNummer()+" lager"+ hylde.getReol().getLager().getLagerId() ;
    }

    public int getFadId() {
        return fadId;
    }

    public Hylde getHylde() {
        return hylde;
    }

    public void setHylde(Hylde hylde) {
        this.hylde = hylde;
    }
}

