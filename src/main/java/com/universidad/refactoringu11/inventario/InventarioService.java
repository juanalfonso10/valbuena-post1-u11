package com.universidad.refactoringu11.inventario;

import com.universidad.refactoringu11.reportes.ReporteBajoStockService; // inventario ahora importa reportes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private static final String ENTRADA = "ENTRADA";
    private static final String SALIDA = "SALIDA";
    private static final int FACTOR_REORDEN = 3;

    private final List<Producto> productos = new ArrayList<>();
    private final List<String> movimientos = new ArrayList<>();

    private ReporteBajoStockService reporteBajoStockService; // colaborador opcional, sin final

    @Autowired(required = false)
    public void setReporteBajoStockService(ReporteBajoStockService reporteBajoStockService) {
        this.reporteBajoStockService = reporteBajoStockService;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> productosBajoStockMinimo() {
        return productos.stream()
                .filter(this::necesitaReorden)
                .toList();
    }

    public String actualizarInventario(Long productoId, int cantidad, String tipoMovimiento) {
        if (!sonDatosValidos(productoId, cantidad, tipoMovimiento)) {
            return "ERROR_DATOS_INVALIDOS";
        }
        return buscarProducto(productoId)
                .map(producto -> aplicarMovimiento(producto, cantidad, tipoMovimiento))
                .orElse("ERROR_PRODUCTO_NO_EXISTE");
    }

    private boolean sonDatosValidos(Long productoId, int cantidad, String tipoMovimiento) {
        return productoId != null && cantidad != 0
                && (tipoMovimiento.equals(ENTRADA) || tipoMovimiento.equals(SALIDA));
    }

    private Optional<Producto> buscarProducto(Long productoId) {
        return productos.stream()
                .filter(p -> p.getId().equals(productoId))
                .findFirst();
    }

    private String aplicarMovimiento(Producto producto, int cantidad, String tipoMovimiento) {
        int nuevoStock = calcularNuevoStock(producto, cantidad, tipoMovimiento);
        if (esStockInsuficiente(tipoMovimiento, nuevoStock)) {
            return "ERROR_STOCK_INSUFICIENTE";
        }
        producto.setStock(nuevoStock);
        registrarMovimiento(tipoMovimiento, cantidad, producto);
        return resultadoDelMovimiento(producto);
    }

    private int calcularNuevoStock(Producto producto, int cantidad, String tipoMovimiento) {
        return tipoMovimiento.equals(ENTRADA)
                ? producto.getStock() + cantidad
                : producto.getStock() - cantidad;
    }

    private boolean esStockInsuficiente(String tipoMovimiento, int nuevoStock) {
        return tipoMovimiento.equals(SALIDA) && nuevoStock < 0;
    }

    private void registrarMovimiento(String tipoMovimiento, int cantidad, Producto producto) {
        String registro = tipoMovimiento + " de " + cantidad + " unidades de "
                + producto.getNombre() + " -> stock final: " + producto.getStock();
        movimientos.add(registro);
        System.out.println(registro);
    }

    private String resultadoDelMovimiento(Producto producto) {
        if (!necesitaReorden(producto)) {
            return "OK_" + producto.getStock();
        }
        int cantidadReorden = calcularCantidadReorden(producto);
        if (reporteBajoStockService != null) {
            System.out.println(reporteBajoStockService.generarReporte());
        }
        emitirAlertaDeReorden(producto, cantidadReorden);
        return "OK_BAJO_STOCK_REORDEN_" + cantidadReorden;
    }

    private boolean necesitaReorden(Producto producto) {
        return producto.getStock() < producto.getStockMinimo();
    }

    private int calcularCantidadReorden(Producto producto) {
        return producto.getStockMinimo() * FACTOR_REORDEN - producto.getStock();
    }

    private void emitirAlertaDeReorden(Producto producto, int cantidadReorden) {
        System.out.println("ALERTA: " + producto.getNombre()
                + " bajo stock minimo. Reordenar " + cantidadReorden + " unidades.");
    }
}
