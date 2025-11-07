/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;


import java.util.ArrayList; 
import java.util.List;    

/**
 *
 * @author victo
 */

/*
Esta clase se encarga de guardar las aristas de un  vertice.
Es decir, cada vertice tiene uan lista con el nombre de cada usario que sigue.

Vertices    Lista de seguidos
....
@victoria  Sigue a: @juan -> @pedro (@juan seria el primero de la lista)
*/
public class Lista {

    Nodo primero;
    int size;

    public Lista() {
        this.primero = null;
        this.size = 0;
    }

    public void insertar(String dato) {
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
        size++;
    }
    
    public void eliminar(String dato) {
        Nodo aux = this.primero;
        
        if(this.primero == null){
            return;
        }
        
        if(aux.usuario.equals(dato)){
            this.primero = aux.siguiente;
        } else {
            while(aux.siguiente != null && !aux.siguiente.usuario.equals(dato)){
                aux = aux.siguiente;
            }
            
            if(aux.siguiente == null){
                return;
            }
            aux.siguiente = aux.siguiente.siguiente;
            
            size--;
        
        }
        
        
    }
    

public List<String> getNombres() {
    List<String> nombres = new ArrayList<>();
    Nodo aux = this.primero;
    
    while (aux != null) {
        nombres.add(aux.usuario); 
        aux = aux.siguiente;
    }
    
    return nombres; 
}
    
    public String mostrar (){
        String lista = "";
        Nodo aux = this.primero;
        
        while(aux != null){
            lista += aux.usuario + ", ";
            aux = aux.siguiente;
        }
  
        return lista;
    }
    
    public Nodo buscar (String dato){
        Nodo aux = this.primero;

        while(aux != null && !aux.usuario.equals(dato)){
            
            aux = aux.siguiente;
        }
        return aux; 
    }
    
    
}
