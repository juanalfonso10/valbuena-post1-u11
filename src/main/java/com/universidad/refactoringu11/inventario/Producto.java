package com.universidad.refactoringu11.inventario;

public class Producto {
    private final Long id;
    private final String nombre;
    private final TipoProducto tipo;
    private final double precio;
    private int stock;
    private final int stockMinimo;

    public Producto(Long id, String nombre, TipoProducto tipo,
                     double precio, int stock, int stockMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public TipoProducto getTipo() { return tipo; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStock(int stock) { this.stock = stock; }
}
