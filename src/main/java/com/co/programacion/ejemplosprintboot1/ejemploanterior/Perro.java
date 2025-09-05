package com.co.programacion.ejemplosprintboot1.ejemploanterior;

public class Perro extends AnimalAbstracto{
    @Override
    public void comer() {
        System.out.println("El perro está comiendo.");
    }

    @Override
    void hacerSonido() {
        System.out.println("El perro hace Guau.");
    }
}
