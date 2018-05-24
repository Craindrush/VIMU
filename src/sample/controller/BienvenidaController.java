package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.classes.StageModify;

import java.io.IOException;

public class BienvenidaController {
    // SOLO HE DECLARADO LOS NODOS A LOS QUE HAGO CAMBIOS
    @FXML   private ImageView minimize;
    @FXML   private ImageView close;
    @FXML   private JFXButton registroBtn;
    @FXML   private JFXButton instrucBtn;
    StageModify stageModify = new StageModify();

    @FXML
    void initialize() {
        //Usando lambdas
        registroBtn.setOnAction(actionEvent -> stageModify.changeStage("/sample/view/registro.fxml",close));
        //instrucBtn.setOnAction(actionEvent -> stageModify.changeStage("/sample/view/menu.fxml",close));
    }

    // Metodos para actuar como handlers de las imagenes al ser clickeadas
    public void minimizeProgram() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram() {
        System.exit(0);
    }

}
