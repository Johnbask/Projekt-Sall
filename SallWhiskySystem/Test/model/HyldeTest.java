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
    void TC1_Constructor_Korrekt() {
        assertAll(
                () -> assertEquals(1, hylde.getNummer()),
                () -> assertEquals(reol, hylde.getReol())
        );
    }

    @Test
    void TC2_Constructor_nummerIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hylde(0, reol)
        );

        assertEquals("Hylde nummer må ikke være null eller tom", exception.getMessage());
    }

    @Test
    void TC3_Constructor_reolIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hylde(1, null)
        );

        assertEquals("Reol må ikke være null eller tom", exception.getMessage());
    }

    @Test
    void testAddFad() {
        Fad fad = new Fad(32, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør 1");

        hylde.addFad(fad);

        assertEquals(fad, hylde.getFad());
    }

}