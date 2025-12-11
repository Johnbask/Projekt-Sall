package model;

public class Destilat implements
        java.io.Serializable {
    public static int antalDestilater =1;
    private Double liter;
    private boolean isSingleMalt;
    private boolean isHeart;
    private int batchId;
    // link attributter
    private Destillering destillering;

    public Destilat(double liter, boolean isSingleMalt, boolean isHeart, Destillering destillering) {
        if (liter <= 0) {
            throw new IllegalArgumentException("Liter skal være > 0");
        }
        if (destillering == null) {
            throw new NullPointerException("Destillering må ikke være null");
        }
        this.liter = liter;
        this.isSingleMalt = isSingleMalt;
        this.isHeart = isHeart;
        this.destillering = destillering;
        this.batchId=antalDestilater;
        antalDestilater++;
    }

    public static void setAntalDestilater(int antalDestilater) {
        Destilat.antalDestilater = antalDestilater;
    }

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
        if(mængde <= 0){
            return false;
        }
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

    @Override
    public String toString() {
        return "Destilat{" +
                "batchId=" + batchId +
                '}';
    }
}
