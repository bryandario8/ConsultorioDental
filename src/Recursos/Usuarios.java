/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

/**
 *
 * @author Flores
 */
public class Usuarios {
    
   String idUsuario;
    String nombre;
    String apellido;
    String direccion;
    String email;
    String profesion;
    String cargo;
    String idConsultorio;
    String idUsuario2;
    

    public Usuarios(String nombre, String apellido,String cargo) {
        this.nombre=nombre;
        this.apellido=apellido;
        this.cargo=cargo;
    }

    public Usuarios(String idUsuario, String nombre, String apellido, String direccion, String email, String profesion, String cargo, String idConsultorio, String idUsuario2) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.profesion = profesion;
        this.cargo = cargo;
        this.idConsultorio = idConsultorio;
        this.idUsuario2 = idUsuario2;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(String idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public String getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(String idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }
    
}
