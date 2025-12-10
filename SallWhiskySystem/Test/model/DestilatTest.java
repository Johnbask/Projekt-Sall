package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DestilatTest {

    private Destilat destilat;
    private Destillering destillering;

    @BeforeEach
    void setDestilat(){
        destillering = new Destillering(LocalDate.of(1999,9,9),LocalDate.of(1999,10,10),100,62,new Medarbejder("Sander Forzen","apostel"),"Øko bygmalt","Tørv","den er våd",new Vand("Østersøen"));
        destilat = new Destilat(100,true,false,destillering);
    }

    @Test
    void TC1_Constructor_Korrekt(){

        Assertions.assertEquals(100, destilat.getLiter());
        Assertions.assertTrue(destilat.isSingleMalt());
        Assertions.assertFalse(destilat.isHeart());

    }

    @Test
    void TC2_Constructor_NullPointerException() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new Destilat(100, true, true, null)
        );

        assertEquals("Destillering må ikke være null", exception.getMessage());
    }

    @Test
    void TC3_Constructor_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Destilat(0, true, true, destillering)
        );

        assertEquals("Liter skal være > 0", exception.getMessage());
    }

    @Test
    void testAntalDestilater(){
        Destilat destilat1 = new Destilat(100,true,true,destillering);
        assertEquals(2,destilat1.getBatchId());
    }

    @Test
    void testTapDestilat(){
        assertTrue(destilat.tapDestilat(60));
        assertFalse(destilat.tapDestilat(60));
        assertFalse(destilat.tapDestilat(-30));
        assertFalse(destilat.tapDestilat(0));
    }

    @Test
    void testAddOmhældning(){
        int test = destilat.getOmhældninger().size();
        Fad fad1 = new Fad(5000,Trætype.MIZUNARA,new ArrayList<>(List.of("Sherry")),"Ruben a/s");
        Fad fad2 = new Fad(5000,Trætype.MIZUNARA,new ArrayList<>(List.of("Sherry")),"Ruben a/s");
        destilat.addOmhælning(new Omhældning(30,LocalDate.now(),fad1,new Medarbejder("John", "johnson"),destilat));
        Assertions.assertEquals(test + 1, destilat.getOmhældninger().size());
        destilat.addOmhælning(new Omhældning(120,LocalDate.now(),fad2,new Medarbejder("John", "johnson"),destilat));
        Assertions.assertEquals(test + 2, destilat.getOmhældninger().size());
    }
}