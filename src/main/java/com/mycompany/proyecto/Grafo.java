/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author victo
 */
public class Grafo {
    int num_vertices;
    Vertice[] usuarios;
    
    public Grafo(int num){
        this.num_vertices = num;
        //Creamos el array con un +20% de capacidad para agregar nuevos usuarios
        usuarios = new Vertice[num + num*(1/5)];
    }
    
    public void agrgandarCapacidad(){
        Vertice[] nuevoArreglo = new Vertice[num_vertices + num_vertices*(1/5)];
        
        for (int i = 0; i < num_vertices; i++) {
            nuevoArreglo[i] = this.usuarios[i];
        }
        
        this.usuarios = nuevoArreglo;
    }
    
    public void insertar(String usuario){
        Vertice nuevo = new Vertice(usuario);
        boolean insertado = false;
        for (int i = 0; i < this.usuarios.length; i++) {
            if(this.usuarios[i] == null){
                this.usuarios[i] =  nuevo;
                insertado = true;
                break;
            }
        }
        if(!insertado){
            this.agrgandarCapacidad();
            this.insertar(usuario);
        }
    }
    
    public Vertice buscarUsuario(String nombre){
        for (Vertice usuario : this.usuarios) {
            if (usuario != null && usuario.usuario.equals(nombre)) {
                return usuario;
            }
        }
        return null;
    }
    
    public void agregarArista( String nombre_origen, String nombre_destino){
        Vertice origen = this.buscarUsuario(nombre_origen);
        Vertice destino = this.buscarUsuario(nombre_destino);
        
        if(origen != null && destino != null){
            origen.adyacentes.insertar(nombre_destino);
        }
    }
    
    public void eliminar(String nombre){
        //ELIMINANOS EL  VERTICE DEL GRAFO
        for (int i = 0; i < this.usuarios.length; i++) {
            if (this.usuarios[i] != null && this.usuarios[i].usuario.equals(nombre)) {
               this.usuarios[i] = null ;
               this.num_vertices --;
               break;
            }
        }
        
        //ELIMINAMOS LAS FLECHAS QUE GUADRABAN AL VERTICE
        for (Vertice usuario : this.usuarios) {
            if (usuario != null) {
                usuario.adyacentes.eliminar(nombre);
            }
        }
    }
    
    
    
    
}
