/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flores
 */
public class ConexionSQL {
    Connection co;
    Statement sta;
    public ConexionSQL(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            co = DriverManager.getConnection("jdbc:mysql://localhost/consultoriodental?user=root&password=");
            sta =co.createStatement();
            System.out.println("Conectado");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("No Conectado");
        }catch(SQLException ee){
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE,null,ee);
            System.out.println("No Conectado");
        }
    }

    public Connection getCo() {
        return co;
    }

    public Statement getSta() {
        return sta;
    }
}
