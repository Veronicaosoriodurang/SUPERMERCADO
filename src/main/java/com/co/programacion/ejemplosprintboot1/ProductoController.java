package com.co.programacion.ejemplosprintboot1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductoController {

    // Lista que se mantiene mientras la aplicación corre
    private List<Producto> productos = new ArrayList<>();

    // Constructor para inicializar productos
    public ProductoController() {
        productos.add(new Producto(1, "Arroz", 4500, 2));
        productos.add(new Producto(2, "Leche", 3800, 1));
        productos.add(new Producto(3, "Pan", 1200, 5));
        productos.add(new Producto(4, "Aceite", 8500, 1));
        productos.add(new Producto(5, "Azúcar", 3200, 3));
    }

    // ==================== READ - Listar productos ====================
    @GetMapping("/productos")
    public String listarProductos(Model model,
                                  @RequestParam(value = "buscar", required = false) String buscar,
                                  @RequestParam(value = "mensaje", required = false) String mensaje) {

        List<Producto> productosFiltrados = productos;

        // Filtrar por nombre si hay búsqueda
        if (buscar != null && !buscar.trim().isEmpty()) {
            productosFiltrados = productos.stream()
                    .filter(p -> p.getNombre().toLowerCase().contains(buscar.toLowerCase()))
                    .toList();
            model.addAttribute("buscar", buscar);
        }

        model.addAttribute("productos", productosFiltrados);
        if (mensaje != null) {
            model.addAttribute("mensaje", mensaje);
        }

        System.out.println("Productos en la lista: " + productos.size());
        return "productos";
    }

    // ==================== CREATE - Mostrar formulario ====================
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        Producto productoVacio = new Producto();
        productoVacio.setId(0);
        model.addAttribute("producto", productoVacio);
        model.addAttribute("accion", "Agregar");
        return "agregarProducto";
    }

    // ==================== CREATE - Guardar producto ====================
    @PostMapping("/productos")
    public String agregarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {
        try {
            // Generar ID automático
            int nuevoId = productos.isEmpty() ? 1 :
                    productos.stream().mapToInt(Producto::getId).max().orElse(0) + 1;
            producto.setId(nuevoId);

            productos.add(producto);

            redirectAttributes.addFlashAttribute("mensaje",
                    "✅ Producto '" + producto.getNombre() + "' agregado exitosamente!");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");

            System.out.println("Producto agregado: " + producto.getNombre());

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "❌ Error al agregar el producto: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }

        return "redirect:/productos";
    }

    // ==================== UPDATE - Mostrar formulario de edición ====================
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Producto> productoOpt = productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (productoOpt.isPresent()) {
            model.addAttribute("producto", productoOpt.get());
            model.addAttribute("accion", "Editar");
            return "agregarProducto"; // Reutilizamos el mismo formulario
        } else {
            redirectAttributes.addFlashAttribute("mensaje",
                    "❌ Producto no encontrado con ID: " + id);
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            return "redirect:/productos";
        }
    }

    // ==================== UPDATE - Actualizar producto ====================
    @PostMapping("/editar/{id}")
    public String actualizarProducto(@PathVariable int id, @ModelAttribute Producto producto,
                                     RedirectAttributes redirectAttributes) {
        try {
            Optional<Producto> productoExistente = productos.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst();

            if (productoExistente.isPresent()) {
                // Actualizar los datos del producto existente
                Producto productoActual = productoExistente.get();
                productoActual.setNombre(producto.getNombre());
                productoActual.setPrecio(producto.getPrecio());
                productoActual.setCantidad(producto.getCantidad());

                redirectAttributes.addFlashAttribute("mensaje",
                        "✅ Producto '" + producto.getNombre() + "' actualizado exitosamente!");
                redirectAttributes.addFlashAttribute("tipoMensaje", "success");

                System.out.println("Producto actualizado: " + producto.getNombre());

            } else {
                redirectAttributes.addFlashAttribute("mensaje",
                        "❌ Producto no encontrado con ID: " + id);
                redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "❌ Error al actualizar el producto: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }

        return "redirect:/productos";
    }

    // ==================== DELETE - Eliminar producto ====================
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Producto> productoOpt = productos.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst();

            if (productoOpt.isPresent()) {
                Producto producto = productoOpt.get();
                productos.remove(producto);

                redirectAttributes.addFlashAttribute("mensaje",
                        "🗑️ Producto '" + producto.getNombre() + "' eliminado exitosamente!");
                redirectAttributes.addFlashAttribute("tipoMensaje", "warning");

                System.out.println("Producto eliminado: " + producto.getNombre());

            } else {
                redirectAttributes.addFlashAttribute("mensaje",
                        "❌ Producto no encontrado con ID: " + id);
                redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "❌ Error al eliminar el producto: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }

        return "redirect:/productos";
    }

    // ==================== Página de inicio ====================
    @GetMapping("/")
    public String inicio(Model model) {
        int totalProductos = productos.size();
        double valorTotal = productos.stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum();

        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("valorTotal", valorTotal);
        model.addAttribute("productosRecientes", productos.stream().limit(3).toList());

        return "inicio";
    }
}