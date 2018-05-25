package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.classes.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SpanishController {
    // DECLARACION DE LOS NODOS QUE SOLO SE UTILIZAN EN EL JUEGO
    @FXML   private ImageView minimize;
    @FXML   private ImageView close;
    @FXML   private ImageView imagenPregunta;
    @FXML   private JFXButton opcionB;
    @FXML   private JFXButton opcionD;
    @FXML   private JFXButton opcionC;
    @FXML   private JFXButton opcionA;
    @FXML   private Label enunciado;
    @FXML   private Label timeCount;
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
    @FXML   private Label puntosTextField;

    // ATRIBUTOS UTILIZADOS PARA LA LOGICA DEL JUEGO
    private String rutaImagen;
    private int count = 0; // Para la cuenta de los indices de las preguntas
    private int[] seconds = {30}; // Numero de segundos por pregunta
    private Quiz spanishQuiz1; // Objeto de tipo Quiz que contiene las preguntas y respuestas para el jugador 1
    private Quiz spanishQuiz2; // Objeto de tipo Quiz que contiene las preguntas y respuestas para el jugador 2
    private Partida partidaSpanish;
    private boolean ronda1 = true;
    private StageModify stageModify = new StageModify();
    private ReadWriteInfo readWriteInfo = new ReadWriteInfo();
    @FXML
    void initialize() {
        spanishQuiz1 = new Quiz("Español", Dificultad.FACIL, "src/sample/QuestionsAnswers/Spanish/Preguntas.txt",
                "src/sample/QuestionsAnswers/Spanish/Respuestas.txt",10,19);
        spanishQuiz2 = new Quiz("Español", Dificultad.FACIL, "src/sample/QuestionsAnswers/Spanish/Preguntas.txt",
                "src/sample/QuestionsAnswers/Spanish/Respuestas.txt",10,19);

        partidaSpanish = readWriteInfo.readPlayersInfo(partidaSpanish,"Español"); // LEYENDO INFORMACION Y CREANDO CON ELLA LA PARTIDA

        ArrayList<Integer> questionIndex1 = spanishQuiz1.getRandomNum();
        Iterator<Integer> iterator1 = questionIndex1.iterator();

        ArrayList<Integer> questionIndex2 = spanishQuiz2.getRandomNum();
        Iterator<Integer> iterator2 = questionIndex2.iterator();

        // PREPARANDO EL CONTADOR
        Timeline counter = downTimer();
        counter.playFromStart();

        // ASIGNANDO NOMBRE DEL JUGADOR Y EDAD
        nombreJugador.setText("JUGADOR: "+partidaSpanish.jugador1.nombre);
        edadJugador.setText("EDAD: "+String.valueOf(partidaSpanish.jugador1.edad)+" años");

        //ASIGNANDO EL ENUNCIADO, PREGUNTAS E IMAGENES INICIALES AL QUIZ
        enunciado.setText(spanishQuiz1.getProblema().get(count).getEnunciado());
        opcionA.setText(spanishQuiz1.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
        opcionB.setText(spanishQuiz1.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
        opcionC.setText(spanishQuiz1.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
        opcionD.setText(spanishQuiz1.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
        changeImage(iterator1.next()+1); //+1 porque las imagenes no empiezan en 0, sino en 1

        opcionA.setOnAction(actionEvent -> {
            manejoPregunta(0,counter,iterator1,iterator2);
        });

        opcionB.setOnAction(actionEvent -> {
            manejoPregunta(1,counter,iterator1,iterator2);
        });

        opcionC.setOnAction(actionEvent -> {
            manejoPregunta(2,counter,iterator1,iterator2);
        });

        opcionD.setOnAction(actionEvent -> {
            manejoPregunta(3,counter,iterator1,iterator2);
        });
    }

    /* METODOS CREADOS PARA ACTUAR COMO LISTENERS DE LOS NODOS */
    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
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

    private void newQuizRound(int index) {
        //Preparando la pregunta inicial del jugador 2 junto con sus respuestas
        count = 0;
        ronda1=false; //Para poder empezar la ronda 2
        seconds[0] = 30;
        nombreJugador.setText("NOMBRE: "+partidaSpanish.jugador2.nombre);
        edadJugador.setText("EDAD: "+String.valueOf(partidaSpanish.jugador2.edad+" años"));
        enunciado.setText(spanishQuiz2.getProblema().get(count).getEnunciado());
        opcionA.setText(spanishQuiz2.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
        opcionB.setText(spanishQuiz2.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
        opcionC.setText(spanishQuiz2.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
        opcionD.setText(spanishQuiz2.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
        puntosTextField.setText("PUNTUACIÓN: 0 pts");
        changeImage(index);
        clearAnswer();
    }

    private void saveSpanishResults () {
        readWriteInfo.saveMateriaResults("src/sample/QuestionsAnswers/Spanish/spanishQuizResults.txt",partidaSpanish);
    }

    private void goQuizResults() {
        stageModify.changeResultsStage(close,partidaSpanish);
    }

    private void manejoPregunta(int indiceRespuesta, Timeline countDown, Iterator<Integer> ite1, Iterator<Integer> ite2) {
        countDown.stop();

        if(ronda1){

            if (spanishQuiz1.getProblema().get(count).getListadoRespuestas()[indiceRespuesta].isCorrecta()) {
                paintAnswer(count+1, Color.GREEN);
                partidaSpanish.jugador1.puntaje.puntos += seconds[0]*4;
                puntosTextField.setText("PUNTUACIÓN: "+partidaSpanish.jugador1.puntaje.puntos+" pts");
            }
            else { paintAnswer(count+1, Color.RED);}
            seconds[0] = 30;
            count++;
            if (count < 10) {
                countDown.play();
                enunciado.setText(spanishQuiz1.getProblema().get(count).getEnunciado());
                opcionA.setText(spanishQuiz1.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                opcionB.setText(spanishQuiz1.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                opcionC.setText(spanishQuiz1.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                opcionD.setText(spanishQuiz1.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                changeImage(ite1.next() + 1);
            }
            else if (count == 10) {
                newQuizRound(ite2.next()+1); //Reiniciando el quiz
                countDown.play();
            }
        }
        else if(ronda1 == false) { // Quiz para el segundo jugador

            if (spanishQuiz2.getProblema().get(count).getListadoRespuestas()[indiceRespuesta].isCorrecta()) {
                paintAnswer(count+1, Color.GREEN);
                partidaSpanish.jugador2.puntaje.puntos += seconds[0]*4;
                puntosTextField.setText("PUNTUACIÓN: "+partidaSpanish.jugador2.puntaje.puntos+" pts");
            }
            else { paintAnswer(count+1, Color.RED);}
            seconds[0] = 30;
            count++;
            if (count < 10) {
                countDown.play();
                enunciado.setText(spanishQuiz2.getProblema().get(count).getEnunciado());
                opcionA.setText(spanishQuiz2.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                opcionB.setText(spanishQuiz2.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                opcionC.setText(spanishQuiz2.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                opcionD.setText(spanishQuiz2.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                changeImage(ite2.next() + 1);
            }
            else if (count == 10) {
                saveSpanishResults();
                goQuizResults();
            }

        }
    }
}
