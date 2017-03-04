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
import javafx.scene.layout.VBox;

/**
 *
 * @author Flores
 */
public class Citas {

    Boolean state = false;
    Boolean selectCrearCita = true;
    Boolean selectEnCita = true;
    Boolean selectNoSe = true;
    Boolean selectBack = true;
    
    StackPane rootPane;
    Button crearCita;
    Button enCita;
    Button noSe;
    Button back;
    VBox botones1;
    Boolean exit = false;
    ImageView fondo;
    String cargo;

    public Citas() {
        this.rootPane = new StackPane();

        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo4.png"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 130);
        rootPane.getChildren().add(fondo);

        this.crearCita = new Button("Crear cita");
        crearCita.setPrefSize(100, 30);
        
        this.enCita = new Button(" En Cita ");
        enCita.setPrefSize(100, 30);
        
        this.noSe = new Button(" ");
        noSe.setPrefSize(100, 30);
        
        this.back = new Button ("Back");
        back.setPrefSize(100, 30);
        
        this.botones1 = new VBox();
        this.botones1.getChildren().addAll(this.crearCita, this.enCita, this.noSe, this.back);
        this.botones1.setAlignment(Pos.CENTER);
        this.botones1.setSpacing(20);

        this.rootPane.getChildren().addAll(this.botones1);
        setupButton();
    }

    private void setupButton() {
        crearCita.setOnAction(e->{
        this.selectCrearCita=false;
        });
        enCita.setOnAction(e->{
        this.selectEnCita=false;
        });
        noSe.setOnAction(e->{
        this.selectNoSe=false;
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

    public Boolean getSelectCrearCita() {
        return selectCrearCita;
    }

    public void setSelectCrearCita(Boolean selectCrearCita) {
        this.selectCrearCita = selectCrearCita;
    }

    public Boolean getSelectEnCita() {
        return selectEnCita;
    }

    public void setSelectEnCita(Boolean selectEnCita) {
        this.selectEnCita = selectEnCita;
    }

    public Boolean getSelectNoSe() {
        return selectNoSe;
    }

    public void setSelectNoSe(Boolean selectNoSe) {
        this.selectNoSe = selectNoSe;
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
                this.botones1.getChildren().addAll(this.crearCita, this.enCita, this.noSe,this.back);
                break;
            case "Profesional":
                System.out.println("Ingreso un administrador");
                this.botones1.getChildren().clear();
                this.botones1.getChildren().addAll(this.crearCita, this.enCita, this.noSe,this.back);
                break;
            case "Asistente":
                System.out.println("Ingreso un administrador");
                this.botones1.getChildren().clear();
                this.botones1.getChildren().addAll(this.crearCita, this.noSe,this.back);
                break;
            default:
                System.out.println("Ahora no joven");
                break;
        }
    }

}
