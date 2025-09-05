package com.co.programacion.ejemplosprintboot1.ejemploanterior;

import java.util.*;

public class EjemploUsoColecciones {

    public static void main(String[] args) {
        // uso de arraylist
        EjemploUsoColecciones ejemplo = new EjemploUsoColecciones();
        ejemplo.ejemploArrayList();
        ejemplo.ejemploLinkedList();
        ejemplo.ejemploHashSet();
        ejemplo.ejemploTreeSet();
        ejemplo.ejemploHashMap();
        ejemplo.ejemploHashMapPersonas();
        ejemplo.ejemploTreeMapPersonas();
    }

    public void ejemploArrayList(){
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        listaVehiculos.add(new Carro());
        listaVehiculos.add(new Moto());
        listaVehiculos. add(2, new Moto());
        System.out.println("Uso de ArrayList");
        for(Vehiculo v: listaVehiculos){
            v.acelerar(50);
            v.conducir();
            v.frenar();
            System.out.println("numero de ruedas: "+v.getNumeroRuedas());
        }
        listaVehiculos.remove(2);
        for(Vehiculo v: listaVehiculos){
            v.acelerar(50);
            v.conducir();
            v.frenar();
            System.out.println("numero de ruedas: "+v.getNumeroRuedas());
        }
    }

    public void ejemploLinkedList(){
        List<AnimalAbstracto> linkedList = new LinkedList<>();
        Perro perro = new Perro();
        linkedList.add(new Perro());
        linkedList.add(new Gato());
        ((LinkedList<AnimalAbstracto>) linkedList).addFirst(new Gallina());
        linkedList.add(2, new Gato());
        linkedList.add(perro);
        linkedList.add(perro);
        linkedList.add(perro);
        System.out.println("Uso de Lista ligada");
        for(AnimalAbstracto a : linkedList){
            a.hacerSonido();
            a.dormir();
            a.comer();
        }
        linkedList.remove(perro);
        System.out.println("luego de remover");
        for(AnimalAbstracto a : linkedList){
            a.hacerSonido();
            a.dormir();
            a.comer();
        }
    }

    public void ejemploHashSet(){
        Set<Persona> setPersonas = new HashSet<>();
        Estudiante estudiante = new Estudiante();
        estudiante.setCedula("22222");
        estudiante.setNombres("tancredo");
        estudiante.setApellidos("neptuno");
        setPersonas.add(estudiante);
        Docente docente = new Docente();
        docente.setCedula("33333");
        docente.setNombres("juan");
        docente.setApellidos("perez");
        setPersonas.add(docente);
        setPersonas.add(estudiante);
        System.out.println("uso de set");
        for(Persona p : setPersonas){
            System.out.println(p.getNombres()+" "+p.getApellidos()+" con cedula "+p.getCedula());
        }
    }

    public void ejemploTreeSet(){
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("manzana");
        treeSet.add("pera");
        treeSet.add("banana");
        treeSet.add("kiwi");

        for(String s : treeSet){
            System.out.println(s);
        }
    }

    public void ejemploHashMap(){
        Map<Integer, String> mapa = new HashMap<>();
        mapa.put(1, "valor 1");
        mapa.put(2, "valor 2");
        mapa.put(3, "valor 3");
        mapa.put(4, "valor 4");

        for(Map.Entry<Integer, String> entry : mapa.entrySet()){
            System.out.println("Llave: "+entry.getKey()+" Valor: "+entry.getValue());
        }

        System.out.println("consulto por la clave 3: "+mapa.get(3));
        Collection<String> valores = mapa.values();
        for(String v : valores){
            System.out.println("valor: "+v);
        }
        mapa.put(3, "nuevo valor 3");
        //despues de actualizar
        System.out.println("despues de actualizar");
        for(Map.Entry<Integer, String> entry : mapa.entrySet()){
            System.out.println("Llave: "+entry.getKey()+" Valor: "+entry.getValue());
        }
    }

    public void ejemploHashMapPersonas(){
        Map<UUID, Persona> mapa = new HashMap<>();

        Estudiante estudiante = new Estudiante();
        estudiante.setCedula("22222");
        estudiante.setNombres("tancredo");
        estudiante.setApellidos("neptuno");
        estudiante.setCarrera("sistemas");

        Docente docente = new Docente();
        docente.setCedula("33333");
        docente.setNombres("juan");
        docente.setApellidos("perez");

        mapa.put(UUID.randomUUID(), estudiante);
        mapa.put(UUID.randomUUID(), docente);

        for(Map.Entry<UUID, Persona> entry : mapa.entrySet()){
            System.out.println("Llave: "+entry.getKey()+" Valor: "+entry.getValue());
        }

        System.out.println("consulto por la clave 3: "+mapa.get(3));
        Collection<Persona> valores = mapa.values();
        for(Persona p : valores){
            System.out.println("valor: "+p);
            System.out.println("cedula "+p.getCedula()+" nombres "+p.getNombres()+" apellidos "+p.getApellidos());
        }

    }

    public void ejemploLinkedHashMap(){
        Map<Integer, String> mapa = new LinkedHashMap<>();
        mapa.put(1, "valor 1");
        mapa.put(2, "valor 2");
        mapa.put(3, "valor 3");
        mapa.put(4, "valor 4");

        for(Map.Entry<Integer, String> entry : mapa.entrySet()){
            System.out.println("Llave: "+entry.getKey()+" Valor: "+entry.getValue());
        }

        System.out.println("consulto por la clave 3: "+mapa.get(3));
        Collection<String> valores = mapa.values();
        for(String v : valores){
            System.out.println("valor: "+v);
        }

    }

    public void ejemploTreeMap(){
        Map<Integer, String> mapa = new TreeMap<>();
        mapa.put(4, "valor 4");
        mapa.put(1, "valor 1");
        mapa.put(3, "valor 3");
        mapa.put(2, "valor 2");

        for(Map.Entry<Integer, String> entry : mapa.entrySet()){
            System.out.println("Llave: "+entry.getKey()+" Valor: "+entry.getValue());
        }

        System.out.println("consulto por la clave 3: "+mapa.get(3));
        Collection<String> valores = mapa.values();
        for(String v : valores){
            System.out.println("valor: "+v);
        }

    }

    public void ejemploTreeMapPersonas(){
        Map<UUID, Persona> mapa = new TreeMap<>();

        Estudiante estudiante = new Estudiante();
        estudiante.setCedula("22222");
        estudiante.setNombres("tancredo");
        estudiante.setApellidos("neptuno");
        estudiante.setCarrera("sistemas");

        Docente docente = new Docente();
        docente.setCedula("33333");
        docente.setNombres("juan");
        docente.setApellidos("perez");

        System.out.println("treeMap personas");

        mapa.put(UUID.randomUUID(), estudiante);
        mapa.put(UUID.randomUUID(), docente);

        for(Map.Entry<UUID, Persona> entry : mapa.entrySet()){
            System.out.println("Llave: "+entry.getKey()+" Valor: "+entry.getValue());
        }


        Collection<Persona> valores = mapa.values();
        for(Persona p : valores){
            System.out.println("valor: "+p);
            System.out.println("cedula "+p.getCedula()+" nombres "+p.getNombres()+" apellidos "+p.getApellidos());
        }

    }


}
