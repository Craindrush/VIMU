package sample.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Quiz {
    protected String materia;
    protected Dificultad dificultad = Dificultad.FACIL;
    private Preguntas questions = null;
    protected ArrayList<Pregunta> problema = new ArrayList<>(); //Donde se guardaran las preguntas aleatorias
    private ArrayList<Integer> randomIndex = new ArrayList<>();


    // CONSTRUCTORES PARA QUIZ NORMAL
    public Quiz(String materia, String textoPreguntas, String textoRespuestas, int numPreguntasJuego, int numPreguntasArchivo) {
        this.materia = materia;
        this.questions = new Preguntas(textoPreguntas,textoRespuestas);
        randomQuestionIndex(numPreguntasJuego,numPreguntasArchivo);
    }

    public Quiz(String materia, Dificultad dificultad, String textoPreguntas, String textoRespuestas, int numPreguntasJuego, int numPreguntasArchivo) {
        this.materia = materia;
        this.questions = new Preguntas(textoPreguntas,textoRespuestas);
        this.dificultad = dificultad;
        randomQuestionIndex(numPreguntasJuego,numPreguntasArchivo);
    }

    //CONSTRUCTORES PARA AUTOMATIC QUIZ
    public Quiz() {

    }

    public Quiz(String materia) {
        this.materia = materia;
    }

    public Quiz(String materia, Dificultad dificultad) {
        this.materia = materia;
        this.dificultad = dificultad;
    }

    // METODO PARA SELECCIONAR PREGUNTAS ALEATORIAS
    private void randomQuestionIndex(int preguntasJuego, int numPreguntasDisponibles) {
        ArrayList<Integer> randomNumber = new ArrayList<>();
        //Llenando el AL de los indices de cada problema
        for (int i=0; i<numPreguntasDisponibles; i++) {
            randomNumber.add(i);
        }
        Collections.shuffle(randomNumber);

        //Utilizando los numeros shuffleleados del randomNumber para almacenarlo
        //en el AL que contendra las preguntas y respuestas aleatorias
        for (int j=0; j<preguntasJuego; j++) {
            problema.add(new Pregunta(questions.getListadoPreguntas().get(randomNumber.get(j)).getEnunciado(),
                    questions.getListadoPreguntas().get(randomNumber.get(j)).getListadoRespuestas()));
            randomIndex.add(randomNumber.get(j));

        }
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public ArrayList<Pregunta> getProblema() {
        return problema;
    }

    public ArrayList<Integer> getRandomNum() {
        return randomIndex;
    }

}
