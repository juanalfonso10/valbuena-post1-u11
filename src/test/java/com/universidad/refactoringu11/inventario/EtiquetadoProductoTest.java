package com.universidad.refactoringu11.inventario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EtiquetadoProductoTest {

    private final EtiquetadoProducto etiquetado = new EtiquetadoProducto();

    @Test
    void libro_incluyeExentoDeIva() {
        Producto p = new Producto(1L, "Refactoring", TipoProducto.LIBRO, 60.0, 10, 2);
        assertEquals("[LIBRO] Refactoring - Exento de IVA", etiquetado.generarEtiqueta(p));
    }

    @Test
    void electronico_incluyeGarantia() {
        Producto p = new Producto(2L, "Teclado", TipoProducto.ELECTRONICO, 100.0, 10, 2);
        assertEquals("[ELECTRO] Teclado - Garantia 1 ano", etiquetado.generarEtiqueta(p));
    }

    @Test
    void ropa_incluyeVerTalla() {
        Producto p = new Producto(3L, "Camisa", TipoProducto.ROPA, 40.0, 10, 2);
        assertEquals("[ROPA] Camisa - Ver talla", etiquetado.generarEtiqueta(p));
    }

    @Test
    void alimento_incluyePerecedero() {
        Producto p = new Producto(4L, "Cafe", TipoProducto.ALIMENTO, 15.0, 10, 2);
        assertEquals("[ALIMENTO] Cafe - Perecedero", etiquetado.generarEtiqueta(p));
    }
}
