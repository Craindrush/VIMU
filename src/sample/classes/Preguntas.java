package sample.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Preguntas {
    private ArrayList<Pregunta> listadoPreguntas = new ArrayList<Pregunta>();

    private String questionFile;
    private String answerFile;
    // Solo admite 2 parametros

    public Preguntas(String questionFile, String answerFile) {
        this.questionFile = questionFile;
        this.answerFile = answerFile;
        readPreguntas();
        separateAnswers(readAnswers());
    }
    public ArrayList<Pregunta> getListadoPreguntas() {
        return listadoPreguntas;
    }

    private void readPreguntas() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(questionFile)), "ISO-8859-1"));
            String linea;
            while ((linea = br.readLine()) != null) {
                listadoPreguntas.add(new Pregunta(linea));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private ArrayList<String> readAnswers() {
        ArrayList<String> respuestas = new ArrayList<>();
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(answerFile)), "ISO-8859-1"));
            String linea;
            while ((linea = br.readLine()) != null) {
                respuestas.add(linea);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuestas;
    }

    private void separateAnswers (ArrayList<String> respuestas) {

        Iterator iterator = respuestas.iterator();
        String[] buff;
        int index = 0;
        do {
            buff = respuestas.get(index).split(";");
            for (int i=0; i<4; i++) { // De 0 a 3 porque son 4 incisos
                if (buff[i].startsWith("*")) {
                    listadoPreguntas.get(index).getListadoRespuestas()[i].setCorrecta(true);
                    listadoPreguntas.get(index).getListadoRespuestas()[i].setOpcionTexto(buff[i].replace('*',' ').trim());
                }
                else {
                    listadoPreguntas.get(index).getListadoRespuestas()[i].setOpcionTexto(buff[i]);
                }
            }
            index++;
            if (index==respuestas.size()) break;
        } while (iterator.hasNext());

    }

}
