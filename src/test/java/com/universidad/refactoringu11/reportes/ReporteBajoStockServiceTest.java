package com.universidad.refactoringu11.reportes;

import com.universidad.refactoringu11.inventario.Producto;
import com.universidad.refactoringu11.inventario.TipoProducto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReporteBajoStockServiceTest {

    private final ReporteBajoStockService reporte = new ReporteBajoStockService();

    @Test
    void sinNotificaciones_indicaQueNoHayAlertas() {
        assertEquals("REPORTE DE BAJO STOCK" + System.lineSeparator() + "Sin alertas registradas",
                reporte.generarReporte());
    }

    @Test
    void conNotificaciones_listaCadaAlertaEnOrden() {
        reporte.notificar(new Producto(1L, "Clean Code", TipoProducto.LIBRO, 45.0, 3, 5), 12);
        reporte.notificar(new Producto(2L, "Cafe", TipoProducto.ALIMENTO, 15.0, 0, 4), 12);

        String n = System.lineSeparator();
        assertEquals("REPORTE DE BAJO STOCK"
                + n + "- Clean Code: stock 3 (minimo 5), reordenar 12"
                + n + "- Cafe: stock 0 (minimo 4), reordenar 12", reporte.generarReporte());
    }

    @Test
    void conservaElStockDelMomentoDeLaAlerta() {
        Producto libro = new Producto(1L, "Clean Code", TipoProducto.LIBRO, 45.0, 3, 5);
        reporte.notificar(libro, 12);
        libro.setStock(40);

        assertTrue(reporte.generarReporte().contains("- Clean Code: stock 3 (minimo 5), reordenar 12"));
    }
}
