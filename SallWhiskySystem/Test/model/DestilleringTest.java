package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DestilleringTest {

    @Test
    void TC1_Constructor_Korrekt() {
        Destillering destillering1 =
                new Destillering(
                        LocalDate.of(1999,9,9), LocalDate.of(1999,10,10),
                        2000,64, new Medarbejder("Ruben","Guden"),
                        "atomer", "gas",
                        "\"let there be whiskye\"",
                        new Vand("ungdommens kilde")
                );
        Assertions.assertEquals(1,destillering1.getNewMakeId());

        Destillering destillering2 =
                new Destillering(
                        LocalDate.of(1999,9,9), LocalDate.of(1999,10,10),
                        2000,64, new Medarbejder("Ruben","Guden"),
                        "atomer","gas","\"let there be whiskye\"",
                        new Vand("ungdommens kilde")
                );
        Assertions.assertEquals(2,destillering2.getNewMakeId());

        Destillering destillering3 =
                new Destillering(
                        LocalDate.of(1999,9,9), LocalDate.of(1999,10,10),
                        2000,64, new Medarbejder("Ruben","Guden"),
                        "atomer","gas","\"let there be whiskye\"",
                        new Vand("ungdommens kilde")
                );
        Assertions.assertEquals(3,destillering3.getNewMakeId());
    }

    @Test
    void TC2_Constructor_NullPointerException() {
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> new Destillering(
                        LocalDate.of(2025, 12, 15), LocalDate.of(2025, 12, 26),
                        100, 70, null, "Byg" , "Blå røg", "Test kommentar",
                        new Vand("Test Kilde")
                )
        );

        Assertions.assertEquals("Medarbejder må ikke være null eller tom", exception.getMessage());
    }

    @Test
    void TC3_Constructor_IllegalArgumentException() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Destillering(
                        LocalDate.of(2025, 12, 15), LocalDate.of(2025, 12, 26),
                        100, 0, null, "Byg" , "Blå røg", "Test kommentar",
                        new Vand("Test Kilde"))
        );

        Assertions.assertEquals("Alkohol Procenten skal være > 0 og må ikke være 100", exception.getMessage());
    }

}