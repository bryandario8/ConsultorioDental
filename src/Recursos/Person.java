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
    String firstName;
    String lastName;
    String direccion;
    String fechaNacimiento;
    String estadoCivil;
    String sexo;
    String email;
    String tipoDeSangre;
    ArrayList<String> telefonos; 
    int tipo;
 
    public Person(String fName, String lName, String email) {
        this.firstName = new String(fName);
        this.lastName = new String(lName);
        this.email = new String(email);
    }
    
    public Person(String Name, String telefono, String email, int tipo ){
        ArrayList<String> lista= new ArrayList<>();
        lista.add(telefono);
        this.firstName = Name;
        this.telefonos = lista;
        this.email = email;
        this.tipo = tipo;
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
 
}
