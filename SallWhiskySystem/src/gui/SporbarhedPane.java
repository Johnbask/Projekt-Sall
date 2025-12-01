package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Fad;

public class SporbarhedPane extends GridPane {
    private final ListView<Fad> lwFade = new ListView<>();
    private final TextField txfFade = new TextField();
    private final Button btnSøg = new Button("Search");

    public SporbarhedPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(new Label("Fade"), 0, 0);
        this.add(lwFade, 0, 1);
        this.add(new Label("Søg Fade:"), 0, 2);
        this.add(txfFade, 0, 2);
        this.add(btnSøg, 0, 2);

        txfFade.setTranslateX(60);
        txfFade.setMaxWidth(110);
        txfFade.setPromptText("Søg Fade");
        btnSøg.setTranslateX(180);

        updateLwFade();
    }

    private void updateLwFade() {
        if (txfFade.getCharacters().isEmpty()) {
            lwFade.getItems().setAll(Controller.getFade());
        }
    }
}
