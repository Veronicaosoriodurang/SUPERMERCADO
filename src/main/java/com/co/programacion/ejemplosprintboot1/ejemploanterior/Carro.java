package com.co.programacion.ejemplosprintboot1.ejemploanterior;

public class Carro implements Vehiculo {

    private static final int NUMERO_RUEDAS = 4;
    private int velocidad;

    static {
        System.out.println("Creando un carro");
    }

    @Override
    public void conducir() {
        System.out.println("Conduciendo el carro");
    }

    @Override
    public void frenar() {
        System.out.println("Frenando el carro");
    }

    @Override
    public void acelerar(int incremento) {
        velocidad = velocidad + incremento;
        System.out.println("El carro va a "+ velocidad + " km/h");
    }

    @Override
    public int getNumeroRuedas() {
        return NUMERO_RUEDAS;
    }
    //comentario
    /*
            explicar static con el uso de métodos y variables static, explicar el build y servidores de aplicaciones Java.
            muchos comentarios
            muchos comentarios

     */
}
