package model;

import controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.Storage;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {


    // bare en test til at teste at vi kunne teste
    @Test
    void getLagerId() {

        Lager lager = new Lager("testLager","");
        Assertions.assertEquals(lager.getLagerId(),1);

    }
}