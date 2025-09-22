package com.co.programacion.ejemplosprintboot1;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    // Constructor vacío (OBLIGATORIO para Spring)
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método adicional para calcular el total
    public double calcularTotal() {
        return precio * cantidad;
    }

    // Método toString para debugging (opcional)
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", total=" + calcularTotal() +
                '}';
    }
}