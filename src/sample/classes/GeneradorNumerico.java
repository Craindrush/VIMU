package sample.classes;

import java.util.Random;

public class GeneradorNumerico {
    public int[] generarTerminosEnteros(String operacionAritmetica) {
        Random random = new Random();
        int[] numerosEnteros = new int[2];

        if (operacionAritmetica == "Suma" || operacionAritmetica == "Resta") {
            //A partir de 20. Numeros del 20 al 1000
            numerosEnteros[0] = random.nextInt(980) + 20;
            numerosEnteros[1] = random.nextInt(980) + 20;
        }
        else if (operacionAritmetica == "Multiplicacion") {
            // Numeros del 1 al 12
            numerosEnteros[0] = random.nextInt(11)+1;
            numerosEnteros[1] = random.nextInt(11)+1;
        }
        else if (operacionAritmetica == "Division") {
            // Numeros del 1 al 100
            do {
                numerosEnteros[0] = random.nextInt(98)+2;
                numerosEnteros[1] = random.nextInt(98)+2;
            } while (numerosEnteros[0]%numerosEnteros[1] != 0); //Tienen que poder ser divisibles
        }
        return numerosEnteros;
    }

    public float[] generarTerminosDecimales(String operacionAritmetica) {
        Random random = new Random();
        float[] numerosDecimales = new float[2];

        if (operacionAritmetica == "Suma" || operacionAritmetica == "Resta") {
            //A partir de 20. Numeros decimales del 20 al 100
            numerosDecimales[0] = (float) (random.nextInt(80)+20)+ (float) (random.nextInt(80)+20)/100;
            numerosDecimales[1] = (float) (random.nextInt(80)+20)+ (float) (random.nextInt(80)+20)/100;
        }

        return numerosDecimales;
    }

    public Fraccion[] generarTerminosFracciones(String operacionAritmetica) {
        Random random = new Random();
        Fraccion[] fracciones = new Fraccion[2];

        if (operacionAritmetica == "Suma" || operacionAritmetica == "Resta") {
            //Fracciones con numeros del 1 al 10
            fracciones[0] = new Fraccion(random.nextInt(9) + 1,random.nextInt(9) + 1);
            fracciones[1] = new Fraccion(random.nextInt(9) + 1,random.nextInt(9) + 1);
        }
        else if (operacionAritmetica == "Multiplicacion") {
            //Fracciones con numeros del 1 al 15
            fracciones[0] = new Fraccion(random.nextInt(14) + 1,random.nextInt(14) + 1);
            fracciones[1] = new Fraccion(random.nextInt(14) + 1,random.nextInt(14) + 1);
        }
        else if (operacionAritmetica == "Division") {
            // Numeros del 1 al 10
            fracciones[0] = new Fraccion(random.nextInt(9) + 1,random.nextInt(9) + 1);
            fracciones[1] = new Fraccion(random.nextInt(9) + 1,random.nextInt(9) + 1);
        }
        else if (operacionAritmetica == "Otra") {
            // Numeros del 1 al 20
            fracciones[0] = new Fraccion(random.nextInt(19) + 1,random.nextInt(9) + 1);
        }
        return fracciones;
    }

    public Fraccion genTerminosFraccionSimplif () {
        Fraccion fraccion;
        Random random = new Random();
        int numerador;
        int denominador;
        do {
            numerador = random.nextInt(39)+1;
            denominador = random.nextInt(39)+1;
            if (numerador%2==0 && denominador%2==0) break;
            if (numerador%3==0 && denominador%3==0) break;
            if (numerador%4==0 && denominador%4==0) break;
            if (numerador%5==0 && denominador%5==0) break;
            if (numerador%6==0 && denominador%6==0) break;
        } while (true);

        fraccion = new Fraccion(numerador,denominador);
        return fraccion;
    }
}
