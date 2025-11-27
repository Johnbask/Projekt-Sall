package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Fad;

public class LagerPane extends GridPane {
    private final ListView<Fad> lWfade = new ListView<>();
    private final TextField tFlager = new TextField();
    private final TextField tFReol = new TextField();
    private final TextField tFHylde = new TextField();

    public LagerPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lFade = new Label("Fade");
        this.add(lFade,0,0);

        this.add(tFlager,2,1);
        Label lLager = new Label("Lager id");
        this.add(lLager,1,1);

        this.add(tFReol,2,2);
        Label lReol = new Label("Reol id");
        this.add(lReol,1,2);

        this.add(tFHylde,2,3);
        Label lHylde = new Label("Hylde id");
        this.add(lHylde,1,3);

        this.add(lWfade,0,1,1,6);

        Label LTest2 = new Label("Test 2");
        this.add(LTest2,3,6);

        updateLWFade();
    }

    private void updateLWFade() {
        if(tFlager.getCharacters().isEmpty()){
            lWfade.getItems().setAll(Controller.getFade());
        } else if (!tFlager.getCharacters().isEmpty()&&!tFReol.getCharacters().isEmpty()&&!tFHylde.getCharacters().isEmpty()){
            lWfade.getItems().setAll(Controller.getLager(Integer.parseInt(tFlager.getText())).getReol(Integer.parseInt(tFReol.getText())).getHylde(Integer.parseInt(tFHylde.getText())).getFade());
        } else if (tFHylde.getCharacters().isEmpty()) {
            lWfade.getItems().setAll(Controller.getLager(tFlager.getText()).getReol(Integer.parseInt(tFReol.getText())).getFade());
        } else if (!tFlager.getText().isEmpty()) {
            lWfade.getItems().setAll(Controller.getLager(tFlager.getText()).getFade());
        }
        else{
            System.out.println("ERROR");// skal erstats med try catch
        }

    }



}
