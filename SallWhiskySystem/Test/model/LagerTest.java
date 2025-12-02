package model;

import controller.Controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import storage.Storage;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    @Test
    void getLagerId() {

        Lager lager = new Lager("testLager","");
        Assertions.assertEquals(lager.getLagerId(),1);

    }
}