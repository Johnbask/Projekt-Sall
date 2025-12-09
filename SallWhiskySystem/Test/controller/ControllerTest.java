package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import storage.Storage;

import javax.naming.ldap.Control;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    void setUp() {
        Storage.getVandKilder().clear();
        Storage.getMedarbejderne().clear();
        Storage.getLagere().clear();
        Storage.getDestilater().clear();
        Storage.getFade().clear();

        Lager.setAntalLagere(1);
        Fad.setAntalFade(1);
    }

    @Test
    void test_opretVand() {
        Vand vand = Controller.opretVand("Test Kildevand 1");
        assertAll(
                () -> assertNotNull(vand),
                () -> assertEquals("Test Kildevand 1", vand.getKilde()),
                () -> assertTrue(Storage.getVandKilder().contains(vand))
        );
    }

    @Test
    void test_getVands() {
        Vand vand1 = Controller.opretVand("Test Kildevand 1");
        Vand vand2 = Controller.opretVand("Test Kildevand 2");

        List<Vand> vande = Controller.getVands();

        assertAll(
                () -> assertEquals(2, vande.size()),
                () -> assertTrue(vande.contains(vand1)),
                () -> assertTrue(vande.contains(vand2))
        );
    }

    @Test
    void test_opretLager() {
        Lager lager = Controller.opretLager("Test Adresse 1", "Test navn 1");

        assertAll(
                () -> assertNotNull(lager),
                () -> assertEquals("Test navn 1", lager.getNavn()),
                () -> assertEquals("Test Adresse 1", lager.getAdresse()),
                () -> assertEquals(1, lager.getLagerId()),
                () -> assertTrue(Storage.getLagere().contains(lager))
        );
    }

    @Test
    void test_getLager() {
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Lager lager2 = Controller.opretLager("Test adresse 2", "Test navn 2");

        List<Lager> lagere = Controller.getLager();

        assertAll(
                () -> assertEquals(2, lagere.size()),
                () -> assertTrue(lagere.contains(lager1)),
                () -> assertTrue(lagere.contains(lager2))
        );
    }

    @Test
    void test_getLagerById() {
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Lager lager2 = Controller.opretLager("Test adresse 2", "Test navn 2");

        assertEquals(lager1, Controller.getLager(1));
        assertEquals(lager2, Controller.getLager(2));
    }

    @Test
    void test_addReolerTilLager() {
        Lager lager = Controller.opretLager("Test adresse 1", "Test navn 1");

        Controller.addReolerTilLager(lager, 4);

        assertAll(
                () -> assertNotNull(lager.getReol(1)),
                () -> assertNotNull(lager.getReol(2)),
                () -> assertNotNull(lager.getReol(3)),
                () -> assertNotNull(lager.getReol(4)),
                () -> assertEquals(4, lager.getReoler().size()),
                () -> assertEquals(lager, lager.getReol(1).getLager())

        );
    }

    // TODO: Eventuel Tilføje test for FindReolPåLager()
    // TODO: Eventuel Tilføje test for FindHyldePåReolFraLager()

    @Test
    void test_addHylderTilReol() {
        Lager lager = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager, 1);
        Reol reol = lager.getReol(1);

        Controller.addHylderTilReol(reol, 3);

        assertAll(
                () -> assertNotNull(reol.getHylde(1)),
                () -> assertNotNull(reol.getHylde(2)),
                () -> assertNotNull(reol.getHylde(3)),
                () -> assertEquals(3, reol.getHylder().size()),
                () -> assertEquals(reol, reol.getHylde(1).getReol())
        );
    }

    @Test
    void test_opretFad() {
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager1, 1);
        Reol reol = lager1.getReol(1);
        Controller.addHylderTilReol(reol, 3);
        Hylde hylde = reol.getHylde(1);

        Fad fad = Controller.opretFad(100, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør 1", hylde);

        assertAll(
                () -> assertNotNull(fad),
                () -> assertEquals(100, fad.getLiter()),
                () -> assertEquals(Trætype.WHITE_OAK, fad.getMateriale()),
                () -> assertEquals(List.of("Whisky"), fad.getTidligereIndhold())
        );
    }

    @Test
    void test_getFade() {

    }

    @Test
    void test_sletFad() {

    }

    @Test
    void test_getFildFade() {

    }

    @Test
    void test_getModneFade() {

    }

    @Test
    void test_opretDestilat() {

    }

    @Test
    void test_getDestilater() {

    }

    @Test
    void test_opretOmhældning() {

    }

    @Test
    void test_opretMedarbejder() {

    }

    @Test
    void test_getMedarbejder() {

    }

    @Test
    void test_writeStorage() {

    }

    @Test
    void test_readStorage() {

    }






}