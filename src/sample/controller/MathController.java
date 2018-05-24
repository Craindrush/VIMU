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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.classes.*;

import java.io.*;
import java.util.Iterator;

public class MathController {

    @FXML   private ImageView minimize;
    @FXML   private ImageView close;
    @FXML   private JFXButton opcionB;
    @FXML   private JFXButton opcionD;
    @FXML   private JFXButton opcionC;
    @FXML   private JFXButton opcionA;
    @FXML   private Label timeCount;
    @FXML   private Label operacionProblema;
    @FXML   private Circle questionStatus1;
    @FXML   private Circle questionStatus2;
    @FXML   private Circle questionStatus3;
    @FXML   private Circle questionStatus4;
    @FXML   private Circle questionStatus5;
    @FXML   private Circle questionStatus6;
    @FXML   private Circle questionStatus7;
    @FXML   private Circle questionStatus8;
    @FXML   private Circle questionStatus9;
    @FXML   private Circle questionStatus10;
    @FXML   private Label edadJugador;
    @FXML   private Label nombreJugador;
    @FXML   private Label puntuacion;

    private int count = 0; // Para la cuenta de los indices de las preguntas
    private int[] seconds = {30}; // Numero de segundos por pregunta
    private AutomaticQuiz mathQuiz1; // Objeto de tipo Quiz que contiene las preguntas y respuestas para el jugador 1
    private AutomaticQuiz mathQuiz2; // Objeto de tipo Quiz que contiene las preguntas y respuestas para el jugador 2
    private Partida partidaMath;
    private boolean ronda1 = true;
    private StageModify stageModify = new StageModify();
    private ReadWriteInfo readWriteInfo = new ReadWriteInfo();

    @FXML
    void initialize() {
        //Esta en el mismo thread principal que nuestra aplicacion de javafx
        mathQuiz1 = new AutomaticQuiz("Matemáticas");
        mathQuiz2 = new AutomaticQuiz("Matemáticas");

        partidaMath = readWriteInfo.readPlayersInfo(partidaMath,"Geografía"); // LEYENDO INFORMACION Y CREANDO CON ELLA LA PARTIDA

        // PREPARANDO EL CONTADOR
        Timeline counter = downTimer();
        counter.playFromStart();

        // ASIGNANDO NOMBRE DEL JUGADOR Y EDAD
        nombreJugador.setText("JUGADOR: "+partidaMath.jugador1.nombre);
        edadJugador.setText("EDAD: "+String.valueOf(partidaMath.jugador1.edad)+" años");

        //ASIGNANDO EL ENUNCIADO, PREGUNTAS E IMAGENES INICIALES AL QUIZ
        operacionProblema.setText(mathQuiz1.getProblema().get(count).getEnunciado());
        opcionA.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
        opcionB.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
        opcionC.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
        opcionD.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());

            opcionA.setOnAction(actionEvent -> {
                manejoPregunta(0,counter);
            });

            opcionB.setOnAction(actionEvent -> {
                manejoPregunta(0,counter);
            });

            opcionC.setOnAction(actionEvent -> {
                manejoPregunta(0,counter);
            });

            opcionD.setOnAction(actionEvent -> {
                manejoPregunta(0,counter);
            });

    }

    /* METODOS CREADOS PARA ACTUAR COMO LISTENERS DE LOS NODOS */
    public void minimizeProgram() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram() {
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

    private void paintAnswer(int questionNumber, Paint color) {
        switch (questionNumber) {
            case 1: questionStatus1.setFill(color);
                break;
            case 2: questionStatus2.setFill(color);
                break;
            case 3: questionStatus3.setFill(color);
                break;
            case 4: questionStatus4.setFill(color);
                break;
            case 5: questionStatus5.setFill(color);
                break;
            case 6: questionStatus6.setFill(color);
                break;
            case 7: questionStatus7.setFill(color);
                break;
            case 8: questionStatus8.setFill(color);
                break;
            case 9: questionStatus9.setFill(color);
                break;
            case 10: questionStatus10.setFill(color);
                break;
        }
    }

    private void clearAnswer() {
        questionStatus1.setFill(Color.WHITE);
        questionStatus2.setFill(Color.WHITE);
        questionStatus3.setFill(Color.WHITE);
        questionStatus4.setFill(Color.WHITE);
        questionStatus5.setFill(Color.WHITE);
        questionStatus6.setFill(Color.WHITE);
        questionStatus7.setFill(Color.WHITE);
        questionStatus8.setFill(Color.WHITE);
        questionStatus9.setFill(Color.WHITE);
        questionStatus10.setFill(Color.WHITE);
    }

    private void newQuizRound() {
        //Preparando la pregunta inicial del jugador 2 junto con sus respuestas
        count = 0;
        ronda1=false; //Para poder empezar la ronda 2
        seconds[0] = 30;
        nombreJugador.setText("NOMBRE: "+partidaMath.jugador2.nombre);
        edadJugador.setText("EDAD: "+String.valueOf(partidaMath.jugador2.edad+" años"));
        operacionProblema.setText(mathQuiz2.getProblema().get(count).getEnunciado());
        opcionA.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
        opcionB.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
        opcionC.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
        opcionD.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
        puntuacion.setText("PUNTUACIÓN: 0 pts");
        clearAnswer();
    }

    private void saveMathResults () {
        readWriteInfo.saveMateriaResults("src/sample/QuestionsAnswers/Math/mathQuizResults.txt",partidaMath);
    }

    private void goQuizResults() {
        stageModify.changeResultsStage(close,partidaMath);
    }


    private void manejoPregunta(int indiceRespuesta, Timeline countDown) {
        countDown.stop();

        if(ronda1){

            if (mathQuiz1.getProblema().get(count).getListadoRespuestas()[indiceRespuesta].isCorrecta()) {
                paintAnswer(count+1, Color.GREEN);
                partidaMath.jugador1.puntaje.puntos += seconds[0]*4;
                puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador1.puntaje.puntos+" pts");
            }
            else { paintAnswer(count+1, Color.RED);}
            seconds[0] = 30;
            count++;
            if (count < 10) {
                countDown.play();
                operacionProblema.setText(mathQuiz1.getProblema().get(count).getEnunciado());
                opcionA.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                opcionB.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                opcionC.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                opcionD.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
            }
            else if (count == 10) {
                newQuizRound(); //Reiniciando el quiz
                countDown.play();
            }
        }
        else if(ronda1 == false) { // Quiz para el segundo jugador

            if (mathQuiz2.getProblema().get(count).getListadoRespuestas()[indiceRespuesta].isCorrecta()) {
                paintAnswer(count+1, Color.GREEN);
                partidaMath.jugador2.puntaje.puntos += seconds[0]*4;
                puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador2.puntaje.puntos+" pts");
            }
            else { paintAnswer(count+1, Color.RED);}
            seconds[0] = 30;
            count++;
            if (count < 10) {
                countDown.play();
                operacionProblema.setText(mathQuiz2.getProblema().get(count).getEnunciado());
                opcionA.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                opcionB.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                opcionC.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                opcionD.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
            }
            else if (count == 10) {
                saveMathResults();
                goQuizResults();
            }
        }
    }
}
