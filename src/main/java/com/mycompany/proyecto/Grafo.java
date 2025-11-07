/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author victo
 */

/*
Esta clase es la que guarda al grafo.
Tiene un arreglo con todos los vertices que existen en el grafo

*/
public class Grafo {
    int num_vertices;
    Vertice[] usuarios;
    
    public Grafo(int num){
        this.num_vertices = num;
        //Creamos el array con un +20% de capacidad para agregar nuevos usuarios
        usuarios = new Vertice[num + num*(1/5)];
    }
    
    public List<Vertice> getVertices() {
    List<Vertice> listaVertices = new ArrayList<>();
    for (Vertice v : this.usuarios) {
        if (v != null) {
            listaVertices.add(v);
        }
    }
    return listaVertices; 
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
                this.num_vertices ++;
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
    
    
    public String verGrafo(){
        String resultado = "";
        for (Vertice usuario : this.usuarios) {
            if (usuario != null) {
               resultado += usuario.usuario + "  Seguidos: " + usuario.adyacentes.mostrar() + "\n";
            }
        }
        return resultado;
    }
    
    // pasos para el dfs (algoritmo de Kosaraju) para detectar nodos fuertemente conectados) 
    
    
    //paso1
    private void dfsPaso1(Vertice v, Set<String> visitados, Stack<Vertice> pila) {
    visitados.add(v.usuario);
    for (String nombreAdyacente : v.adyacentes.getNombres()) { 
        if (!visitados.contains(nombreAdyacente)) {
            dfsPaso1(this.buscarUsuario(nombreAdyacente), visitados, pila);
        }
    }
    pila.push(v);
}
    
    //paso2
private void dfsPaso2(Vertice v, Set<String> visitados, List<Vertice> componenteActual) {
    visitados.add(v.usuario);
    componenteActual.add(v);
    for (String nombreAdyacente : v.adyacentes.getNombres()) {
        if (!visitados.contains(nombreAdyacente)) {
            dfsPaso2(this.buscarUsuario(nombreAdyacente), visitados, componenteActual);
        }
    }
}

//paso para obtener el transpuesto (aristas invertidas)
public Grafo getTranspuesto() {
    Grafo gTranspuesto = new Grafo(this.num_vertices);
    for (Vertice v : this.getVertices()) {
        gTranspuesto.insertar(v.usuario);
    }
    for (Vertice v : this.getVertices()) {
        for (String nombreAdyacente : v.adyacentes.getNombres()) { 
            gTranspuesto.agregarArista(nombreAdyacente, v.usuario);
        }
    }
    return gTranspuesto;
}
    
// Metodo principal para obtener los nodos o bien vertices fuertemente conectados) 
public List<List<Vertice>> encontrarComponentesFuertementeConectados() {
    Stack<Vertice> pila = new Stack<>();
    Set<String> visitados = new HashSet<>();
    for (Vertice v : this.getVertices()) {
        if (!visitados.contains(v.usuario)) {
            dfsPaso1(v, visitados, pila);
        }
    }
    Grafo gTranspuesto = this.getTranspuesto();
    visitados.clear(); 
    List<List<Vertice>> todosLosComponentes = new ArrayList<>();
    while (!pila.isEmpty()) {
        Vertice v = pila.pop();
        Vertice vTranspuesto = gTranspuesto.buscarUsuario(v.usuario);
        if (vTranspuesto != null && !visitados.contains(vTranspuesto.usuario)) {
            List<Vertice> componenteActual = new ArrayList<>();
            gTranspuesto.dfsPaso2(vTranspuesto, visitados, componenteActual);
            todosLosComponentes.add(componenteActual);
        }
    }
    return todosLosComponentes;
}


public String getFuertementeconectados() {
    String resultadoFinal = "";
    resultadoFinal += "--- Componentes Fuertemente Conectados ---\n";
    List<List<Vertice>> componentes = this.encontrarComponentesFuertementeConectados();
    int i = 1;
    for (List<Vertice> componente : componentes) {
        resultadoFinal += "Componente " + i + ": { ";
        String Nodos = "";
        for (Vertice v : componente) {
            Nodos += v.usuario + ", "; 
        }
        if (!Nodos.isEmpty()) {
            Nodos = Nodos.substring(0, Nodos.length() - 2);
        }
        resultadoFinal += Nodos + " }\n";
        i++;
    }
    resultadoFinal += "------------------------------------------\n";
    return resultadoFinal;
}
}


