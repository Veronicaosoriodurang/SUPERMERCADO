package com.co.programacion.ejemplosprintboot1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    private ProductoController productoController;

    // Lista de ventas realizadas
    private List<Venta> ventas = new ArrayList<>();

    // Venta actual en proceso (carrito)
    private Venta ventaActual = null;

    // Constructor para inicializar datos de prueba
    public VentasController() {
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        // Crear algunas ventas de prueba
        Venta venta1 = new Venta(1, "Juan Pérez", "María García");
        venta1.agregarDetalle(new Producto(1, "Arroz", 4500, 10), 2);
        venta1.agregarDetalle(new Producto(2, "Leche", 3800, 5), 1);
        venta1.setMetodoPago("EFECTIVO");
        venta1.setFecha(LocalDateTime.now().minusHours(2));
        ventas.add(venta1);

        Venta venta2 = new Venta(2, "Ana López", "Carlos Ruiz");
        venta2.agregarDetalle(new Producto(3, "Pan", 1200, 8), 3);
        venta2.setMetodoPago("TARJETA");
        venta2.setFecha(LocalDateTime.now().minusMinutes(30));
        ventas.add(venta2);
    }

    // ==================== PUNTO DE VENTA (POS) ====================

    @GetMapping("/pos")
    public String mostrarPuntoVenta(Model model) {
        if (ventaActual == null) {
            ventaActual = new Venta();
            ventaActual.setVendedor("Sistema");
        }

        model.addAttribute("venta", ventaActual);
        model.addAttribute("productos", obtenerProductosDisponibles());
        model.addAttribute("productosCarrito", ventaActual.getDetalles());

        return "pos";
    }

    @PostMapping("/agregar-al-carrito")
    public String agregarAlCarrito(@RequestParam int productoId,
                                   @RequestParam int cantidad,
                                   RedirectAttributes redirectAttributes) {
        try {
            if (ventaActual == null) {
                ventaActual = new Venta();
                ventaActual.setVendedor("Sistema");
            }

            Producto producto = buscarProductoPorId(productoId);
            if (producto == null) {
                redirectAttributes.addFlashAttribute("error",
                        "❌ Producto no encontrado");
                return "redirect:/ventas/pos";
            }

            if (producto.getCantidad() < cantidad) {
                redirectAttributes.addFlashAttribute("error",
                        "❌ Stock insuficiente. Disponible: " + producto.getCantidad());
                return "redirect:/ventas/pos";
            }

            ventaActual.agregarDetalle(producto, cantidad);

            redirectAttributes.addFlashAttribute("success",
                    "✅ Producto agregado al carrito");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "❌ Error al agregar producto: " + e.getMessage());
        }

        return "redirect:/ventas/pos";
    }

    @PostMapping("/actualizar-carrito")
    public String actualizarCarrito(@RequestParam int productoId,
                                    @RequestParam int cantidad,
                                    RedirectAttributes redirectAttributes) {
        try {
            if (ventaActual != null) {
                Producto producto = buscarProductoPorId(productoId);
                if (producto != null && producto.getCantidad() >= cantidad) {
                    ventaActual.actualizarCantidadDetalle(productoId, cantidad);
                    redirectAttributes.addFlashAttribute("success",
                            "✅ Carrito actualizado");
                } else {
                    redirectAttributes.addFlashAttribute("error",
                            "❌ Stock insuficiente");
                }
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "❌ Error al actualizar carrito");
        }

        return "redirect:/ventas/pos";
    }

    @PostMapping("/remover-del-carrito")
    public String removerDelCarrito(@RequestParam int productoId,
                                    RedirectAttributes redirectAttributes) {
        try {
            if (ventaActual != null) {
                ventaActual.removerDetalle(productoId);
                redirectAttributes.addFlashAttribute("success",
                        "🗑️ Producto removido del carrito");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "❌ Error al remover producto");
        }

        return "redirect:/ventas/pos";
    }

    @PostMapping("/finalizar-venta")
    public String finalizarVenta(@RequestParam String cliente,
                                 @RequestParam String metodoPago,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (ventaActual == null || ventaActual.getDetalles().isEmpty()) {
                redirectAttributes.addFlashAttribute("error",
                        "❌ No hay productos en el carrito");
                return "redirect:/ventas/pos";
            }

            // Configurar la venta
            int nuevoId = ventas.size() + 1;
            ventaActual.setId(nuevoId);
            ventaActual.setCliente(cliente.isEmpty() ? "Cliente General" : cliente);
            ventaActual.setMetodoPago(metodoPago);
            ventaActual.setFecha(LocalDateTime.now());

            // Actualizar inventario
            boolean inventarioActualizado = actualizarInventario(ventaActual);

            if (!inventarioActualizado) {
                redirectAttributes.addFlashAttribute("error",
                        "❌ Error al actualizar inventario. Venta cancelada.");
                return "redirect:/ventas/pos";
            }

            // Guardar la venta
            ventas.add(ventaActual);

            redirectAttributes.addFlashAttribute("ventaCompletada", ventaActual);
            redirectAttributes.addFlashAttribute("success",
                    "🎉 Venta completada exitosamente!");

            // Limpiar carrito para nueva venta
            ventaActual = null;

            return "redirect:/ventas/factura/" + nuevoId;

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "❌ Error al procesar la venta: " + e.getMessage());
            return "redirect:/ventas/pos";
        }
    }

    @PostMapping("/cancelar-venta")
    public String cancelarVenta(RedirectAttributes redirectAttributes) {
        ventaActual = null;
        redirectAttributes.addFlashAttribute("warning",
                "⚠️ Venta cancelada");
        return "redirect:/ventas/pos";
    }

    // ==================== GESTIÓN DE VENTAS ====================

    @GetMapping("/lista")
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventas);
        model.addAttribute("totalVentas", ventas.size());
        model.addAttribute("montoTotal", calcularMontoTotalVentas());
        return "lista-ventas";
    }

    @GetMapping("/factura/{id}")
    public String verFactura(@PathVariable int id, Model model) {
        Optional<Venta> ventaOpt = ventas.stream()
                .filter(v -> v.getId() == id)
                .findFirst();

        if (ventaOpt.isPresent()) {
            model.addAttribute("venta", ventaOpt.get());
            return "factura";
        } else {
            return "redirect:/ventas/lista";
        }
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private List<Producto> obtenerProductosDisponibles() {
        // Aquí obtienes los productos del ProductoController
        // Por simplicidad, creo algunos productos de ejemplo
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Arroz", 4500, 10));
        productos.add(new Producto(2, "Leche", 3800, 5));
        productos.add(new Producto(3, "Pan", 1200, 8));
        productos.add(new Producto(4, "Aceite", 8500, 3));
        productos.add(new Producto(5, "Azúcar", 3200, 6));
        return productos;
    }

    private Producto buscarProductoPorId(int id) {
        return obtenerProductosDisponibles().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private boolean actualizarInventario(Venta venta) {
        try {
            List<Producto> productos = obtenerProductosDisponibles();

            for (DetalleVenta detalle : venta.getDetalles()) {
                Producto producto = productos.stream()
                        .filter(p -> p.getId() == detalle.getProductoId())
                        .findFirst()
                        .orElse(null);

                if (producto != null) {
                    int nuevaCantidad = producto.getCantidad() - detalle.getCantidad();
                    if (nuevaCantidad < 0) {
                        return false; // Stock insuficiente
                    }
                    producto.setCantidad(nuevaCantidad);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private double calcularMontoTotalVentas() {
        return ventas.stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }
}