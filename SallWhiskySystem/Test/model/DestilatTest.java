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
    void testConstructor(){

        Assertions.assertEquals(100, destilat.getLiter());
        Assertions.assertTrue(destilat.isSingleMalt());
        Assertions.assertFalse(destilat.isHeart());

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


}