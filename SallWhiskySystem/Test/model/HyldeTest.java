package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HyldeTest {
    private Lager lager;
    private Reol reol;
    private Hylde hylde;

    @BeforeEach
    void setup() {
        Lager.setAntalLagere(1);
        lager = new Lager("Test adresse 1", "Test navn 1");
        reol = new Reol(3, lager);
        hylde = new Hylde(1, reol);
    }

    @Test
    void testConstructor() {
        assertAll(
                () -> assertEquals(1, hylde.getNummer()),
                () -> assertEquals(reol, hylde.getReol())
        );
    }

    @Test
    void testAddFad() {
        Fad fad = new Fad(32, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør 1");

        hylde.addFad(fad);

        assertEquals(fad, hylde.getFad());
    }

}