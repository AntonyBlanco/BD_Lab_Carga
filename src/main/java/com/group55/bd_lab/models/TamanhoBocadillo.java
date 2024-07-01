package com.group55.bd_lab.models;

public class TamanhoBocadillo {
    public int nro_TamanhoBocadillo;
    public String nombre;
    public String descripcion;
    public String available;

    public TamanhoBocadillo(int nro_TamanhoBocadillo, String nombre, String descripcion, String available) {
        this.nro_TamanhoBocadillo = nro_TamanhoBocadillo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.available = available;
    }
}
