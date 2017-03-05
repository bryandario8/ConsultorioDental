/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.util.ArrayList;

/**
 *
 * @author Flores
 */

public class Person {
    String ruc;
    String firstName;
    String lastName;
    String direccion;
    String fechaNacimiento;
    String estadoCivil;
    String sexo;
    String email;
    String tipoDeSangre;
    String telefonos; 
    String idUsuario;
    int tipo;
 
    public Person(String fName, String lName, String email) {
        this.firstName = new String(fName);
        this.lastName = new String(lName);
        this.email = new String(email);
    }
    
    public Person(String ruc, String Name, String direccion, String telefono, String email, int tipo ){
        
        this.ruc = ruc;
        this.firstName = Name;
        this.direccion = direccion;
        this.telefonos = telefono;
        this.email = email;
        this.tipo = tipo;
    }
    
    public Person(String ruc, String Name, String direccion, String telefono, String email, String idUsuario ){
        
        this.ruc = ruc;
        this.firstName = Name;
        this.direccion = direccion;
        this.telefonos = telefono;
        this.email = email;
        this.idUsuario = idUsuario;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void imprimir(){
        
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoDeSangre() {
        return tipoDeSangre;
    }

    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
 
}
