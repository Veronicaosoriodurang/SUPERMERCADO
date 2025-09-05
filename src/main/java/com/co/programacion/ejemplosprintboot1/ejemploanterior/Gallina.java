package com.co.programacion.ejemplosprintboot1.ejemploanterior;

public class Gallina extends AnimalAbstracto{
    @Override
    public void comer() {
        System.out.println("La gallina está comiendo maíz.");
    }

    @Override
    void hacerSonido() {
        System.out.println("La gallina cacarea.");
    }
}
