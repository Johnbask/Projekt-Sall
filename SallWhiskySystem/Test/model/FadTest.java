package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;

    @BeforeEach
    void setup() {
        Fad.setAntalFade(1);
        fad = new Fad(100, Trætype.WHITE_OAK, List.of("Whisky", "Bourbon"), "Leverandør 1");
    }

    @Test
    void testConstructor() {
        assertAll(
                () -> assertEquals(1, fad.getFadId()),
                () -> assertEquals(100, fad.getLiter()),
                () -> assertEquals(Trætype.WHITE_OAK, fad.getMateriale()),
                () -> assertEquals(2, fad.getTidligereIndhold().size()),
                () -> assertTrue(fad.getTidligereIndhold().contains("Whisky")),
                () -> assertTrue(fad.getTidligereIndhold().contains("Bourbon")),
                () -> assertEquals("Leverandør 1", fad.getLeverandør())
        );
    }

    @Test
    void testIndsætNyFad_FadIdEquals2() {
        Fad fad2 = new Fad(80, Trætype.MIZUNARA, List.of("Sherry"), "Leverandør 2");

        assertEquals(2, fad2.getFadId());
    }

    @Test
    void testAddTidligereIndhold() {
        int tidligereIndholdSize = fad.getTidligereIndhold().size();
        fad.addTidligereIndhold("Wine");

        assertEquals(tidligereIndholdSize + 1, fad.getTidligereIndhold().size());
        assertTrue(fad.getTidligereIndhold().contains("Wine"));
    }

    @Test
    void testAddLagerHist() {
        Lager lager = new Lager("Test Adresse 1", "Test Navn 1");
        Reol reol = new Reol(1, lager);
        Hylde hylde = new Hylde(1, reol);
        fad.setHylde(hylde);

        int lageringsHistSize = fad.getLageringsHists().size();
        fad.addLagerHist();

        assertEquals(lageringsHistSize + 1, fad.getLageringsHists().size());
    }

    @Test
    void testAddLiterOfDestilatToFad() {
        assertTrue(fad.addLiterOfDestilatToFad(50));
        assertEquals(50, fad.getLitterIFad());

        assertTrue(fad.addLiterOfDestilatToFad(30));
        assertEquals(80, fad.getLitterIFad());

        assertFalse(fad.addLiterOfDestilatToFad(30));
        assertEquals(80, fad.getLitterIFad());
    }

    @Test
    void testIsTom() {
        assertTrue(fad.isTom());

        fad.setLitterIFad(50);
        assertFalse(fad.isTom());

        fad.setLitterIFad(0);
        assertTrue(fad.isTom());
    }

    @Test
    void testSetHylde() {
        Lager lager = new Lager("Test adresse 1", "Test navn 1");
        Reol reol = new Reol(1, lager);
        Hylde hylde = new Hylde(1, reol);
        fad.setHylde(hylde);

        assertEquals(hylde, fad.getHylde());
    }

}