package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.classes.Jugador;

import java.io.IOException;

public class ResultadosQuizController {
    @FXML
    private JFXButton menuBtn;

    @FXML
    private Label nombreGanador;

    @FXML
    private Label puntuacionGanador;

    @FXML
    private Label edadGanador;

    @FXML
    private Label nombrePerdedor;

    @FXML
    private Label puntuacionPerdedor;

    @FXML
    private Label edadPerdedor;

    @FXML
    private Label title;

    @FXML
    private Label subtitle;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView close;

    @FXML
    private Label nombreMateria;

    public void setGanador(Jugador ganador) {
        nombreGanador.setText("Jugador: "+ganador.nombre);
        puntuacionGanador.setText("Puntuación: "+String.valueOf(ganador.puntaje.puntos));
        edadGanador.setText("Edad: "+String.valueOf(ganador.edad));
    }

    public void setPerdedor(Jugador perdedor) {
        nombrePerdedor.setText("Jugador: "+perdedor.nombre);
        puntuacionPerdedor.setText("Puntuación: "+String.valueOf(perdedor.puntaje.puntos));
        edadPerdedor.setText("Edad: "+String.valueOf(perdedor.edad));
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria.setText(nombreMateria);
    }

    @FXML
    void initialize() {
       menuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goMenu();
            }
        });
    }

    private void goMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/menu.fxml"));
            loader.load();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            stage.setResizable(false);
            menuBtn.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeProgram (MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }
}
