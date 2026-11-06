package com.universidad.refactoringu11.inventario;

public class ClasificadorProducto {

    public double calcularRecargoEnvio(Producto producto) {
        return producto.getTipo().recargoEnvio(producto.getPrecio());
    }
}
