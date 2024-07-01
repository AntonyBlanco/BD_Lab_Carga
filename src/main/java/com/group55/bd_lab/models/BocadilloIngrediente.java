package com.group55.bd_lab.models;

public class BocadilloIngrediente {
    public int id_Bocadillo_Ingrediente;
    public int id_Bocadillo;
    public String ingrediente;

    public BocadilloIngrediente(int id_Bocadillo_Ingrediente, int id_Bocadillo, String ingrediente) {
        this.id_Bocadillo_Ingrediente = id_Bocadillo_Ingrediente;
        this.id_Bocadillo = id_Bocadillo;
        this.ingrediente = ingrediente;
    }
}
