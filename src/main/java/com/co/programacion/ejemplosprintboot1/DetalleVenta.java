package com.co.programacion.ejemplosprintboot1;

public class DetalleVenta {
    private int productoId;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidad;
    private double subtotal;

    // Constructor vacío
    public DetalleVenta() {
    }

    // Constructor con parámetros
    public DetalleVenta(int productoId, String nombreProducto, double precioUnitario, int cantidad) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    // Calcular subtotal automáticamente
    public void calcularSubtotal() {
        this.subtotal = this.precioUnitario * this.cantidad;
    }

    // Getters y Setters
    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        calcularSubtotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "productoId=" + productoId +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}