package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReolTest {

    private Lager lager;
    private Reol reol;

    @BeforeEach
    void setup() {
        Lager.setAntalLagere(1);
        lager = new Lager("Test adresse 1", "Test navn 1");
        reol = new Reol(1, lager);
    }

    @Test
    void TC1_Constructor_Korrekt() {
        assertAll(
                () -> assertEquals(1, reol.getNummer()),
                () -> assertEquals(lager, reol.getLager())
        );
    }

    @Test
    void TC2_Constructor_nummerIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Reol(0, lager)
        );

        assertEquals("Reol nummer må ikke være null eller tom", exception.getMessage());
    }

    @Test
    void TC3_Constructor_LagerIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Reol(1, null)
        );

        assertEquals("Lager må ikke være null eller tom", exception.getMessage());
    }

    @Test
    void testAddHylde() {
        reol.addHylde(1);
        reol.addHylde(2);

        assertAll(
                () -> assertEquals(2, reol.getHylder().size()),
                () -> assertNotNull(reol.getHylde(1)),
                () -> assertNotNull(reol.getHylde(2)),
                () -> assertEquals(1, reol.getHylde(1).getNummer()),
                () -> assertEquals(reol, reol.getHylde(1).getReol())
        );
    }

    @Test
    void testGetFade() {
        reol.addHylde(1);
        reol.addHylde(2);

        Hylde hylde1 = reol.getHylde(1);
        Hylde hylde2 = reol.getHylde(2);

        Fad fad1 = new Fad(34, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør1");
        Fad fad2 = new Fad(40, Trætype.MIZUNARA, List.of("Sherry"), "Leverandør2");

        hylde1.addFad(fad1);
        hylde2.addFad(fad2);

        List<Fad> fade = reol.getFade();

        assertAll(
                () -> assertEquals(2, fade.size()),
                () -> assertTrue(fade.contains(fad1)),
                () -> assertTrue(fade.contains(fad2))
        );
    }
}