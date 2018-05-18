package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class SpanishController {

    @FXML
    private Label tituloMateria;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView close;

    @FXML
    private ImageView imagenPregunta;

    @FXML
    private JFXButton opcionB;

    @FXML
    private JFXButton opcionD;

    @FXML
    private JFXButton opcionC;

    @FXML
    private JFXButton opcionA;

    @FXML
    private Label enunciado;

    @FXML
    private ImageView timerIcon;

    @FXML
    private Label timeCount;

    @FXML
    private Label secondsLabel;

    @FXML
    private Circle questionStatus1;

    @FXML
    private Circle questionStatus2;

    @FXML
    private Circle questionStatus3;

    @FXML
    private Circle questionStatus4;

    @FXML
    private Circle questionStatus5;

    @FXML
    private Circle questionStatus6;

    @FXML
    private Circle questionStatus7;

    @FXML
    private Circle questionStatus8;

    @FXML
    private Circle questionStatus9;

    @FXML
    private Circle questionStatus10;

    private String rutaImagen;

    private int[] seconds = {30};

    @FXML
    void initialize() {
        //Creando la collecion que contendra nuestros indices aleaotrios de las preguntas
        HashSet<Integer> questionIndex = randomQuestionIndex(10, 19);
        Iterator<Integer> iterator = questionIndex.iterator();
        changeImage(iterator.next());

        Timeline counter = downTimer();
        counter.playFromStart();

        opcionA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                counter.stop();
                if (iterator.hasNext() == true) {
                    if (false /*valor de la pregunta*/ == true) {
                        changeImage(iterator.next());
                        seconds[0] = 30;
                    }
                    counter.play();
                } else minimizeWindow();
            }
        });

        opcionB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (iterator.hasNext() == true) {
                    changeImage(iterator.next());
                } else minimizeWindow();
            }
        });

        opcionC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (iterator.hasNext() == true) {
                    changeImage(iterator.next());
                } else minimizeWindow();
            }
        });

        opcionD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (iterator.hasNext() == true) {
                    changeImage(iterator.next());
                } else minimizeWindow();
            }
        });

    }

    /* METODOS CREADOS PARA ACTUAR COMO LISTENERS DE LOS NODOS */
    public void minimizeProgram(MouseEvent mouseEvent) {
        minimizeWindow();
    }

    public void closeProgram(MouseEvent mouseEvent) {
        System.exit(0);
    }

    /* METODOS USADOS PARA LA LOGICA/FUNCIONALIDAD DEL JUEGO */

    //Metodo que prepara una animacion de un temporizador de reloj. Recibe por parametro la duracion del temporizador
    private Timeline downTimer() {

        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                timeCount.setText(Integer.toString(seconds[0]));
                if (seconds[0] <= 0) {
                    time.stop(); //Para que se detenga el downtimer al llegar a 0
                }
                seconds[0]--;
            }
        });

        time.getKeyFrames().add(frame);
        return time;
    }

    private void changeImage(int i) {
        rutaImagen = "/sample/assets/quizEspañol/pregunta" + Integer.toString(i) + ".jpg";
        imagenPregunta.setImage(new Image(rutaImagen));
    }

    private HashSet<Integer> randomQuestionIndex(int preguntasJuego, int numPreguntasDisponibles) {
        HashSet<Integer> randomNum = new HashSet<Integer>(preguntasJuego);
        do {
            randomNum.add((int) (Math.random() * ((numPreguntasDisponibles - 1) + 1)) + 1);
        } while (randomNum.size() != preguntasJuego);
        return randomNum;
    }

    private void minimizeWindow() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }
}
