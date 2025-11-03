/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author victo
 */
public class Vertice {
    String usuario;
    Lista adyacentes;
    boolean visitado;
    
    public Vertice(String usuario){
        this.usuario =  usuario;
        this.adyacentes = new Lista();
        visitado = false;
    }
}
