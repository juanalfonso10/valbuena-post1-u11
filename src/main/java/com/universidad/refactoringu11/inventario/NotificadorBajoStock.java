package com.universidad.refactoringu11.inventario;

/**
 * Puerto de salida del dominio de inventario: lo que inventario necesita avisar cuando un
 * producto queda bajo su stock minimo. Vive en inventario (el paquete de mayor nivel) y lo
 * implementan los paquetes que reaccionan al aviso, como reportes.
 */
@FunctionalInterface
public interface NotificadorBajoStock {
    void notificar(Producto producto, int cantidadReorden);
}
