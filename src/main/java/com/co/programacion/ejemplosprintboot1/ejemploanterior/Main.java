package com.co.programacion.ejemplosprintboot1.ejemploanterior;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    // TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    System.out.println("Hello and welcome! \n");
    byte miByte;
    short miShort;
    int j;
    float miFloat;
    double miDouble;
    boolean isVerdad;
    long miLong;
    char miChar = 'w';
    String palabra = "la palabra";

    Byte objetoByte;
    Short objetoShort;
    Integer objetoInt;
    Double objetoDouble;
    Float objetoFloat;
    Long objetoLong;


    MiClase miClase = new MiClase();
    MiClase miClase2 = new MiClase();


    for (int i = 1; i <= 5; i++) {
      // TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon
      // src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
      // for you, but you can always add more by pressing <shortcut
      // actionId="ToggleLineBreakpoint"/>.
      System.out.println("i = " + i);
      j = 0 ;
      miShort = 31000;

    }

    Vehiculo lista [] = new Vehiculo[4];
    lista[0] = new Carro();
    lista[1] = new Moto();
    lista[2] = new Moto();
    lista[3] = new Camion();
    System.out.println("Uso de Arreglo normal");
    for(Vehiculo v : lista){
      v.acelerar(20);
      v.conducir();
      v.frenar();
      System.out.println("numero de ruedas: "+v.getNumeroRuedas());
    }

    AnimalAbstracto animales [] = new AnimalAbstracto[3];
    animales[0] = new Perro();
    animales[1] = new Gato();
    animales[2] = new Gallina();

    for(AnimalAbstracto a : animales){
      a.comer();
      a.hacerSonido();
      a.dormir();
    }

    miClase.escibaTodo();
    miClase2.escibaTodo();

      Calculadora miCalculadora = new Calculadora();

      miCalculadora.ejecutarCalculadora();

  }
}
