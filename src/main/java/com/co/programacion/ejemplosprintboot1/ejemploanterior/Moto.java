package com.co.programacion.ejemplosprintboot1.ejemploanterior;

public class Moto implements Vehiculo{
    private static final int NUMERO_RUEDAS = 2;
    private int velocidad;

    @Override
    public void conducir() {
        System.out.println("En prura en la moto");
    }

    @Override
    public void frenar() {
        System.out.println("Parando la Moto");
    }

    @Override
    public void acelerar(int incremento) {
        velocidad = velocidad + incremento;
        System.out.println("Acelerando la moto a " + velocidad + " km/h");
    }

    @Override
    public int getNumeroRuedas() {
        return NUMERO_RUEDAS;
    }
}
