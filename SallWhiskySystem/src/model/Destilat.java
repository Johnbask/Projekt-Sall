package model;

import java.security.PrivateKey;
import java.util.List;

public class Destilat implements
        java.io.Serializable {
    private Double liter;
    private boolean isSingleMalt;
    private boolean isHeart;
    private int batchId;
    private boolean isUsed = false;

    private String røgmateriale; //nullable

    // link attributter
    List<Destilat> destilater;
    private Destillering destillering;
    private ModningsTid modningstid;

    public Destilat(Double liter, boolean isSingleMalt, boolean isHeart, Destillering destillering) {
        this.liter = liter;
        this.isSingleMalt = isSingleMalt;
        this.isHeart = isHeart;
        this.destillering = destillering;
    }

    public void setRøgmateriale(String røgmateriale) {
        this.røgmateriale = røgmateriale;
    }

    public void addDestilat(Destilat d){
        destilater.add(d);
    }
    public void setUsed(boolean b){
        isUsed = b;
    }
    public boolean getUsed(){
        return isUsed;
    }

    // Getters
    public Double getLiter() {
        return liter;
    }

    public int getBatchId() {
        return batchId;
    }

    public List<Destilat> getDestilater() {
        return destilater;
    }

    public ModningsTid getModningstid() {
        return modningstid;
    }

    public String getRøgmateriale() {
        return røgmateriale;
    }

    public Destillering getDestillering() {
        return destillering;
    }
}
