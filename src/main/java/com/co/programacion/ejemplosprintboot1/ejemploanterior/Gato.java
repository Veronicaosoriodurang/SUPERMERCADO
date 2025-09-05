package com.co.programacion.ejemplosprintboot1.ejemploanterior;

public class Gato extends AnimalAbstracto{
    @Override
    public void comer() {
        System.out.println("El gato está comiendo.");
    }

    @Override
    void hacerSonido() {
        System.out.println("El gato hace miau.");
    }
}
