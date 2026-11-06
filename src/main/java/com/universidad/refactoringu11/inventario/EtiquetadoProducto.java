package com.universidad.refactoringu11.inventario;

public class EtiquetadoProducto {

    // Mismo switch sobre TipoProducto que ClasificadorProducto — duplicacion estructural
    public String generarEtiqueta(Producto producto) {
        switch (producto.getTipo()) {
            case LIBRO:       return "[LIBRO] " + producto.getNombre() + " - Exento de IVA";
            case ELECTRONICO: return "[ELECTRO] " + producto.getNombre() + " - Garantia 1 ano";
            case ROPA:        return "[ROPA] " + producto.getNombre() + " - Ver talla";
            case ALIMENTO:    return "[ALIMENTO] " + producto.getNombre() + " - Perecedero";
            default: throw new IllegalArgumentException(
                "Tipo de producto desconocido: " + producto.getTipo());
        }
    }
}
