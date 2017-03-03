/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FABKME
 */
public class SuministroData {
    String nombre;
    int cantidad;
    Date fechaVencimiento;
    Date fechaRegistro;
    String idProveedor;
    
    
 
    public SuministroData(String nombre, int cantidad, Date fechaVencimiento) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    
    

    


}
