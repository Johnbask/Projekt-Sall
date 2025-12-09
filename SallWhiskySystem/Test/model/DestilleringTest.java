package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DestilleringTest {

    @Test
    void testConstructor(){
        Destillering destillering1 = new Destillering(LocalDate.of(1999,9,9),LocalDate.of(1999,10,10),2000,64,new Medarbejder("Ruben","Guden"),"atomer","gas","\"let there be whiskye\"",new Vand("ungdommens kilde"));
        Assertions.assertEquals(1,destillering1.getNewMakeId());
        Destillering destillering2 = new Destillering(LocalDate.of(1999,9,9),LocalDate.of(1999,10,10),2000,64,new Medarbejder("Ruben","Guden"),"atomer","gas","\"let there be whiskye\"",new Vand("ungdommens kilde"));
        Assertions.assertEquals(2,destillering2.getNewMakeId());
        Destillering destillering3 = new Destillering(LocalDate.of(1999,9,9),LocalDate.of(1999,10,10),2000,64,new Medarbejder("Ruben","Guden"),"atomer","gas","\"let there be whiskye\"",new Vand("ungdommens kilde"));
        Assertions.assertEquals(3,destillering3.getNewMakeId());

    }

}