/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Flores
 */
public class CitaData {

    String idCita;
    Time time;
    Date fecha;
    String detalles;
    Time duracion;
    String idUsuario;
    String idPaciente;

    public CitaData(String idCita, Time time, Date fecha, String detalles, Time duracion, String idUsuario, String idPaciente) {
        this.idCita = idCita;
        this.time = time;
        this.fecha = fecha;
        this.detalles = detalles;
        this.duracion = duracion;
        this.idUsuario = idUsuario;
        this.idPaciente = idPaciente;
    }
    

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
    
}
