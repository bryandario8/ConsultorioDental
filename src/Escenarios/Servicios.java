/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Constantes.Settings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author FABKME
 */
public class Servicios {
Boolean state = false;
    Boolean selectSuministros = true;
    Boolean selectProveedor = true;
    Boolean selectCentroEspecializado = true;
    Boolean selectBack = true;
    StackPane rootPane;
    Button Suministros;
    Button Proveedor;
    Button CentroEspecializado;
    Button back;
    VBox botones1;
    Boolean exit = false;
    ImageView fondo;
    String cargo;

    public Servicios() {
        this.rootPane = new StackPane();

        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo4.png"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 130);
        rootPane.getChildren().add(fondo);

        this.Suministros = new Button("Suministros");
        Suministros.setPrefSize(130, 30);
        
        this.Proveedor = new Button("Proveedor");
        Proveedor.setPrefSize(130, 30);
        
        this.CentroEspecializado = new Button("Centro Especializado");
        CentroEspecializado.setPrefSize(130, 30);
        
        this.back = new Button ("Back");
        back.setPrefSize(130, 30);
        
        this.botones1 = new VBox();
        this.botones1.getChildren().addAll(this.Suministros, this.Proveedor, this.CentroEspecializado,this.back);
        this.botones1.setAlignment(Pos.CENTER);
        this.botones1.setSpacing(20);

        this.rootPane.getChildren().addAll(this.botones1);
        setupButton();
    }

    private void setupButton() {
        Suministros.setOnAction(e->{
        this.selectSuministros=false;
        });
        Proveedor.setOnAction(e->{
        this.selectProveedor=false;
        });
        CentroEspecializado.setOnAction(e->{
        this.selectCentroEspecializado=false;
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

    public Boolean getSelectSuministros() {
        return selectSuministros;
    }

    public void setSelectSuministros(Boolean selectSuministros) {
        this.selectSuministros = selectSuministros;
    }

    public Boolean getSelectProveedor() {
        return selectProveedor;
    }

    public void setSelectProveedor(Boolean selectProveedor) {
        this.selectProveedor = selectProveedor;
    }

    public Boolean getSelectCentroEspecializado() {
        return selectCentroEspecializado;
    }

    public void setSelectCentroEspecializado(Boolean selectCentroEspecializado) {
        this.selectCentroEspecializado = selectCentroEspecializado;
    }

    public Button getSuministros() {
        return Suministros;
    }

    public void setSuministros(Button Suministros) {
        this.Suministros = Suministros;
    }

    public Button getProveedor() {
        return Proveedor;
    }

    public void setProveedor(Button Proveedor) {
        this.Proveedor = Proveedor;
    }

    public Button getCentroEspecializado() {
        return CentroEspecializado;
    }

    public void setCentroEspecializado(Button CentroEspecializado) {
        this.CentroEspecializado = CentroEspecializado;
    }

    public Button getBack() {
        return back;
    }

    public void setBack(Button back) {
        this.back = back;
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
                this.botones1.getChildren().addAll(this.Suministros, this.Proveedor, this.CentroEspecializado,this.back);
                break;
            case "Profesional":
                System.out.println("Ingreso un administrador");
                this.botones1.getChildren().clear();
                this.botones1.getChildren().addAll(this.Suministros, this.Proveedor, this.CentroEspecializado,this.back);
                break;
            case "Asistente":
                System.out.println("Ingreso un administrador");
                this.botones1.getChildren().clear();
                this.botones1.getChildren().addAll(this.Suministros, this.Proveedor,this.back);
                break;
            default:
                System.out.println("Ahora no joven");
                break;
        }
    }

}
