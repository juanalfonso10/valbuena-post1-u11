package com.universidad.refactoringu11.reportes;

import com.universidad.refactoringu11.inventario.NotificadorBajoStock;
import com.universidad.refactoringu11.inventario.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ReporteBajoStockService implements NotificadorBajoStock {

    private final List<AlertaBajoStock> alertas = new CopyOnWriteArrayList<>();

    @Override
    public void notificar(Producto producto, int cantidadReorden) {
        // Se guarda una copia de los valores del momento: el stock del producto puede
        // cambiar despues y el reporte debe mostrar el estado que disparo la alerta
        alertas.add(new AlertaBajoStock(producto.getNombre(), producto.getStock(),
                producto.getStockMinimo(), cantidadReorden));
    }

    public String generarReporte() {
        StringBuilder reporte = new StringBuilder("REPORTE DE BAJO STOCK");
        if (alertas.isEmpty()) {
            return reporte.append(System.lineSeparator())
                          .append("Sin alertas registradas").toString();
        }
        for (AlertaBajoStock alerta : alertas) {
            reporte.append(System.lineSeparator())
                   .append("- ").append(alerta.nombreProducto())
                   .append(": stock ").append(alerta.stock())
                   .append(" (minimo ").append(alerta.stockMinimo())
                   .append("), reordenar ").append(alerta.cantidadReorden());
        }
        return reporte.toString();
    }

    private record AlertaBajoStock(String nombreProducto, int stock, int stockMinimo,
                                   int cantidadReorden) {
    }
}
