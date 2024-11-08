/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coco;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pvespucio
 */
public class Task {
    
    int idTask, prioridad;
    String nombre;
    Date fechaLimite;
    boolean completada;

    public Task(int idTask, int prioridad, String nombre, Date fechaLimite, boolean completada) {
        this.idTask = idTask;
        this.prioridad = prioridad;
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.completada = completada;
    }

    public Task(int prioridad, String nombre, Date fechaLimite) {
        this.prioridad = prioridad;
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
    }
    
    

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

//    @Override
//    public String toString() {
//        return "Task{" + "idTask=" + idTask 
//                + ", prioridad=" + prioridad 
//                + ", nombre=" + nombre 
//                + ", fechaLimite=" + fechaLimite 
//                + ", completada=" + completada + '}';
//    }
    
    private String prioridadToString() {
        String prioridadString = "";
        switch (prioridad) {
            case 0:
                prioridadString = "Alta";
                break;
            case 1:
                prioridadString = "Media";
                break;
            case 2:
                prioridadString = "Baja";
                break;
            default:
                prioridadString = "?!";
        }
        
        return prioridadString;
    }
    
       @Override
    public String toString() {
        
        return idTask + " - " + nombre + " - " + new SimpleDateFormat("dd-MM-yyyy").format(fechaLimite) + " - Prioridad: " + prioridadToString();
    }
    
    
    
    
}
