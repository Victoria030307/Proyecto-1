/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author victo
 */
public class Lista {

    Nodo primero;
    int size;

    public Lista() {
        this.primero = null;
        this.size = 0;
    }

    public void insertar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (this.primero == null) {
            this.primero = nuevo;
        

        } else {
            Nodo aux = this.primero;

            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }
    
    public void eliminar(int dato) {
        Nodo aux = this.primero;
        
        if(this.primero == null){
            return;
        }
        
        if(aux.usuario == dato){
            this.primero = aux.siguiente;
        } else {
            while(aux.siguiente != null && aux.siguiente.usuario != dato){
                aux = aux.siguiente;
            }
            
            if(aux.siguiente == null){
                return;
            }
            aux.siguiente = aux.siguiente.siguiente;
            

        
        }
        
        
    }
    
    public String mostrar (){
        String lista = "";
        Nodo aux = this.primero;
        
        while(aux != null){
            lista += aux.usuario + "\n";
            aux = aux.siguiente;
        }
        
        System.out.println(lista);
  
        return lista;
    }
    
    public Nodo buscar (int dato){
        Nodo aux = this.primero;

        while(aux != null && aux.usuario != dato){
            
            aux = aux.siguiente;
        }
        return aux; 
    }
    
    
}
