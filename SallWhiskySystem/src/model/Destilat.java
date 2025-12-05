package model;

import java.security.PrivateKey;
import java.util.List;

public class Destilat implements
        java.io.Serializable {
    public static int antalDestilater =1;

    private Double liter;
    private boolean isSingleMalt;
    private boolean isHeart;
    private int batchId;
    private boolean isUsed = false;

    // link attributter
    List<Destilat> destilater;
    private Destillering destillering;

    public static void setAntalDestilater(int antalDestilater) {
        Destilat.antalDestilater = antalDestilater;
    }

    public Destilat(double liter, boolean isSingleMalt, boolean isHeart, Destillering destillering) {
        this.liter = liter;
        this.isSingleMalt = isSingleMalt;
        this.isHeart = isHeart;
        this.destillering = destillering;
        this.batchId=antalDestilater;
        antalDestilater++;
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

    public Destillering getDestillering() {
        return destillering;
    }

    public boolean getSingleMalt() {
        return isSingleMalt;
    }

    public boolean getHeart() {
        return isHeart;
    }
}
