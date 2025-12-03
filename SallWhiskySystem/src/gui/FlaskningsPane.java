package gui;

import com.sun.javafx.scene.control.DoubleField;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Fad;
import model.Vand;

public class FlaskningsPane extends GridPane {
    public FlaskningsPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        intContent();
    }

    private final TextField tFMakeName = new TextField();
    private final ComboBox<Fad> cbFade = new ComboBox<>();
    private final DoubleField dFLiterFraFad = new DoubleField();
    private final DoubleField dFLiterVand = new DoubleField();
    private final ComboBox<Vand> cbVandKilde = new ComboBox<>();
    private final DoubleField dFFlaskeStørelse = new DoubleField();
    private final TextArea tAHistorien = new TextArea();
    private final Button bGenrateStory = new Button("Genrate Story");
    private final Button bFlaskkiefy = new Button("Flaskiefy");

    private void intContent() {

        Label lFlaskning = new Label("Flaskning");
        this.add(lFlaskning,0,0,2,2);
        Label lHistorie = new Label("Historien");
        this.add(lHistorie,3,0,2,2);

        this.add(tAHistorien,3,3,1,7);
        tAHistorien.setEditable(false);
        tAHistorien.setMinWidth(300);
        tAHistorien.setMinHeight(300);

        Label lMakeNavn = new Label("Make name");
        this.add(lMakeNavn,0,3);
        this.add(tFMakeName,1,3);

        Label lVælgFad = new Label("Vælg Fad");
        this.add(lVælgFad,0,4);
        this.add(cbFade,1,4);
        cbFade.getItems().setAll(Controller.getFildFad());

        Label lLiterFraFad = new Label("Liter fra fad");
        this.add(lLiterFraFad,0,5);
        this.add(dFLiterFraFad,1,5);

        Label lLiterVand = new Label("Liter vand tilføjet i alt");
        this.add(lLiterVand,0,6);
        this.add(dFLiterVand,1,6);

        Label lVandKilde = new Label("Vandkilde");
        this.add(lVandKilde,0,7);
        this.add(cbVandKilde,1,7);
        cbVandKilde.getItems().setAll(Controller.getVands());

        Label lFlaskeStørelse = new Label("Flaske størlese L");
        this.add(lFlaskeStørelse,0,8);
        this.add(dFFlaskeStørelse,1,8);

        this.add(bGenrateStory,0,9);
        bGenrateStory.setOnAction(event -> this.genrateStory());

        this.add(bFlaskkiefy,1,9);
        bFlaskkiefy.setOnAction(event -> this.Flaskkiefy());

    }

    private void Flaskkiefy() {
    }

    private void genrateStory() {
        tAHistorien.setText("Der var en gang en gang og for enden af den gang var der en dør");
    }

}
