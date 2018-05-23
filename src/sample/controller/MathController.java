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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.classes.AutomaticQuiz;
import sample.classes.Jugador;
import sample.classes.Partida;
import sample.classes.Quiz;

import java.io.*;

public class MathController {

    @FXML
    private Label tituloMateria;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView close;

    @FXML
    private JFXButton opcionB;

    @FXML
    private JFXButton opcionD;

    @FXML
    private JFXButton opcionC;

    @FXML
    private JFXButton opcionA;

    @FXML
    private Label secondsLabel;

    @FXML
    private Label timeCount;

    @FXML
    private Label puntuacion;

    @FXML
    private ImageView timerIcon;

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

    @FXML
    private Label operacionProblema;

    @FXML
    private Label nombreJugador;

    @FXML
    private Label edadJugador;

    private int count = 0; // Para la cuenta de los indices de las preguntas

    private int[] seconds = {30}; // Numero de segundos por pregunta

    private AutomaticQuiz mathQuiz1; // Objeto de tipo Quiz que contiene las preguntas y respuestas para el jugador 1

    private AutomaticQuiz mathQuiz2; // Objeto de tipo Quiz que contiene las preguntas y respuestas para el jugador 2

    private Partida partidaMath;

    private boolean ronda1 = true;

    @FXML
    void initialize() {
        //Esta en el mismo thread principal que nuestra aplicacion de javafx
        mathQuiz1 = new AutomaticQuiz("Matemáticas");
        mathQuiz2 = new AutomaticQuiz("Matemáticas");

        readPlayersInfo(); //Recuperando la informacion de los jugadores por medio de txt

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


        opcionA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                counter.stop();
                if(ronda1){
                    if (mathQuiz1.getProblema().get(count).getListadoRespuestas()[0].isCorrecta()) {
                        correctAnswer(count+1);
                        partidaMath.jugador1.puntaje.puntos += seconds[0]*4;
                        puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador1.puntaje.puntos+" pts");
                    }
                    else { wrongAnswer(count+1);}
                    seconds[0] = 30;
                    count++;
                    if (count < 10) {
                        counter.play();
                        operacionProblema.setText(mathQuiz1.getProblema().get(count).getEnunciado());
                        opcionA.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                        opcionB.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                        opcionC.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                        opcionD.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                    }
                    else if (count == 10) {
                        newQuizRound(); //Reiniciando el quiz
                        counter.play();
                    }
                }
                else if(ronda1 == false) { // Quiz para el segundo jugador

                    if (mathQuiz2.getProblema().get(count).getListadoRespuestas()[0].isCorrecta()) {
                        correctAnswer(count+1);
                        partidaMath.jugador2.puntaje.puntos += seconds[0]*4;
                        puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador2.puntaje.puntos+" pts");
                    }
                    else { wrongAnswer(count+1);}
                    seconds[0] = 30;
                    count++;
                    if (count < 10) {
                        counter.play();
                        operacionProblema.setText(mathQuiz2.getProblema().get(count).getEnunciado());
                        opcionA.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                        opcionB.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                        opcionC.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                        opcionD.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                    }
                    else if (count == 10) {
                        saveSpanishResults();
                        goQuizResults();
                    }
                }
            }
        });

        opcionB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                counter.stop();
                if(ronda1){
                    if (mathQuiz1.getProblema().get(count).getListadoRespuestas()[1].isCorrecta()) {
                        correctAnswer(count+1);
                        partidaMath.jugador1.puntaje.puntos += seconds[0]*4;
                        puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador1.puntaje.puntos+" pts");
                    }
                    else { wrongAnswer(count+1);}
                    seconds[0] = 30;
                    count++;
                    if (count < 10) {
                        counter.play();
                        operacionProblema.setText(mathQuiz1.getProblema().get(count).getEnunciado());
                        opcionA.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                        opcionB.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                        opcionC.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                        opcionD.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                    }
                    else if (count == 10) {
                        newQuizRound(); //Reiniciando el quiz
                        counter.play();
                    }
                }
                else if(ronda1 == false) { // Quiz para el segundo jugador

                    if (mathQuiz2.getProblema().get(count).getListadoRespuestas()[1].isCorrecta()) {
                        correctAnswer(count+1);
                        partidaMath.jugador2.puntaje.puntos += seconds[0]*4;
                        puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador2.puntaje.puntos+" pts");
                    }
                    else { wrongAnswer(count+1);}
                    seconds[0] = 30;
                    count++;
                    if (count < 10) {
                        counter.play();
                        operacionProblema.setText(mathQuiz2.getProblema().get(count).getEnunciado());
                        opcionA.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                        opcionB.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                        opcionC.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                        opcionD.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                    }
                    else if (count == 10) {
                        saveSpanishResults();
                        goQuizResults();
                    }
                }
            }
        });

        opcionC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                counter.stop();
                if(ronda1){
                    if (mathQuiz1.getProblema().get(count).getListadoRespuestas()[1].isCorrecta()) {
                        correctAnswer(count+1);
                        partidaMath.jugador1.puntaje.puntos += seconds[0]*4;
                        puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador1.puntaje.puntos+" pts");
                    }
                    else { wrongAnswer(count+1);}
                    seconds[0] = 30;
                    count++;
                    if (count < 10) {
                        counter.play();
                        operacionProblema.setText(mathQuiz1.getProblema().get(count).getEnunciado());
                        opcionA.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                        opcionB.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                        opcionC.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                        opcionD.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                    }
                    else if (count == 10) {
                        newQuizRound(); //Reiniciando el quiz
                        counter.play();
                    }
                }
                else if(ronda1 == false) { // Quiz para el segundo jugador

                    if (mathQuiz2.getProblema().get(count).getListadoRespuestas()[2].isCorrecta()) {
                        correctAnswer(count+1);
                        partidaMath.jugador2.puntaje.puntos += seconds[0]*4;
                        puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador2.puntaje.puntos+" pts");
                    }
                    else { wrongAnswer(count+1);}
                    seconds[0] = 30;
                    count++;
                    if (count < 10) {
                        counter.play();
                        operacionProblema.setText(mathQuiz2.getProblema().get(count).getEnunciado());
                        opcionA.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                        opcionB.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                        opcionC.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                        opcionD.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                    }
                    else if (count == 10) {
                        saveSpanishResults();
                        goQuizResults();
                    }
                }
            }
        });

        opcionD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                counter.stop();
                if(ronda1){
                    if (mathQuiz1.getProblema().get(count).getListadoRespuestas()[3].isCorrecta()) {
                        correctAnswer(count+1);
                        partidaMath.jugador1.puntaje.puntos += seconds[0]*4;
                        puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador1.puntaje.puntos+" pts");
                    }
                    else { wrongAnswer(count+1);}
                    seconds[0] = 30;
                    count++;
                    if (count < 10) {
                        counter.play();
                        operacionProblema.setText(mathQuiz1.getProblema().get(count).getEnunciado());
                        opcionA.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                        opcionB.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                        opcionC.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                        opcionD.setText(mathQuiz1.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                    }
                    else if (count == 10) {
                        newQuizRound(); //Reiniciando el quiz
                        counter.play();
                    }
                }
                else if(ronda1 == false) { // Quiz para el segundo jugador

                    if (mathQuiz2.getProblema().get(count).getListadoRespuestas()[3].isCorrecta()) {
                        correctAnswer(count+1);
                        partidaMath.jugador2.puntaje.puntos += seconds[0]*4;
                        puntuacion.setText("PUNTUACIÓN: "+partidaMath.jugador2.puntaje.puntos+" pts");
                    }
                    else { wrongAnswer(count+1);}
                    seconds[0] = 30;
                    count++;
                    if (count < 10) {
                        counter.play();
                        operacionProblema.setText(mathQuiz2.getProblema().get(count).getEnunciado());
                        opcionA.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[0].getOpcionTexto());
                        opcionB.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[1].getOpcionTexto());
                        opcionC.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[2].getOpcionTexto());
                        opcionD.setText(mathQuiz2.getProblema().get(count).getListadoRespuestas()[3].getOpcionTexto());
                    }
                    else if (count == 10) {
                        saveSpanishResults();
                        goQuizResults();
                    }
                }
            }
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

    private void correctAnswer(int questionNumber) {
        switch (questionNumber) {
            case 1: questionStatus1.setFill(Color.GREEN);
                break;
            case 2: questionStatus2.setFill(Color.GREEN);
                break;
            case 3: questionStatus3.setFill(Color.GREEN);
                break;
            case 4: questionStatus4.setFill(Color.GREEN);
                break;
            case 5: questionStatus5.setFill(Color.GREEN);
                break;
            case 6: questionStatus6.setFill(Color.GREEN);
                break;
            case 7: questionStatus7.setFill(Color.GREEN);
                break;
            case 8: questionStatus8.setFill(Color.GREEN);
                break;
            case 9: questionStatus9.setFill(Color.GREEN);
                break;
            case 10: questionStatus10.setFill(Color.GREEN);
                break;
        }
    }

    private void wrongAnswer(int questionNumber) {
        switch (questionNumber) {
            case 1: questionStatus1.setFill(Color.RED);
                break;
            case 2: questionStatus2.setFill(Color.RED);
                break;
            case 3: questionStatus3.setFill(Color.RED);
                break;
            case 4: questionStatus4.setFill(Color.RED);
                break;
            case 5: questionStatus5.setFill(Color.RED);
                break;
            case 6: questionStatus6.setFill(Color.RED);
                break;
            case 7: questionStatus7.setFill(Color.RED);
                break;
            case 8: questionStatus8.setFill(Color.RED);
                break;
            case 9: questionStatus9.setFill(Color.RED);
                break;
            case 10: questionStatus10.setFill(Color.RED);
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

    private void readPlayersInfo() {
        String[] playersInfo = new String[2];
        String[] player1;
        String[] player2;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/sample/QuestionsAnswers/Math/players.txt")), "ISO-8859-1"));
            playersInfo[0] = br.readLine();
            playersInfo[1] = br.readLine();

            player1 = playersInfo[0].split(";");
            player2 = playersInfo[1].split(";");

            partidaMath = new Partida("Matemáticas",new Jugador(player1[1],Integer.parseInt(player1[2]),
                    player1[0]), new Jugador(player2[1],Integer.parseInt(player2[2]),
                    player2[0]));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        clearAnswer(); // Borrar los circulos rellenos
    }

    private void saveSpanishResults () {
        File file = null;
        file = new File("src/sample/QuestionsAnswers/Math/mathQuizResults.txt");

        try (FileWriter fileWriter = new FileWriter(file)){
            if(partidaMath.jugador1.puntaje.puntos > partidaMath.jugador2.puntaje.puntos) {
                fileWriter.write(partidaMath.jugador1.puntaje.puntos+";"+
                        partidaMath.jugador1.nombre+";"+partidaMath.jugador1.genero+";"+
                        String.valueOf(partidaMath.jugador1.edad+"\n"));

                fileWriter.write(partidaMath.jugador2.puntaje.puntos+";"+
                        partidaMath.jugador2.nombre+";"+partidaMath.jugador2.genero+";"+
                        String.valueOf(partidaMath.jugador2.edad+"\n"));
            }
            else {
                fileWriter.write(partidaMath.jugador2.puntaje.puntos+";"+
                        partidaMath.jugador2.nombre+";"+partidaMath.jugador2.genero+";"+
                        String.valueOf(partidaMath.jugador2.edad+"\n"));

                fileWriter.write(partidaMath.jugador1.puntaje.puntos+";"+
                        partidaMath.jugador1.nombre+";"+partidaMath.jugador1.genero+";"+
                        String.valueOf(partidaMath.jugador1.edad+"\n"));
            }
        } catch (Exception e) {}
    }

    private void goQuizResults() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/resultadosQuiz.fxml"));
            loader.load();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);

            ResultadosQuizController resultadosQuizController = loader.getController();
            if (partidaMath.jugador1.puntaje.puntos > partidaMath.jugador2.puntaje.puntos) {
                resultadosQuizController.setGanador(partidaMath.jugador1);
                resultadosQuizController.setPerdedor(partidaMath.jugador2);
            }
            else {
                resultadosQuizController.setGanador(partidaMath.jugador2);
                resultadosQuizController.setPerdedor(partidaMath.jugador1);
            }
            resultadosQuizController.setNombreMateria(partidaMath.tema);
            stage.show();
            stage.setResizable(false);
            opcionA.getScene().getWindow().hide();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
