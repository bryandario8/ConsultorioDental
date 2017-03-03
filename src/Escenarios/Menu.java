/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Constantes.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Flores
 */
public class Menu {

    Boolean state = false;
    Boolean selectPacientes = true;
    Boolean selectCita = true;
    Boolean selectServicios = true;
    Boolean selectUsuarios = true;
    Boolean selectBack = true;
    StackPane rootPane;
    Button pacientes;
    Button cita;
    Button servicios;
    Button usuarios;
    Button back;
    HBox botones1;
    Boolean exit = false;
    ImageView fondo;
    String cargo;

    public Menu() {
        this.rootPane = new StackPane();

        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo.jpg"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 20);
        rootPane.getChildren().add(fondo);

        this.pacientes = new Button("Pacientes");
        this.cita = new Button(" Cita ");
        this.servicios = new Button("Servicios");
        this.usuarios = new Button("Usuarios");
        this.back=new Button ("Back");
        
        this.botones1 = new HBox();
        this.botones1.getChildren().addAll(this.pacientes, this.cita, this.servicios, usuarios,back);
        this.botones1.setAlignment(Pos.CENTER);
        this.botones1.setSpacing(80);

        this.rootPane.getChildren().addAll(this.botones1);
        setupButton();
    }

    private void setupButton() {
        pacientes.setOnAction(e -> {
            this.selectPacientes = false;
        });
        cita.setOnAction(e -> {
            this.selectCita = false;
        });
        servicios.setOnAction(e -> {
            this.selectServicios = false;
        });
        usuarios.setOnAction(e -> {
            this.selectUsuarios = false;
        });
        back.setOnAction(e -> {
            this.selectBack = false;
        });

    }

    public StackPane getRootPane() {
        return rootPane;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getExit() {
        return exit;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Boolean getSelectPacientes() {
        return selectPacientes;
    }

    public void setSelectPacientes(Boolean selectPacientes) {
        this.selectPacientes = selectPacientes;
    }

    public Boolean getSelectCita() {
        return selectCita;
    }

    public void setSelectCita(Boolean selectCita) {
        this.selectCita = selectCita;
    }

    public Boolean getSelectServicios() {
        return selectServicios;
    }

    public void setSelectServicios(Boolean selectServicios) {
        this.selectServicios = selectServicios;
    }

    public Boolean getSelectUsuarios() {
        return selectUsuarios;
    }

    public void setSelectUsuarios(Boolean selectUsuarios) {
        this.selectUsuarios = selectUsuarios;
    }

    public Boolean getSelectBack() {
        return selectBack;
    }

    public void setSelectBack(Boolean selectBack) {
        this.selectBack = selectBack;
    }
    

    public void actualizaarScene() {
        switch (this.cargo) {
            case "Administrador":
                System.out.println("Ingreso un administrador");
                this.botones1.getChildren().clear();
                this.botones1.getChildren().addAll(this.pacientes, this.cita, this.servicios, usuarios,back);
                break;
            case "Profesional":
                System.out.println("Ingreso un administrador");
                this.botones1.getChildren().clear();
                this.botones1.getChildren().addAll(this.pacientes, this.cita, this.servicios,back);
                break;
            case "Asistente":
                System.out.println("Ingreso un administrador");
                this.botones1.getChildren().clear();
                this.botones1.getChildren().addAll(this.cita, this.servicios,back);
                break;
            default:
                System.out.println("Ahora no joven");
                break;
        }
    }

}
