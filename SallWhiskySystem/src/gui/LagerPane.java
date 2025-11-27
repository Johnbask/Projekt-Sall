package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Lager;

public class LagerPane extends GridPane {

    public LagerPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lTest1 = new Label("Test");
        this.add(lTest1,0,0);

        Label LTest2 = new Label("Test 2");
        this.add(LTest2,3,6);
    }
}
