package model;

public enum Trætype {
    WHITE_OAK("White Oak"),
    QUERCUSROBUR("Quercus Robur"),
    MIZUNARA("Mizunara"),
    ;

    private final String sort;

    Trætype(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return sort;
    }
}
