package com.universidad.refactoringu11.inventario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClasificadorProductoTest {

    private final ClasificadorProducto clasificador = new ClasificadorProducto();

    @Test
    void libro_noTieneRecargo() {
        Producto p = new Producto(1L, "Refactoring", TipoProducto.LIBRO, 60.0, 10, 2);
        assertEquals(0.0, clasificador.calcularRecargoEnvio(p), 0.001);
    }

    @Test
    void electronico_recargaOchoPorCiento() {
        Producto p = new Producto(2L, "Teclado", TipoProducto.ELECTRONICO, 100.0, 10, 2);
        assertEquals(8.0, clasificador.calcularRecargoEnvio(p), 0.001);
    }

    @Test
    void ropa_recargaTresPorCiento() {
        Producto p = new Producto(3L, "Camisa", TipoProducto.ROPA, 40.0, 10, 2);
        assertEquals(1.2, clasificador.calcularRecargoEnvio(p), 0.001);
    }

    @Test
    void alimento_recargoFijo() {
        Producto p = new Producto(4L, "Cafe", TipoProducto.ALIMENTO, 15.0, 10, 2);
        assertEquals(2.50, clasificador.calcularRecargoEnvio(p), 0.001);
    }
}
