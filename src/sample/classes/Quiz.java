package sample.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Quiz {
    protected String materia;
    protected Dificultad dificultad = Dificultad.FACIL;
    private Preguntas questions = null;
    protected ArrayList<Pregunta> problema = new ArrayList<>(); //Donde se guardaran las preguntas aleatorias
    private HashSet<Integer> randomNum;


    // CONSTRUCTORES PARA QUIZ NORMAL
    public Quiz(String materia, String textoPreguntas, String textoRespuestas) {
        this.materia = materia;
        this.questions = new Preguntas(textoPreguntas,textoRespuestas);
        randomQuestionIndex(10,19);
    }

    public Quiz(String materia, Dificultad dificultad, String textoPreguntas, String textoRespuestas) {
        this.materia = materia;
        this.questions = new Preguntas(textoPreguntas,textoRespuestas);
        this.dificultad = dificultad;
        this.questions = new Preguntas(textoPreguntas,textoRespuestas);
        randomQuestionIndex(10,19);
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
        int index, i=0;
        randomNum = new HashSet<Integer>(preguntasJuego);
        Iterator iterator;
        // Generando indices aleatorios
        do {
            randomNum.add((int) (Math.random() * ((numPreguntasDisponibles - 1) + 1)));
        } while (randomNum.size() != preguntasJuego);

        //Asignando a nuestro ArrayList preguntas y respuestas aleatorias
        iterator = randomNum.iterator();
        while(iterator.hasNext()) {
            index =  (int) iterator.next();
            problema.add(new Pregunta(questions.getListadoPreguntas().get(index).getEnunciado(),
                    questions.getListadoPreguntas().get(index).getListadoRespuestas()));
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

    public HashSet<Integer> getRandomNum() {
        return randomNum;
    }

}
