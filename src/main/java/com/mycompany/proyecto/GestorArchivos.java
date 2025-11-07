package com.mycompany.proyecto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class GestorArchivos {

    public static Grafo cargarGrafoDesdeArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            
            Grafo grafoCargado = new Grafo(10);

            try (BufferedReader br = new BufferedReader(new FileReader(archivoSeleccionado))) {
                String linea;
                boolean leyendoUsuarios = false;
                boolean leyendoRelaciones = false;

                while ((linea = br.readLine()) != null) {
                    linea = linea.trim();
                    
                    if (linea.isEmpty()) continue;

                    if (linea.equals("usuarios")) {
                        leyendoUsuarios = true;
                        leyendoRelaciones = false;
                        continue;
                    } else if (linea.equals("relaciones")) {
                        leyendoRelaciones = true;
                        leyendoUsuarios = false;
                        continue;
                    }

                    if (leyendoUsuarios) {
                        grafoCargado.insertar(linea); 
                    } 
                    else if (leyendoRelaciones) {
                        String[] partes = linea.split(",\\s*"); 
                        if (partes.length == 2) {
                            String origen = partes[0];
                            String destino = partes[1];
                            grafoCargado.agregarArista(origen, destino);
                        }
                    }
                }
                
                JOptionPane.showMessageDialog(null, "¡Grafo cargado exitosamente!");
                return grafoCargado; 

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        return null; 
    }

    public static void guardarGrafoEnArchivo(Grafo grafo) {
        if (grafo == null) {
            JOptionPane.showMessageDialog(null, "No hay grafo cargado para guardar.");
            return;
        }
        
        String nombreArchivo = "red_social.txt"; 

        try (PrintWriter pw = new PrintWriter(nombreArchivo)) {
            
            pw.println("usuarios");
            for (Vertice v : grafo.getVertices()) {
                pw.println(v.usuario);
            }
            pw.println("relaciones");
            for (Vertice v : grafo.getVertices()) {
                for (String seguidor : v.adyacentes.getNombres()) {
                    pw.println(v.usuario + ", " + seguidor);
                }
            }
            JOptionPane.showMessageDialog(null, "¡Repositorio actualizado en " + nombreArchivo + "!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
