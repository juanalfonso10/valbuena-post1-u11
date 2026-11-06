package com.universidad.refactoringu11;

import com.universidad.refactoringu11.inventario.InventarioService;
import com.universidad.refactoringu11.inventario.Producto;
import com.universidad.refactoringu11.inventario.TipoProducto;
import com.universidad.refactoringu11.reportes.ReporteBajoStockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// Levanta el contexto real de Spring: antes de invertir la dependencia este contexto
// no arrancaba por el ciclo de beans inventarioService <-> reporteBajoStockService.
@SpringBootTest
class InventarioReportesIntegracionTest {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private ReporteBajoStockService reporteBajoStockService;

    @Test
    void salidaBajoElMinimo_springConectaElNotificadorYLaAlertaLlegaAlReporte() {
        inventarioService.agregarProducto(new Producto(1L, "Clean Code", TipoProducto.LIBRO, 45.0, 20, 5));

        assertEquals("OK_BAJO_STOCK_REORDEN_12", inventarioService.actualizarInventario(1L, 17, "SALIDA"));
        assertTrue(reporteBajoStockService.generarReporte()
                .contains("- Clean Code: stock 3 (minimo 5), reordenar 12"));
    }
}
