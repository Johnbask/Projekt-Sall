package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    private Lager lager;

    @BeforeEach
    void setup() {
        Lager.setAntalLagere(1);
        lager = new Lager("Test adresse", "Test navn");
    }

    @Test
    void TC1_Constructor_Korrekt() {
        assertAll(
                () -> assertEquals("Test adresse", lager.getAdresse()),
                () -> assertEquals("Test navn", lager.getNavn()),
                () -> assertEquals(1, lager.getLagerId())
        );
    }

    @Test
    void TC2_Constructor_Adresse_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Lager(null, "Test navn")
        );

        assertEquals("Adresse må ikke være null eller tom", exception.getMessage());
    }

    @Test
    void TC3_Constructor_Navn_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Lager("Test adresse", null)
        );

        assertEquals("Navn må ikke være null eller tom", exception.getMessage());
    }

    @Test
    void testIndsætNyLager_LagerIdEquals2() {
        Lager lager2 = new Lager("Test adresse 2", "Test navn 2");
        assertEquals(2, lager2.getLagerId());
    }

    @Test
    void testAddReol() {
        lager.addReol(1);
        lager.addReol(2);

        Map<Integer, Reol> reoler = lager.getReoler();

        assertAll(
                () -> assertEquals(2, reoler.size()),
                () -> assertNotNull(reoler.get(1)),
                () -> assertNotNull(reoler.get(2)),
                () -> assertEquals(1, reoler.get(1).getNummer()),
                () -> assertEquals(2, reoler.get(2).getNummer()),
                () -> assertEquals(lager, reoler.get(1).getLager()),
                () -> assertEquals(lager, reoler.get(2).getLager())
        );
    }

    @Test
    void testGetReol() {
        lager.addReol(1);
        Reol reol = lager.getReol(1);

        assertAll(
                () -> assertNotNull(reol),
                () -> assertEquals(1, reol.getNummer()),
                () -> assertEquals(lager, reol.getLager())
        );
    }

    @Test
    void testGetFade() {
        lager.addReol(1);
        Reol reol = lager.getReol(1);
        reol.addHylde(1);
        reol.addHylde(2);

        Hylde hylde1 = reol.getHylde(1);
        Hylde hylde2 = reol.getHylde(2);

        Fad fad1 = new Fad(34, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør1");
        Fad fad2 = new Fad(30, Trætype.QUERCUSROBUR, List.of("Bourbon"), "Leverandør2");

        hylde1.addFad(fad1);
        hylde2.addFad(fad2);

        List<Fad> fade = lager.getFade();

        assertAll(
                () -> assertEquals(2, fade.size()),
                () -> assertTrue(fade.contains(fad1)),
                () -> assertTrue(fade.contains(fad2)),
                () -> assertEquals(2, lager.getFade().size())
        );
    }



}