/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultoriodental;

import Escenarios.CentroEspecializado;
import Escenarios.Citas;
import Recursos.ConexionSQL;
import Escenarios.CrearCita;
import Escenarios.CrearPaciente;
import Escenarios.CrearUsuario;
import Escenarios.EnCita;
import Escenarios.Login;
import Escenarios.Menu;
import Escenarios.Proveedor;
import Escenarios.Servicios;
import Escenarios.Suministro;
import Escenarios.Usuario;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Flores
 */
public class SceneOrganizer {

    Boolean state = true;
    Scene scene;
    CrearCita crearCita;
    EnCita enCita;
    ConexionSQL conect;
    Timeline timeline;
    Login login;
    Menu menu;
    Citas citas;
    Servicios servicios;
    Suministro suministro;
    Proveedor proveedor;
    CentroEspecializado centro;
    
    CrearPaciente crearPaciente;
    Usuario usuario;

    public SceneOrganizer() {
        ConexionSQL conect = new ConexionSQL();
        login = new Login(conect);
       
        usuario = new Usuario(conect);
        crearCita = new CrearCita(conect);
        enCita = new EnCita(conect);
        suministro = new Suministro(conect,login);
        proveedor = new Proveedor(conect);
        centro = new CentroEspecializado(conect,login);
        crearPaciente = new CrearPaciente(conect);
        
        citas = new Citas();
        
        
        servicios = new Servicios();
        menu = new Menu();
        scene = new Scene(login.getRootPane(), 800, 500);
        setupTimeline();
    }

    public Scene getScene() {
        return scene;
    }

    public Boolean getState() {
        return state;
    }

    public void setupTimeline() {
        KeyFrame kf = new KeyFrame(Duration.millis(100), new changWindow());
        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Clase interna TimeHandler elige que ventana debe presentarse segun el
     * estado en que se encuentre
     */
    private class changWindow implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            if (login.getState()) {
                scene.setRoot(login.getRootPane());
                login.setState(false);
                
            }
            if(!login.getState2()){
                crearCita.ponerLogin(login);
                menu.setState(true);
                login.setState2(true);
            }
            
            
            
            
            if (menu.getState()) { 
                menu.setCargo(login.getUsuarioTemp().getCargo());
                menu.actualizaarScene();
                scene.setRoot(menu.getRootPane());
                menu.setState(false);
            }
            if(!menu.getSelectPacientes()){
                crearPaciente.setState(true);
                menu.setSelectPacientes(true);
            }
            if(!menu.getSelectCita()){
                citas.setState(true);
                menu.setSelectCita(true);
            }
            if(!menu.getSelectUsuarios()){
                usuario.setState(true);
                menu.setSelectUsuarios(true);
            }
            if(!menu.getSelectServicios()){
                servicios.setState(true);
                menu.setSelectServicios(true);
            }
            if(!menu.getSelectLogin()){
                scene.setRoot(login.getRootPane());
                login.seteo();
                login.setState(true);
                menu.setSelectLogin(true);

            }
            if(usuario.getState()){
                scene.setRoot(usuario.getRootPane());
                usuario.setState(false);
                usuario.llenarTabla();
            }
            if(!usuario.getSelectBack()){
                menu.setState(true);
                usuario.setSelectBack(true);
            }
            if(servicios.getState()){
                servicios.setCargo(login.getUsuarioTemp().getCargo());
                servicios.actualizaarScene();
                scene.setRoot(servicios.getRootPane());
                servicios.setState(false);
            }
            if(!servicios.getSelectSuministros()){
                suministro.setState(true);
                servicios.setSelectSuministros(true);
            }
            if(!servicios.getSelectProveedor()){
                proveedor.setState(true);
                servicios.setSelectProveedor(true);
            }
            if(!servicios.getSelectCentroEspecializado()){
                centro.setState(true);
                servicios.setSelectCentroEspecializado(true);
            }
            if(!servicios.getSelectBack()){
                menu.setState(true);
                servicios.setSelectBack(true);
            }
            if (crearPaciente.getState()) {
                scene.setRoot(crearPaciente.getRootPane());
                crearPaciente.setState(false);
                crearPaciente.llenarTabla();
            } 
            if(!crearPaciente.getSelectBack()){
                menu.setState(true);
                crearPaciente.setSelectBack(true);
            }
            if(suministro.getState()){
                scene.setRoot(suministro.getRootPane());
                suministro.setState(false);
                suministro.llenarTabla();
            }
            if(!suministro.getSelectBack()){
                servicios.setState(true);
                suministro.setSelectBack(true);
            }
            if(proveedor.getState()){
                scene.setRoot(proveedor.getRootPane());
                proveedor.setState(false);
                proveedor.llenarTabla();
            }
            if(!proveedor.getSelectBack()){
                servicios.setState(true);
                proveedor.setSelectBack(true);
            }
            if(centro.getState()){
                scene.setRoot(centro.getRootPane());
                centro.setState(false);
                centro.llenarTabla();
            }
            if(!centro.getSelectBack()){
                servicios.setState(true);
                centro.setSelectBack(true);
            }
            if (citas.getState()) {
                citas.setCargo(login.getUsuarioTemp().getCargo());
                citas.actualizaarScene();
                scene.setRoot(citas.getRootPane());
                citas.setState(false);
            } 
            if(!citas.getSelectCrearCita()){
                crearCita.setState(true);
                citas.setSelectCrearCita(true);
            }
            if(!citas.getSelectEnCita()){
                enCita.setState(true);
                citas.setSelectEnCita(true);
            }
            if(!citas.getSelectNoSe()){
                //////noSe.setState(true);
                citas.setSelectNoSe(true);
            }
            if(!citas.getSelectBack()){
                menu.setState(true);
                citas.setSelectBack(true);
            }
            
            
            if (crearCita.getState()) { 
                scene.setRoot(crearCita.getRootPane());
                crearCita.setState(false);
            }
            if(!crearCita.getSelectBack()){
                citas.setState(true);
                crearCita.setSelectBack(true);
            }
            
            
            if (enCita.getState()) {
                scene.setRoot(enCita.getRootPane());
                enCita.setState(false);
            } 
            
        }
    }
}


