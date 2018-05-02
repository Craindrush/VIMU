package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label title;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView close;

    @FXML
    private ImageView matematicas;

    @FXML
    private ImageView spanish;

    @FXML
    private ImageView geografia;

    @FXML
    private Label labelMath;

    @FXML
    private Label labelGeo;

    @FXML
    private Label labelSpanish;

    @FXML
    private Hyperlink howToMath;

    @FXML
    private Hyperlink howToSpanish;

    @FXML
    private Hyperlink howToGeo;

    @FXML
    private Label subtitle;

    @FXML
    void initialize() {

    }

    public void closeProgram (MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void playMath(MouseEvent mouseEvent) {

        registroUsuario();
        matematicas.getScene().getWindow().hide();
    }

    public void playSpanish(MouseEvent mouseEvent) {
        registroUsuario();
        spanish.getScene().getWindow().hide();

    }

    public void playGeo(MouseEvent mouseEvent) {

        registroUsuario();
        geografia.getScene().getWindow().hide();
    }

    private void registroUsuario() {
        Stage registroStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/view/registro.fxml"));
            Scene scene = new Scene(root);
            registroStage.setScene(scene);
            registroStage.initStyle(StageStyle.UNDECORATED);

            registroStage.show();
            registroStage.setResizable(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
