/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.sql.Date;

/**
 *
 * @author Flores
 */
public class DataPaciente {

    String cedula;
    String nombre;
    String apellido;
    String direcion;
    Date fechaNacimiento;
    String estadoCivil;
    String email;
    String idUsuario;
    String idConsultorio;

    public DataPaciente() {
    }

    public DataPaciente(String cedula, String nombre, String apellido, String direcion, Date fechaNacimiento, String estadoCivil, String email, String idUsuario, String idConsultorio) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direcion = direcion;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.email = email;
        this.idUsuario = idUsuario;
        this.idConsultorio = idConsultorio;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(String idConsultorio) {
        this.idConsultorio = idConsultorio;
    }
    
    

}
