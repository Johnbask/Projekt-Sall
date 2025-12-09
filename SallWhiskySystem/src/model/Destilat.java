package model;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class Destilat implements
        java.io.Serializable {
    public static int antalDestilater =1;
    private Double liter;
    private boolean isSingleMalt;
    private boolean isHeart;
    private int batchId;
    private boolean isUsed = false;
    private List<Omhældning> omhældninger = new ArrayList<>();

    // link attributter
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


    public Destillering getDestillering() {
        return destillering;
    }

    public boolean getSingleMalt() {
        return isSingleMalt;
    }

    public boolean getHeart() {
        return isHeart;
    }

    public String getRøgmateriale(){
        return this.destillering.getRøg();
    }

    public boolean tapDestilat(double mængde) {

        if(mængde>liter){
            return false;
        }else {
            liter-=mængde;
            return true;
        }
    }

    public boolean isSingleMalt(){
        return isSingleMalt;
    }

    public boolean isHeart(){
        return isHeart;
    }

    public void addOmhælning(Omhældning omhældning){
        omhældninger.add(omhældning);
    }

    public List<Omhældning> getOmhældninger() {
        return omhældninger;
    }
}
