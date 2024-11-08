/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coco;

import java.util.ArrayList;

/**
 *
 * @author pvespucio
 */
public class Proyecto {
    
    int idProyecto;
    String nombre;
    ArrayList<Task> tareas= new ArrayList<>();

    public Proyecto(int idProyecto, String nombre) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.tareas=new  ArrayList<>();
    }
    
    public void AgregarTarea(Task t){
        tareas.add(t);
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "idProyecto=" + idProyecto + ", nombre=" + nombre + ", tareas=" + tareas + '}';
    }
    
    
    
    
    
}
