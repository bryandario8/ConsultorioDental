/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Recursos.ConexionSQL;
import Recursos.Person;
import Constantes.Settings;
import Recursos.Usuario;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Flores
 */
public class Login {

    Boolean state = true;
    Boolean state2 = true;
    StackPane rootPane;
    VBox lista;
    Label textnombre;
    TextField tf_nombre;
    Label textclave;
    TextField tf_clave;
    Button login;
    Button exit;
    HBox hbox_usuario;
    HBox hbox_clave;
    ImageView fondo;
    ConexionSQL conect;
    public Usuario usuarioTemp;
    Text mensaje;

    public Login(ConexionSQL conect) {
        this.conect = conect;
        rootPane = new StackPane();

        mensaje = new Text();
        mensaje.setVisible(false);
        mensaje.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        mensaje.setFill(Color.RED);

        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo.jpg"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 20);
        rootPane.getChildren().add(fondo);

        lista = new VBox();
        textnombre = new Label();
        tf_nombre = new TextField();
        textclave = new Label();
        tf_clave = new TextField();

        login = new Button("Login");
        exit = new Button("Exit");

        hbox_usuario = new HBox();
        hbox_usuario.getChildren().addAll(textnombre, tf_nombre);
        hbox_usuario.setAlignment(Pos.CENTER);
        hbox_clave = new HBox();
        hbox_clave.getChildren().addAll(textclave, tf_clave);
        hbox_clave.setAlignment(Pos.CENTER);
        seteo();
        HBox contenedor = new HBox();
        contenedor.getChildren().addAll(login, exit);
        contenedor.setAlignment(Pos.CENTER);
        lista.getChildren().addAll(hbox_usuario, hbox_clave, contenedor, mensaje);
        lista.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(lista);
        metOnClick();
    }

    public void seteo() {
        textnombre.setText("Usuario: ");
        textnombre.setFont(new Font("helvetica", 20.0));
        textnombre.setTextFill(Color.WHITE);

        tf_nombre.setPromptText("Ingrese su usuario");

        textclave.setText("Clave: ");
        textclave.setFont(new Font("helvetica", 20.0));
        textclave.setTextFill(Color.WHITE);

        tf_clave.setPromptText("Ingrese su clave");
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

    public void metOnClick() {
        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (tf_nombre.textProperty().get().equals("")) {
                    if (tf_clave.textProperty().get().equals("")) {
                        mensaje.setText("Ingrese los campos por favor");
                    }
                    mensaje.setText("Ingrese los campos por favor");

                } else {
                    try {
                        ResultSet rs = conect.getSta().executeQuery("select * from usuario where nombre="
                                + "'" + tf_nombre.textProperty().get() + "'" + ";");
                        int size = 0;
                        while (rs.next()) {
                            usuarioTemp = new Usuario(rs.getString("idUsuario"), rs.getString("nombre"), rs.getString("apellido"),
                            rs.getString("direccion"), rs.getString("email"), rs.getString("profesion"),
                            rs.getString("cargo"), rs.getString("idConsultorio"), rs.getString("idUsuario2"));

                            System.out.println(rs.getString("nombre"));
                            size++;

                        }

                        if (size == 1) {
                            if (tf_clave.textProperty().get().equals(usuarioTemp.getApellido())) {
                                state2 = false;
                            } else {
                                mensaje.setText("Contraseña mal ingresada");
                            }
                        }
                        if (size == 0) {
                            mensaje.setText("No existe ningun usuario con ese nombre");
                        }

                    } catch (Exception error) {
                    }
                }
                state = false;
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });

    }

    public Usuario getUsuarioTemp() {
        return usuarioTemp;
    }

    public void setUsuarioTemp(Usuario usuarioTemp) {
        this.usuarioTemp = usuarioTemp;
    }

    public Boolean getState2() {
        return state2;
    }

    public void setState2(Boolean state2) {
        this.state2 = state2;
    }
    
    

}
