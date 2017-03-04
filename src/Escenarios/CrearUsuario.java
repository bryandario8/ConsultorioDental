/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Constantes.Settings;
import Recursos.ConexionSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 *
 * @author FABKME
 */
public class CrearUsuario {
StackPane rootPane;
    
    Boolean state = false;
    Boolean selectBack = true;
    
    ConexionSQL conect;
    
    ComboBox tf_consultorios;
    
    TextField dataIdUsuario;
    TextField dataNombre;
    TextField dataApellido;
    TextField dataDireccion;
    TextField dataEmail;
    TextField dataProfesion;
    TextField dataCargo;
    TextField dataIdUsuario2;
    
    Button clear;
    Button save;
    Button cancel;
    
    ImageView fondo;
    
    public CrearUsuario(ConexionSQL conect) {
        this.conect = conect;
        
        this.rootPane = new StackPane();
        
        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo2.jpg"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 130);
        
        Label lb_idUsuario = new Label("idUsuario : ");
        Label lb_nombre = new Label("Nombre : ");
        Label lb_apellido = new Label("Apellido : ");
        Label lb_direccion = new Label("Direccion : ");
        Label lb_email = new Label("Email : ");
        Label lb_profesion = new Label("Profesion : ");
        Label lb_cargo = new Label("Cargo : ");
        Label lb_consultorio = new Label("Consultorio : ");
        Label lb_idUsuario2 = new Label("idUsuario2 : ");
        
        lb_idUsuario.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb_nombre.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb_apellido.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb_direccion.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb_email.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb_profesion.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb_cargo.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb_consultorio.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lb_idUsuario2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        
        tf_consultorios = new ComboBox();
        llenarComboBoxConsultorios();
        tf_consultorios.setPromptText("Consultorios Disponibles");
        tf_consultorios.setEditable(true);
        
        dataIdUsuario = new TextField();
        
        dataNombre = new TextField();
        dataApellido = new TextField();
        dataDireccion = new TextField();
        dataEmail = new TextField();
        dataProfesion = new TextField();
        dataCargo = new TextField();
        dataIdUsuario2 = new TextField();
        
        HBox hBoxIdUsuario = new HBox();
        HBox hBoxNombre = new HBox();
        HBox hBoxApellido = new HBox();
        HBox hBoxDireccion = new HBox();
        HBox hBoxEmail = new HBox();
        HBox hBoxProfesion = new HBox();
        HBox hBoxCargo = new HBox();
        HBox hBoxConsultorio = new HBox();
        HBox hBoxIdUsuario2 = new HBox();
        
        hBoxIdUsuario.getChildren().addAll(lb_idUsuario, dataIdUsuario);
        hBoxNombre.getChildren().addAll(lb_nombre, dataNombre);
        hBoxApellido.getChildren().addAll(lb_apellido, dataApellido);
        hBoxDireccion.getChildren().addAll(lb_direccion, dataDireccion);
        hBoxEmail.getChildren().addAll(lb_email, dataEmail);
        hBoxProfesion.getChildren().addAll(lb_profesion, dataProfesion);
        hBoxCargo.getChildren().addAll(lb_cargo, dataCargo);
        hBoxConsultorio.getChildren().addAll(lb_consultorio, tf_consultorios);
        hBoxIdUsuario2.getChildren().addAll(lb_idUsuario2, dataIdUsuario2);
        
        hBoxIdUsuario.setAlignment(Pos.CENTER);
        hBoxNombre.setAlignment(Pos.CENTER);
        hBoxApellido.setAlignment(Pos.CENTER);
        hBoxDireccion.setAlignment(Pos.CENTER);
        hBoxEmail.setAlignment(Pos.CENTER);
        hBoxProfesion.setAlignment(Pos.CENTER);
        hBoxCargo.setAlignment(Pos.CENTER);
        hBoxConsultorio.setAlignment(Pos.CENTER);
        hBoxIdUsuario2.setAlignment(Pos.CENTER);
        
        clear = new Button("Limpiar");
        save = new Button("Guardar");
        cancel = new Button("Cancelar");
        
        HBox hBoxButtons = new HBox();
        hBoxButtons.getChildren().addAll(clear, save, cancel);
        hBoxButtons.setAlignment(Pos.CENTER);
        
        VBox datosUsuario = new VBox();
        datosUsuario.getChildren().addAll(hBoxIdUsuario, hBoxNombre, hBoxApellido, hBoxDireccion, hBoxEmail, hBoxProfesion, hBoxCargo, hBoxConsultorio, hBoxIdUsuario2, hBoxButtons);
        datosUsuario.setAlignment(Pos.CENTER);
        datosUsuario.setSpacing(15);
        
        rootPane.getChildren().addAll(fondo, datosUsuario);
        rootPane.setPrefSize(800, 400);
        rootPane.setAlignment(Pos.CENTER);
        
        setButtons();
    }
    
