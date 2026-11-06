package com.universidad.refactoringu11.reportes;

import com.universidad.refactoringu11.inventario.InventarioService;
import com.universidad.refactoringu11.inventario.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteBajoStockService {

    private final InventarioService inventarioService;

    public ReporteBajoStockService(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    public String generarReporte() {
        // recorre los productos bajo stock minimo expuestos por InventarioService
        // y arma un reporte de texto plano
        List<Producto> bajoStock = inventarioService.productosBajoStockMinimo();
        StringBuilder reporte = new StringBuilder("REPORTE DE BAJO STOCK");
        for (Producto producto : bajoStock) {
            reporte.append(System.lineSeparator())
                   .append("- ").append(producto.getNombre())
                   .append(": stock ").append(producto.getStock())
                   .append(" (minimo ").append(producto.getStockMinimo()).append(")");
        }
        return reporte.toString();
    }
}
