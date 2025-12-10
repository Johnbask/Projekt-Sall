package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
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
    void TC1_opretLager_KorrektOprettet() {
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
    void TC2_opretLager_AdresseIllegalArgument() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Controller.opretLager(null, "Test navn 1")
        );

        assertEquals("Adresse må ikke være null eller tom", exception.getMessage());
    }

    @Test
    void TC3_opretLager_NavnIllegalArgument() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Controller.opretLager("Test Adresse 1", null)
        );

        assertEquals("Navn må ikke være null eller tom", exception.getMessage());
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
    void TC1_opretFad_KorrektOprettet() {
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
                () -> assertEquals(List.of("Whisky"), fad.getTidligereIndhold()),
                () -> assertEquals("Leverandør 1", fad.getLeverandør()),
                () -> assertEquals(hylde, fad.getHylde()),
                () -> assertEquals(fad, hylde.getFad()),
                () -> assertTrue(Storage.getFade().contains(fad))
        );
    }

    @Test
    void TC2_opretFad_ThrowsIllegalArgumentException() {
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager1, 1);
        Reol reol = lager1.getReol(1);
        Controller.addHylderTilReol(reol, 3);
        Hylde hylde = reol.getHylde(1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Controller.opretFad(0, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør 1", hylde)
        );

        assertEquals("Negative space doesnt exist, please use a positive integer for the liters ", exception.getMessage());
    }

    @Test
    void TC3_opretFad_ThrowsNullPointerException() {
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager1, 1);
        Reol reol = lager1.getReol(1);
        Controller.addHylderTilReol(reol, 3);
        Hylde hylde = reol.getHylde(1);

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Controller.opretFad(100, null, List.of("Whisky"), "Leverandør 1", hylde)
        );

        assertEquals("One or more arguments were null", exception.getMessage());
    }

    @Test
    void test_getFade() {
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager1, 1);
        Reol reol = lager1.getReol(1);
        Controller.addHylderTilReol(reol, 3);
        Hylde hylde1 = reol.getHylde(1);

        Fad fad1 = Controller.opretFad(100, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør 1", hylde1);

        Hylde hylde2 = reol.getHylde(2);

        Fad fad2 = Controller.opretFad(150, Trætype.QUERCUSROBUR, List.of("Sherry"), "Leverandør 2", hylde2);

        List<Fad> fade = Controller.getFade();

        assertAll(
                () -> assertEquals(2, fade.size()),
                () -> assertTrue(fade.contains(fad1)),
                () -> assertTrue(fade.contains(fad2))
        );

    }

    @Test
    void test_sletFad() {
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager1, 1);
        Reol reol = lager1.getReol(1);
        Controller.addHylderTilReol(reol, 3);
        Hylde hylde1 = reol.getHylde(1);

        Fad fad1 = Controller.opretFad(90, Trætype.MIZUNARA, List.of("Bourbon"), "Leverandør 1", hylde1);

        assertAll(
                () -> assertNotNull(fad1.getHylde()),
                () -> assertNotNull(hylde1.getFad()),
                () -> assertEquals(fad1, hylde1.getFad()),
                () -> assertEquals(hylde1, fad1.getHylde()),
                () -> assertTrue(Storage.getFade().contains(fad1))
        );

        Controller.sletFad(fad1);

        assertAll(
                () -> assertNull(hylde1.getFad()),
                () -> assertNotEquals(fad1, hylde1.getFad()),
                () -> assertFalse(Storage.getFade().contains(fad1))
        );
    }

    @Test
    void test_getFildFade() {
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager1, 1);
        Reol reol = lager1.getReol(1);
        Controller.addHylderTilReol(reol, 3);
        Hylde hylde1 = reol.getHylde(1);

        Fad fad1 = Controller.opretFad(100, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør 1", hylde1);
        fad1.setLitterIFad(50);

        Hylde hylde2 = reol.getHylde(2);

        Fad fad2 = Controller.opretFad(150, Trætype.QUERCUSROBUR, List.of("Sherry"), "Leverandør 2", hylde2);
        fad2.setLitterIFad(100);

        List<Fad> FilledFade = Controller.getFildFad();

        assertAll(
                () -> assertEquals(2, FilledFade.size()),
                () -> assertTrue(FilledFade.contains(fad1)),
                () -> assertTrue(FilledFade.contains(fad2)),
                () -> assertEquals(50, fad1.getLitterIFad()),
                () -> assertEquals(100, fad2.getLitterIFad())
        );
    }

    @Test
    void test_opretMedarbejder() {
        Medarbejder medarbejder = Controller.opretMedarbejder("Ib Hansen", "Medarbejder");

        assertAll(
                () -> assertNotNull(medarbejder),
                () -> assertEquals("Ib Hansen", medarbejder.getNavn()),
                () -> assertEquals("Medarbejder", medarbejder.getStilling()),
                () -> assertTrue(Storage.getMedarbejderne().contains(medarbejder))
        );
    }

    @Test
    void test_getMedarbejder() {
        Medarbejder medarbejder1 = Controller.opretMedarbejder("Ib Hansen", "Medarbejder1");
        Medarbejder medarbejder2 = Controller.opretMedarbejder("Bob Jørgensen", "Medarbejder2");

        List<Medarbejder> medarbejdere = Controller.getMedarbejderne();

        assertAll(
                () -> assertEquals(2, medarbejdere.size()),
                () -> assertTrue(medarbejdere.contains(medarbejder1)),
                () -> assertTrue(medarbejdere.contains(medarbejder2))
        );
    }

    @Test
    void test_opretDestilat() {
        LocalDate startDato = LocalDate.of(2025, 12, 9);
        LocalDate slutDato = LocalDate.of(2025, 12, 15);
        Vand vand = Controller.opretVand("Vand Kilde 1");
        Medarbejder medarbejder = Controller.opretMedarbejder("Ib Jensen", "Destillatør");

        Destillering destillering = new Destillering(
                startDato, slutDato,
                100, 64, medarbejder,
                "Byg", "Røg Materiale",
                "Kommentar", vand
        );

        Destilat destilat = Controller.opretDestilat(50, true, false, destillering);

        assertAll(
                () -> assertNotNull(destilat),
                () -> assertEquals(1, destilat.getBatchId()),
                () -> assertEquals(50, destilat.getLiter()),
                () -> assertTrue(destilat.isSingleMalt()),
                () -> assertFalse(destilat.isHeart()),
                () -> assertEquals(destillering, destilat.getDestillering()),
                () -> assertTrue(Storage.getDestilater().contains(destilat))
        );
    }

    @Test
    void test_getDestilater() {
        // Første Destilat
        LocalDate startDato1 = LocalDate.of(2025, 12, 12);
        LocalDate slutDato1 = LocalDate.of(2025, 12, 20);
        Vand vand1 = Controller.opretVand("Vandkilden 1");
        Medarbejder medarbejder1 = Controller.opretMedarbejder("Ib Jørgens", "Destillatør medhjælper");

        Destillering destillering1 = new Destillering(
                startDato1, slutDato1,
                100, 64, medarbejder1,
                "Byg", "Røg Materiale 1",
                "test kommentar", vand1
        );

        Destilat destilat1 = Controller.opretDestilat(50, true, false, destillering1);

        // Anden Destilat
        LocalDate startDato2 = LocalDate.of(2025, 12, 15);
        LocalDate slutDato2 = LocalDate.of(2025, 12, 25);
        Vand vand2 = Controller.opretVand("Vandkilden 2");
        Medarbejder medarbejder2 = Controller.opretMedarbejder("Bo Hansen", "Leder");

        Destillering destillering2 = new Destillering(
                startDato2, slutDato2,
                80, 70, medarbejder2,
                "Byg", "Røg materiale 2",
                "Kommentar", vand2
        );

        Destilat destilat2 = Controller.opretDestilat(50, false, true, destillering2);

        List<Destilat> destilater =  Controller.getDestilater();

        assertAll(
                () -> assertEquals(2, destilater.size()),
                () -> assertTrue(destilater.contains(destilat1)),
                () -> assertTrue(destilater.contains(destilat2))
        );
    }

    @Test
    void TC1_opretOmhældning_KorrektOprettet() {
        // Sætter Lager systemet op
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager1, 1);
        Reol reol = lager1.getReol(1);
        Controller.addHylderTilReol(reol, 3);
        Hylde hylde1 = reol.getHylde(1);

        // Laver Fad
        Fad fad1 = Controller.opretFad(100, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør 1", hylde1);

        // Laver Vand
        Vand vand1 = Controller.opretVand("Vandkilden 1");

        // Laver Medarbejder
        Medarbejder medarbejder = Controller.opretMedarbejder("Hans Jensen", "Anden Leder");

        // Laver Destillering
        LocalDate startDato = LocalDate.of(2025, 12, 11);
        LocalDate slutDato = LocalDate.of(2025, 12, 24);

        Destillering destillering = new Destillering(startDato, slutDato,
                100, 69, medarbejder,
                "Byg", "Røg materiale",
                "Kommentar", vand1
        );

        // Laver Destilat
        Destilat destilat = Controller.opretDestilat(50, true, true, destillering);

        // Dato for omhældning
        LocalDate dato =  LocalDate.of(2025, 12, 30);

        Omhældning omhældning = Controller.opretPåhældning(fad1, destilat, dato, 30, medarbejder);

        assertAll(
                () -> assertNotNull(omhældning),
                () -> assertEquals(fad1, omhældning.getFad()),
                () -> assertEquals(destilat, omhældning.getDestilat()),
                () -> assertEquals(dato, omhældning.getDato()),
                () -> assertEquals(30, omhældning.getMængde()),
                () -> assertEquals(medarbejder, omhældning.getMedarbejder()),
                () -> assertEquals(30, fad1.getLitterIFad()),
                () -> assertEquals(20, destilat.getLiter()), // 50 - 30 = 20
                () -> assertTrue(fad1.getOmhældning().contains(omhældning))
        );
    }

    @Test
    void TC2_opretOmhældning_NullPointerException() {
        // Sætter Lager systemet op
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager1, 1);
        Reol reol = lager1.getReol(1);
        Controller.addHylderTilReol(reol, 3);
        Hylde hylde1 = reol.getHylde(1);

        // Laver Fad
        Fad fad1 = Controller.opretFad(100, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør 1", hylde1);

        // Laver Vand
        Vand vand1 = Controller.opretVand("Vandkilden 1");

        // Laver Medarbejder
        Medarbejder medarbejder = Controller.opretMedarbejder("Hans Jensen", "Anden Leder");

        // Laver Destillering
        LocalDate startDato = LocalDate.of(2025, 12, 11);
        LocalDate slutDato = LocalDate.of(2025, 12, 24);

        Destillering destillering = new Destillering(startDato, slutDato,
                100, 69, medarbejder,
                "Byg", "Røg materiale",
                "Kommentar", vand1
        );

        // Laver Destilat
        Destilat destilat = Controller.opretDestilat(50, true, true, destillering);

        // Dato for omhældning
        LocalDate dato =  LocalDate.of(2025, 12, 30);

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Controller.opretPåhældning(null, destilat, dato, 30, medarbejder)
        );

        assertEquals("Fad må ikke være null",  exception.getMessage());
    }

    @Test
    void TC3_opretOmhældning_IllegalArgumentException() {
        // Sætter Lager systemet op
        Lager lager1 = Controller.opretLager("Test adresse 1", "Test navn 1");
        Controller.addReolerTilLager(lager1, 1);
        Reol reol = lager1.getReol(1);
        Controller.addHylderTilReol(reol, 3);
        Hylde hylde1 = reol.getHylde(1);

        // Laver Fad
        Fad fad1 = Controller.opretFad(100, Trætype.WHITE_OAK, List.of("Whisky"), "Leverandør 1", hylde1);

        // Laver Vand
        Vand vand1 = Controller.opretVand("Vandkilden 1");

        // Laver Medarbejder
        Medarbejder medarbejder = Controller.opretMedarbejder("Hans Jensen", "Anden Leder");

        // Laver Destillering
        LocalDate startDato = LocalDate.of(2025, 12, 11);
        LocalDate slutDato = LocalDate.of(2025, 12, 24);

        Destillering destillering = new Destillering(startDato, slutDato,
                100, 69, medarbejder,
                "Byg", "Røg materiale",
                "Kommentar", vand1
        );

        // Laver Destilat
        Destilat destilat = Controller.opretDestilat(50, true, true, destillering);

        // Dato for omhældning
        LocalDate dato =  LocalDate.of(2025, 12, 30);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Controller.opretPåhældning(fad1, destilat, dato, 0, medarbejder)
        );

        assertEquals("Mængde skal være større end 0", exception.getMessage());
    }

    @Test
    void test_enDestilleringFlereDestilater() {
        LocalDate startDato = LocalDate.of(2025, 12, 11);
        LocalDate slutDato = LocalDate.of(2025, 12, 24);

        Medarbejder medarbejder = Controller.opretMedarbejder("Hans Jensen", "Anden Leder");

        Vand vand1 = Controller.opretVand("Vandkilden 1");

        Destillering destillering = new Destillering(startDato, slutDato,
                300, 69, medarbejder,
                "Byg", "Røg materiale",
                "Kommentar", vand1
        );

        Destilat destilat1 =  Controller.opretDestilat(100, true, true, destillering);

        Destilat destilat2 =  Controller.opretDestilat(50, true, true, destillering);

        Destilat destilat3 =  Controller.opretDestilat(130, true, true, destillering);

        assertAll(
                () -> assertEquals(3, Storage.getDestilater().size()),
                () -> assertTrue(Storage.getDestilater().contains(destilat1)),
                () -> assertTrue(Storage.getDestilater().contains(destilat2)),
                () -> assertTrue(Storage.getDestilater().contains(destilat3)),
                () -> assertEquals(destillering, destilat1.getDestillering()),
                () -> assertEquals(destillering, destilat2.getDestillering()),
                () -> assertEquals(destillering, destilat3.getDestillering())
        );
    }

}