    public StackPane getRootPane() {
        return rootPane;
    }
    
    public void setButtons() {
        
        this.cancel.setOnAction(e -> {
            this.selectBack = false;
        });
        this.save.setOnAction(e -> {
            System.out.println("Entro");
            if (!this.dataIdUsuario.textProperty().get().equals("") && !this.dataNombre.textProperty().get().equals("")
                    && !this.dataApellido.textProperty().get().equals("") && !this.dataDireccion.textProperty().get().equals("")
                    && !this.dataEmail.textProperty().get().equals("") && !this.dataProfesion.textProperty().get().equals("")
                    && !this.dataCargo.textProperty().get().equals("")) {
                if ((this.dataIdUsuario.textProperty().get().length() <= 10) && (this.dataNombre.textProperty().get().length() <= 30)
                        && (this.dataApellido.textProperty().get().length() <= 30) && this.dataDireccion.textProperty().get().length() <= 30
                        && this.dataEmail.textProperty().get().length() <= 30 && this.dataProfesion.textProperty().get().length() <= 30
                        && this.dataCargo.textProperty().get().length() <= 30 && dataIdUsuario2.textProperty().get().length() <= 10) {
                    try {
                        System.out.println("Entro1");
                        PreparedStatement pps = this.conect.getCo().prepareStatement("INSERT INTO usuario(idUsuario, nombre, apellido, direccion, email, profesion, cargo, idConsultorio, idUsuario2) VALUES(?,?,?,?,?,?,?,?,?)");
                        pps.setString(1, this.dataIdUsuario.textProperty().get());
                        pps.setString(2, this.dataNombre.textProperty().get());
                        pps.setString(3, this.dataApellido.textProperty().get());
                        pps.setString(4, this.dataDireccion.textProperty().get());
                        pps.setString(5, this.dataEmail.textProperty().get());
                        pps.setString(6, this.dataProfesion.textProperty().get());
                        pps.setString(7, this.dataCargo.textProperty().get());
                        pps.setString(8, (String) tf_consultorios.getValue());
                        pps.setString(9, dataIdUsuario2.textProperty().get());
                        
                        pps.executeUpdate();
                        System.out.println("Entro2");
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        
                    }
                    
                }
            }
            
        });
        this.clear.setOnAction(e -> {
            dataIdUsuario.clear();
            dataNombre.clear();
            dataApellido.clear();
            dataDireccion.clear();
            dataEmail.clear();
            dataProfesion.clear();
            dataCargo.clear();
            dataIdUsuario2.clear();
            System.out.println(tf_consultorios.getValue());
            tf_consultorios.getItems().clear();
            llenarComboBoxConsultorios();
            System.out.println(tf_consultorios.getValue());
            
        });
    }
    
    public void llenarComboBoxConsultorios() {
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select * from consultorio;");
            while (rs.next()) {
                this.tf_consultorios.getItems().add(rs.getString("idConsultorio"));
                System.out.println(rs.toString());
            }
        } catch (Exception error) {
        }
        
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getSelectBack() {
        return selectBack;
    }

    public void setSelectBack(Boolean selectBack) {
        this.selectBack = selectBack;
    }
    
    
}
