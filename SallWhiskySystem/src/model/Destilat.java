package model;

import java.security.PrivateKey;

public class Destilat {
    private Double liter;
    private boolean isSingleMalt;
    private boolean isHeart;
    private int batchId;

    private String røgmateriale; //nullable

    // link attributter
    private Destillering destillering;
    private ModningsTid modningstid;

    public Destilat(Double liter, boolean isSingleMalt, boolean isHeart, int batchId) {
        this.liter = liter;
        this.isSingleMalt = isSingleMalt;
        this.isHeart = isHeart;
        this.batchId = batchId;
    }

    public void setRøgmateriale(String røgmateriale) {
        this.røgmateriale = røgmateriale;
    }
}
