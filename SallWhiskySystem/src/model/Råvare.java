package model;

public class Råvare {
    private boolean isØkologisk;
    private String type;

    // Links
    private Destillering destillering;

    public Råvare(boolean isØkologisk, String type) {
        this.isØkologisk = isØkologisk;
        this.type = type;
    }

    public boolean isØkologisk() {
        return isØkologisk;
    }

    public String getType() {
        return type;
    }

    public Destillering getDestillering() {
        return destillering;
    }
}
