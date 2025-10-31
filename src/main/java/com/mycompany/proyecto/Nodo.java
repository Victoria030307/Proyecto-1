/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author victo
 */
public class Nodo {
    Nodo siguiente;
    int usuario;
    
    public Nodo (int dato){
        this.siguiente = null;
        this.usuario = dato;
    }
}
