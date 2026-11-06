package com.universidad.refactoringu11.inventario;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventarioService {

    private final List<Producto> productos = new ArrayList<>();
    private final List<String> movimientos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Long Method: valida, busca, actualiza stock, calcula reorden
    // y registra/alerta, todo en un solo metodo (Bloater)
    public String actualizarInventario(Long productoId, int cantidad, String tipoMovimiento) {

        // Validacion de entrada (deberia ser responsabilidad propia)
        if (productoId == null || cantidad == 0
                || (!tipoMovimiento.equals("ENTRADA") && !tipoMovimiento.equals("SALIDA"))) {
            return "ERROR_DATOS_INVALIDOS";
        }

        // Busqueda del producto (mezclada con la logica de negocio)
        Producto producto = null;
        for (Producto p : productos) {
            if (p.getId().equals(productoId)) { producto = p; break; }
        }
        if (producto == null) return "ERROR_PRODUCTO_NO_EXISTE";

        // Actualizacion de stock
        int nuevoStock;
        if (tipoMovimiento.equals("ENTRADA")) {
            nuevoStock = producto.getStock() + cantidad;
        } else {
            nuevoStock = producto.getStock() - cantidad;
            if (nuevoStock < 0) return "ERROR_STOCK_INSUFICIENTE";
        }
        producto.setStock(nuevoStock);

        // Calculo de cantidad de reorden (otra responsabilidad distinta)
        boolean necesitaReorden = nuevoStock < producto.getStockMinimo();
        int cantidadReorden = necesitaReorden
                ? (producto.getStockMinimo() * 3) - nuevoStock : 0;

        // Registro del movimiento (responsabilidad de auditoria)
        String registro = tipoMovimiento + " de " + cantidad + " unidades de "
                + producto.getNombre() + " -> stock final: " + nuevoStock;
        movimientos.add(registro);
        System.out.println(registro);

        // Decision de alerta (responsabilidad de notificacion)
        if (necesitaReorden) {
            System.out.println("ALERTA: " + producto.getNombre()
                    + " bajo stock minimo. Reordenar " + cantidadReorden + " unidades.");
            return "OK_BAJO_STOCK_REORDEN_" + cantidadReorden;
        }
        return "OK_" + nuevoStock;
    }
}
