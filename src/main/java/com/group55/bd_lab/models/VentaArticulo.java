package com.group55.bd_lab.models;

import java.util.Date;

public class VentaArticulo {
    public int id_Venta;
    public Date fecha;
    public double total;
    public String descripcion;

    public VentaArticulo(int id_Venta, Date fecha, double total, String descripcion) {
        this.id_Venta = id_Venta;
        this.fecha = fecha;
        this.total = total;
        this.descripcion = descripcion;
    }
}
