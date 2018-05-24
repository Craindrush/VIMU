package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.classes.StageModify;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuController{

    @FXML   private ImageView minimize;
    @FXML   private ImageView close;
    @FXML   private ImageView home;
    @FXML   private ImageView matematicas;
    @FXML   private ImageView spanish;
    @FXML   private ImageView geografia;
    @FXML   private Hyperlink rankingMath;
    @FXML   private Hyperlink rankingSpanish;
    @FXML   private Hyperlink rankingGeo;

    private StageModify stageModify;

    public MenuController() {
        stageModify = new StageModify();
    }

    @FXML
    void initialize() {
        rankingGeo.setOnAction(actionEvent -> stageModify.changeStage("/sample/view/topRankingGeo.fxml",close));

        rankingMath.setOnAction(actionEvent -> stageModify.changeStage("/sample/view/topRankingMath.fxml",close));

        rankingSpanish.setOnAction(actionEvent -> stageModify.changeStage("/sample/view/topRankingSpanish.fxml",close));
    }

    public void closeProgram () {
        System.exit(0);
    }

    public void minimizeProgram() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void playMath() {
        stageModify.changeStage("/sample/view/mathQuiz.fxml",matematicas);
    }

    public void playSpanish() {
        stageModify.changeStage("/sample/view/spanishQuiz.fxml",spanish);
    }

    public void playGeo() {
        stageModify.changeStage("/sample/view/geographyQuiz.fxml",geografia);
    }

    public void returnHome() {
        if (confirmationAlert()) stageModify.changeStage("/sample/view/bienvenida.fxml",home);
    }

    private boolean confirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de confirmación");
        alert.setHeaderText("Al salir al menú principal tendrá que pasar de nuevo por el proceso de registro de usuario para poder jugar." );
        alert.setContentText("¿Está de seguro de esta desición?");

        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.get() == ButtonType.OK) return true;
            else return false;
    }
}
