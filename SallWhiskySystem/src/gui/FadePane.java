package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.scene.control.*;

public class FadePane extends GridPane {
    private final Label lblFade = new Label("Fade");

    public FadePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        this.add(lblFade, 0, 0, 2, 1);
        lblFade.setStyle("-fx-font-size: 24px");

        FirstSection();

    }

    // fields for first section

    private void FirstSection() {
        TableView<>
    }

}
