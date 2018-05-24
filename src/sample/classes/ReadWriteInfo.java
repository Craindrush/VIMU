package sample.classes;

import java.io.*;
import java.util.*;

public class ReadWriteInfo {

    public Partida readPlayersInfo(Partida partida, String nombrePartida) {
        Partida partidaMateria = new Partida(nombrePartida);
        String[] playersInfo = new String[2];
        String[] player1;
        String[] player2;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/sample/QuestionsAnswers/players.txt")), "ISO-8859-1"));
            playersInfo[0] = br.readLine();
            playersInfo[1] = br.readLine();

            player1 = playersInfo[0].split(";");
            player2 = playersInfo[1].split(";");

            partidaMateria = new Partida(nombrePartida, new Jugador(player1[1], Integer.parseInt(player1[2]),
                    player1[0]), new Jugador(player2[1], Integer.parseInt(player2[2]),
                    player2[0]));

            br.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return partidaMateria;
    }

    public void saveMateriaResults(String ruta, Partida partida) {
        File file = null;
        file = new File(ruta);
        try (FileWriter fileWriter = new FileWriter(file)) {
            if (partida.jugador1.puntaje.puntos > partida.jugador2.puntaje.puntos) {
                fileWriter.write(partida.jugador1.puntaje.puntos + ";" +
                        partida.jugador1.nombre + ";" + partida.jugador1.genero + ";" +
                        String.valueOf(partida.jugador1.edad + "\n"));

                fileWriter.write(partida.jugador2.puntaje.puntos + ";" +
                        partida.jugador2.nombre + ";" + partida.jugador2.genero + ";" +
                        String.valueOf(partida.jugador2.edad + "\n"));
                fileWriter.close();
            } else {
                fileWriter.write(partida.jugador2.puntaje.puntos + ";" +
                        partida.jugador2.nombre + ";" + partida.jugador2.genero + ";" +
                        String.valueOf(partida.jugador2.edad + "\n"));
                fileWriter.write(partida.jugador1.puntaje.puntos + ";" +
                        partida.jugador1.nombre + ";" + partida.jugador1.genero + ";" +
                        String.valueOf(partida.jugador1.edad + "\n"));
                fileWriter.close();
            }
        } catch (Exception e) {
        }
    }

    public List<rankingPlayer> readRankingList(String nameMateria) throws IOException {

        List<rankingPlayer> listPlayers = new ArrayList<rankingPlayer>();
        File file1 = null, file2 = null;
        String[] temp1, temp2, player1, player2;
        String linea;
        boolean isPlayer1 = false, isPlayer2 = false;
        int index = 0;

        if (nameMateria == "Geo") {
            file1 = new File("src/sample/QuestionsAnswers/Geography/listaRecordsGeo.txt");
            file2 = new File("src/sample/QuestionsAnswers/Geography/geographyQuizResults.txt");
        }
        else if (nameMateria == "Mat") {
            file1 = new File("src/sample/QuestionsAnswers/Math/listaRecordsMat.txt");
            file2 = new File("src/sample/QuestionsAnswers/Math/mathQuizResults.txt");
        }
        else if (nameMateria == "Esp") {
            file1 = new File("src/sample/QuestionsAnswers/Spanish/listaRecordsEsp.txt");
            file2 = new File("src/sample/QuestionsAnswers/Spanish/spanishQuizResults.txt");
        }

        //Solo para comprobar si el archivo esta vacio
        BufferedReader lista = new BufferedReader(new FileReader(file1));
        if (lista.readLine() == null) {
            lista.close();

            BufferedReader br = new BufferedReader(new FileReader(file2));
            FileWriter fileWriter = new FileWriter(file1);

            temp1 = br.readLine().split(";");
            temp2 = br.readLine().split(";");

            if (Integer.parseInt(temp1[0]) > Integer.parseInt(temp2[0])) {
                fileWriter.write(temp1[0] + ";" + temp1[1] + "\n");
                fileWriter.write(temp2[0] + ";" + temp2[1] + "\n");
            }
            else {
                fileWriter.write(temp2[0] + ";" + temp2[1] + "\n");
                fileWriter.write(temp1[0] + ";" + temp1[1] + "\n");
            }
            br.close();
            fileWriter.close();

        }else {
            //Leyendo la lista de los jugadores y sus puntos
            BufferedReader records = new BufferedReader(new FileReader(file1));

            //Separando los campos por el ; y guardandolo en el HashMap
            while ((linea = records.readLine()) != null) {
                temp1 = linea.split(";");
                //Almacenando nombre, seguido de puntaje
                listPlayers.add(new rankingPlayer(temp1[1], Integer.parseInt(temp1[0])));
            }
            records.close();
            // Leyendo las dos lineas de nuestro jugador1 y jugador2
            BufferedReader br = new BufferedReader(new FileReader(file2));
            player1 = br.readLine().split(";");
            player2 = br.readLine().split(";");
            br.close();

            while (index != listPlayers.size()) {
                //Si los nombres son iguales, el mismo jugador
                if (listPlayers.get(index).nombre.equals(player1[1])) {
                    listPlayers.get(index).puntos = Integer.parseInt(player1[0]);
                    isPlayer1 = true;
                } else if (listPlayers.get(index).nombre.equals(player2[1])) {
                    listPlayers.get(index).puntos = Integer.parseInt(player2[0]);
                    isPlayer2 = true;
                }
                index++;
            }

            // Comprobando si player 1 y player 2 estaban repetidos en la lista
            if (!isPlayer1) {
                listPlayers.add(new rankingPlayer(player1[1], Integer.parseInt(player1[0])));
            }
            if (!isPlayer2) {
                listPlayers.add(new rankingPlayer(player2[1], Integer.parseInt(player2[0])));
            }
            //Ordenando nuestra lista respecto al numero de puntos
            Collections.sort(listPlayers, new Comparator<rankingPlayer>() {
                @Override
                public int compare(rankingPlayer o1, rankingPlayer o2) {
                    return Integer.valueOf(o2.puntos).compareTo(o1.puntos);
                }
            });

            FileWriter fileWriter = new FileWriter(file1);
            for (int i = 0; i < listPlayers.size(); i++) {
                System.out.println(listPlayers.get(i).puntos + ";" + listPlayers.get(i).nombre);
                fileWriter.write(listPlayers.get(i).puntos + ";" + String.valueOf(listPlayers.get(i).nombre + "\n"));
            }
            fileWriter.close();
        }
        return listPlayers;
    }

}
