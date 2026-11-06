package com.universidad.refactoringu11.inventario;

public class ClasificadorProducto {

    // Switch Statement sobre TipoProducto — se repite en EtiquetadoProducto
    public double calcularRecargoEnvio(Producto producto) {
        switch (producto.getTipo()) {
            case LIBRO:       return 0.0;
            case ELECTRONICO: return producto.getPrecio() * 0.08;
            case ROPA:        return producto.getPrecio() * 0.03;
            case ALIMENTO:    return 2.50;
            default: throw new IllegalArgumentException(
                "Tipo de producto desconocido: " + producto.getTipo());
        }
    }
}
