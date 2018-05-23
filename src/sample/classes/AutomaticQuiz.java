package sample.classes;

import java.util.Random;

public class AutomaticQuiz extends Quiz implements GeneradorPreguntas {

    private GeneradorAritmetico operaciones; //Genera las operaciones
    private GeneradorNumerico numeros;
    private int[] numEnteros = new int[2];
    private float[] numDecimales = new float[2];
    private Fraccion[] numFraccion = new Fraccion[2];
    private Fraccion frac; //Utilizada para problemas en los que solo se ocupa una fraccion

    public AutomaticQuiz() {
        operaciones = new GeneradorAritmetico();
        numeros = new GeneradorNumerico();
        getPregunta();
    }

    public AutomaticQuiz(String materia) {
        super(materia);
        operaciones = new GeneradorAritmetico();
        numeros = new GeneradorNumerico();
        getPregunta();
    }

    public AutomaticQuiz(String materia, Dificultad dificultad) {
        super(materia,dificultad);
        operaciones = new GeneradorAritmetico();
        numeros = new GeneradorNumerico();
        getPregunta();
    }

    @Override
    public void getPregunta() {
        int index = 0; // Para acceder a los objetos Pregunta del ArrayList
        Random random = new Random(); // Para generar numeros aleaotrios del 0 al 3
        int indiceRespuesta = 0;
        int count = 0;

        do {
            indiceRespuesta = random.nextInt(4);
            switch (index) {
                case 0: {

                    numFraccion = numeros.generarTerminosFracciones("Suma");
                    // Asignando la pregunta a nuestro arraylist
                    problema.add(new Pregunta("Resuelva la siguiente suma de fracciones :\n"+
                    numFraccion[0].imprimirFraccion()+" + "+numFraccion[1].imprimirFraccion()));
                    // Asignando la respuesta correcta
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.suma(numFraccion[0], numFraccion[1]) );
                    do {
                        if (count != indiceRespuesta) {
                            numFraccion = numeros.generarTerminosFracciones("Suma");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.suma(numFraccion[0], numFraccion[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 1: {
                    numEnteros = numeros.generarTerminosEnteros("Suma");
                    problema.add(new Pregunta("Resuelva la siguiente suma de enteros: \n"+
                    numEnteros[0]+" + "+numEnteros[1]));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.suma(numEnteros[0], numEnteros[1]) );
                    do {
                        if (count != indiceRespuesta) {
                            numEnteros = numeros.generarTerminosEnteros("Suma");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.suma(numEnteros[0], numEnteros[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 2: {
                    numDecimales = numeros.generarTerminosDecimales("Suma");
                    problema.add(new Pregunta("Resuelva la siguiente suma de decimales: \n"+
                            numDecimales[0]+" + "+numDecimales[1]));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.suma(numDecimales[0], numDecimales[1])
                    );

                    do {
                        if (count != indiceRespuesta) {
                            numDecimales = numeros.generarTerminosDecimales("Suma");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.suma(numDecimales[0], numDecimales[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 3: {
                    numFraccion = numeros.generarTerminosFracciones("Resta");
                    // Asignando la problema a nuestro arraylist
                    problema.add(new Pregunta("Resuelva la siguiente resta de fracciones: \n"+
                            numFraccion[0].imprimirFraccion()+" - "+numFraccion[1].imprimirFraccion()));
                    // Asignando la respuesta correcta
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.resta(numFraccion[0], numFraccion[1]) );

                    do {
                        if (count != indiceRespuesta) {
                            numFraccion = numeros.generarTerminosFracciones("Resta");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.resta(numFraccion[0], numFraccion[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 4: {
                    numEnteros = numeros.generarTerminosEnteros("Resta");
                    problema.add(new Pregunta("Resuelva la siguiente resta de enteros: \n"+
                            numEnteros[0]+" - "+numEnteros[1]));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.resta(numEnteros[0], numEnteros[1]) );

                    do {
                        if (count != indiceRespuesta) {
                            numEnteros = numeros.generarTerminosEnteros("Resta");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.suma(numEnteros[0], numEnteros[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 5: {
                    numDecimales = numeros.generarTerminosDecimales("Resta");
                    problema.add(new Pregunta("Resuelva la siguiente resta de decimales: \n"+
                            numDecimales[0]+" - "+numDecimales[1]));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.resta(numDecimales[0], numDecimales[1])
                    );

                    do {
                        if (count != indiceRespuesta) {
                            numDecimales = numeros.generarTerminosDecimales("Resta");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.resta(numDecimales[0], numDecimales[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 6: {
                    numFraccion = numeros.generarTerminosFracciones("Multiplicacion");
                    problema.add(new Pregunta("Resuelva la siguiente multiplicacion de fracciones : \n" +
                            numFraccion[0].imprimirFraccion()+" X "+numFraccion[1].imprimirFraccion()));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.multiplicacion(numFraccion[0], numFraccion[1]) );

                    do {
                        if (count != indiceRespuesta) {
                            numFraccion = numeros.generarTerminosFracciones("Multiplicacion");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.multiplicacion(numFraccion[0], numFraccion[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 7: {
                    numEnteros = numeros.generarTerminosEnteros("Multiplicacion");
                    problema.add(new Pregunta("Resuelva la siguiente multiplicacion de enteros: \n"+
                            numEnteros[0]+" X "+numEnteros[1]));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.multiplicacion(numEnteros[0], numEnteros[1]) );

                    do {
                        if (count != indiceRespuesta) {
                            numEnteros = numeros.generarTerminosEnteros("Multiplicacion");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.multiplicacion(numEnteros[0], numEnteros[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 8: {
                    numFraccion = numeros.generarTerminosFracciones("Division");
                    problema.add(new Pregunta("Resuelva la siguiente division de fracciones : \n" +
                            numFraccion[0].imprimirFraccion()+" ÷ "+numFraccion[1].imprimirFraccion()));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.division(numFraccion[0], numFraccion[1]) );

                    do {
                        if (count != indiceRespuesta) {
                            numFraccion = numeros.generarTerminosFracciones("Division");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.division(numFraccion[0], numFraccion[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 9: {
                    numEnteros = numeros.generarTerminosEnteros("Division");
                    problema.add(new Pregunta("Resuelva la siguiente division de enteros: \n"+
                            numEnteros[0]+" ÷ "+numEnteros[1]));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.division(numEnteros[0], numEnteros[1]) );
                    do {
                        if (count != indiceRespuesta) {
                            numEnteros = numeros.generarTerminosEnteros("Division");
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.division(numEnteros[0], numEnteros[1]));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 10: {
                    frac = numeros.generarTerminosFracciones("Otra")[0];
                    problema.add(new Pregunta("Exprese la siguiente fracción en decimal: \n"+
                    frac.imprimirFraccion())); //Solo usaremos el primer elemento del arreglo
                    //para aprovechar la variable ya declarada

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.fraccionDecimal(frac));
                    do {
                        if (count != indiceRespuesta) {
                            frac = numeros.generarTerminosFracciones("Otra")[0];
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.fraccionDecimal(frac));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 11: {
                    frac = numeros.genTerminosFraccionSimplif();
                    problema.add(new Pregunta("Simplifique la siguiente fracción: \n"+
                    frac.imprimirFraccion()));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.simplificarFraccion(frac));
                    do {
                        if (count != indiceRespuesta) {
                            frac = numeros.genTerminosFraccionSimplif();
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.simplificarFraccion(frac));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
                case 12: {
                    frac = new Fraccion(random.nextInt(5)+1,random.nextInt(9)+1,random.nextInt(9)+1);
                    problema.add(new Pregunta("Exprese la siguiente fracción mixta en forma normal: \n"+
                    frac.imprimirFraccionMixta()));

                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setCorrecta(true);
                    problema.get(index).getListadoRespuestas()[indiceRespuesta].setOpcionTexto(
                            operaciones.simplificarMixta(frac.getEntero(),frac.getNumerador(),frac.getDenominador()));
                    do {
                        if (count != indiceRespuesta) {
                            frac = new Fraccion(random.nextInt(5)+1,random.nextInt(9)+1,random.nextInt(9)+1);
                            problema.get(index).getListadoRespuestas()[count].setOpcionTexto(
                                    operaciones.simplificarMixta(frac.getEntero(),frac.getNumerador(),frac.getDenominador()));
                        }
                        count++;
                    } while (count != 4);
                }
                    break;
            }
            count = 0;
            index++;
        }while (index != 13);
    }
}


