package com.universidad.refactoringu11.inventario;

/**
 * Cada tipo de producto implementa sus propios comportamientos: agregar un tipo nuevo
 * obliga (en compilacion) a definir su recargo y su etiqueta, sin tocar las clases que
 * los usan.
 */
public enum TipoProducto {

    LIBRO {
        @Override
        public double recargoEnvio(double precio) {
            return 0.0;
        }

        @Override
        public String etiqueta(String nombre) {
            return "[LIBRO] " + nombre + " - Exento de IVA";
        }
    },
    ELECTRONICO {
        @Override
        public double recargoEnvio(double precio) {
            return precio * 0.08;
        }

        @Override
        public String etiqueta(String nombre) {
            return "[ELECTRO] " + nombre + " - Garantia 1 ano";
        }
    },
    ROPA {
        @Override
        public double recargoEnvio(double precio) {
            return precio * 0.03;
        }

        @Override
        public String etiqueta(String nombre) {
            return "[ROPA] " + nombre + " - Ver talla";
        }
    },
    ALIMENTO {
        @Override
        public double recargoEnvio(double precio) {
            return 2.50;
        }

        @Override
        public String etiqueta(String nombre) {
            return "[ALIMENTO] " + nombre + " - Perecedero";
        }
    };

    public abstract double recargoEnvio(double precio);

    public abstract String etiqueta(String nombre);
}
