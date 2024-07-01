package com.group55.bd_lab.models;

public class Bocadillo {
    public int id_Bocadillo;
    public int id_Tamanio_Bocadillo;
    public int id_Articulo;

    public Bocadillo(int id_Bocadillo, int id_Tamanio_Bocadillo, int id_Articulo) {
        this.id_Bocadillo = id_Bocadillo;
        this.id_Tamanio_Bocadillo = id_Tamanio_Bocadillo;
        this.id_Articulo = id_Articulo;
    }
}
