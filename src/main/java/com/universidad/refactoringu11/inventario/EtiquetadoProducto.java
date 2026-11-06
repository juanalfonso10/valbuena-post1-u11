package com.universidad.refactoringu11.inventario;

public class EtiquetadoProducto {

    public String generarEtiqueta(Producto producto) {
        return producto.getTipo().etiqueta(producto.getNombre());
    }
}
