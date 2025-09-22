package com.co.programacion.ejemplosprintboot1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int id;
    private String numeroFactura;
    private LocalDateTime fecha;
    private List<DetalleVenta> detalles;
    private double subtotal;
    private double iva;
    private double total;
    private String cliente;
    private String vendedor;
    private String estado; // COMPLETADA, CANCELADA
    private String metodoPago; // EFECTIVO, TARJETA, TRANSFERENCIA

    // Constructor vacío
    public Venta() {
        this.detalles = new ArrayList<>();
        this.fecha = LocalDateTime.now();
        this.estado = "COMPLETADA";
        this.metodoPago = "EFECTIVO";
    }

    // Constructor con parámetros
    public Venta(int id, String cliente, String vendedor) {
        this();
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.numeroFactura = generarNumeroFactura();
    }

    // Generar número de factura automático
    private String generarNumeroFactura() {
        return "F" + String.format("%06d", id) + "-" +
                fecha.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    // Agregar detalle a la venta
    public void agregarDetalle(Producto producto, int cantidad) {
        // Verificar si el producto ya existe en el detalle
        DetalleVenta detalleExistente = detalles.stream()
                .filter(d -> d.getProductoId() == producto.getId())
                .findFirst()
                .orElse(null);

        if (detalleExistente != null) {
            // Si existe, actualizar cantidad
            detalleExistente.setCantidad(detalleExistente.getCantidad() + cantidad);
        } else {
            // Si no existe, crear nuevo detalle
            DetalleVenta nuevoDetalle = new DetalleVenta(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    cantidad
            );
            detalles.add(nuevoDetalle);
        }
        calcularTotales();
    }

    // Remover detalle de la venta
    public void removerDetalle(int productoId) {
        detalles.removeIf(detalle -> detalle.getProductoId() == productoId);
        calcularTotales();
    }

    // Actualizar cantidad de un detalle
    public void actualizarCantidadDetalle(int productoId, int nuevaCantidad) {
        DetalleVenta detalle = detalles.stream()
                .filter(d -> d.getProductoId() == productoId)
                .findFirst()
                .orElse(null);

        if (detalle != null) {
            if (nuevaCantidad <= 0) {
                removerDetalle(productoId);
            } else {
                detalle.setCantidad(nuevaCantidad);
                calcularTotales();
            }
        }
    }

    // Calcular totales de la venta
    public void calcularTotales() {
        this.subtotal = detalles.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();
        this.iva = subtotal * 0.19; // IVA del 19%
        this.total = subtotal + iva;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.numeroFactura = generarNumeroFactura();
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getFechaFormateada() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
        calcularTotales();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getCantidadTotalProductos() {
        return detalles.stream()
                .mapToInt(DetalleVenta::getCantidad)
                .sum();
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", numeroFactura='" + numeroFactura + '\'' +
                ", fecha=" + fecha +
                ", cliente='" + cliente + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                '}';
    }
}