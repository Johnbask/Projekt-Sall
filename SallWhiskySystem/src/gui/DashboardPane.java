package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import java.awt.*;

public class DashboardPane extends GridPane {

    private final Button btnCreateDestillering = new Button("Create Destillering");
    private final Button btnCreateFad = new Button("Create Fad");
    private final Button btnSporbarhed = new Button("Sporbarhed");

    public DashboardPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        // Labels
        Label lblTotalDestilleringer = new Label("Total Destilleringer: ");
        this.add(lblTotalDestilleringer, 0, 0);

        Label lblTotalFade = new Label("Total Fade: ");
        this.add(lblTotalFade, 1, 0);

        Label lblTotalFadeKlar = new Label("Total Fade Klar Til Tapping: ");
        this.add(lblTotalFadeKlar, 2, 0);


        // Buttons
        this.add(btnCreateDestillering, 0, 1);

        this.add(btnCreateFad, 1, 1);

        this.add(btnSporbarhed, 2, 1);

        Label lblFjern = new Label("Fjern?");
        lblFjern.setStyle("-fx-font-size: 24");

        this.add(lblFjern, 1, 3);

    }
}
