package sample.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import sample.classes.StageModify;


public class InstruccionesController {
    @FXML   private ImageView minimize;
    @FXML   private ImageView close;
    @FXML   private ImageView back;

    StageModify stageModify = new StageModify();


    public void minimizeProgram() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram() {
        System.exit(0);
    }

    public void backMenu() {stageModify.changeStage("/sample/view/bienvenida.fxml",close); }
}
