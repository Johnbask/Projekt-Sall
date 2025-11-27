package model;

public class Fad implements
        java.io.Serializable {
    private int fadId;
    private Hylde hylde;

    public Fad(int fadId,Hylde hylde) {
        this.fadId = fadId; this.hylde = hylde;
    }

    @Override
    public String toString() {
        return "Fad{" +
                "fadId=" + fadId +
                '}';
    }
}

