package com.co.programacion.ejemplosprintboot1.ejemploanterior;

public class Camion implements Vehiculo {

    private static final int NUMERO_RUEDAS = 12;
    private int velocidad;

    @Override
    public void conducir() {
        System.out.println("Conduciendo el camion");
    }

    @Override
    public void frenar() {
        System.out.println("Frenando el camion");
    }

    @Override
    public void acelerar(int incremento) {
        velocidad = velocidad + incremento;
        System.out.println("El camion va a "+ velocidad + " km/h");
    }

    @Override
    public int getNumeroRuedas() {
        return NUMERO_RUEDAS;
    }
}
