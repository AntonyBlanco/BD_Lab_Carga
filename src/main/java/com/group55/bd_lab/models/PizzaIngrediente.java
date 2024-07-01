package com.group55.bd_lab.models;

public class PizzaIngrediente {
    public int id_Pizza_Ingrediente;
    public int id_Pizza;
    public int id_Ingrediente;

    public PizzaIngrediente(int id_Pizza_Ingrediente, int id_Pizza, int id_Ingrediente) {
        this.id_Pizza_Ingrediente = id_Pizza_Ingrediente;
        this.id_Pizza = id_Pizza;
        this.id_Ingrediente = id_Ingrediente;
    }
}
