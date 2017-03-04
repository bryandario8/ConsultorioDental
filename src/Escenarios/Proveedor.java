/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Constantes.Settings;
import Recursos.ConexionSQL;
import Recursos.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author FABKME
 */
public class Proveedor {
    Boolean state=false;
    Boolean selectPlus=true;
    Boolean selectBack=true;
    
    StackPane rootPane;
    private TableView<Person> table = new TableView<Person>();
    Person personTemp = null;
    private ObservableList<Person> data = FXCollections.observableArrayList();
    Button search;
    Button back;
    Button plus;
    String option = "";
    TextField tf_search;
    ConexionSQL conect;
    VBox info;
    Button ingresar;
    ImageView fondo;
    
    TextField dataRuc;
    TextField dataNombre;
    TextField dataDireccion;
    TextField dataTelefono;
    TextField dataEmail;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
    HBox hBoxRuc = new HBox();
        HBox hBoxNombre = new HBox();
        HBox hBoxDireccion = new HBox();
        HBox hBoxTelefono = new HBox();
        HBox hBoxEmail = new HBox();

    public Proveedor(ConexionSQL conect) {
        this.conect = conect;
        //this.personTemp=persona;
        this.rootPane = new StackPane();
        
        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo2.jpg"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 130);
       
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Ruc");
        rb1.setToggleGroup(group);
        rb1.setUserData("A");

        RadioButton rb2 = new RadioButton("Nombre");
        rb2.setToggleGroup(group);
        rb2.setUserData("B");

        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    option = group.getSelectedToggle().getUserData().toString();
                    System.out.println(group.getSelectedToggle().getUserData().toString());
                }
            }
        });
        HBox contenedorRadio = new HBox();

        contenedorRadio.getChildren().add(rb1);
        contenedorRadio.getChildren().add(rb2);
        contenedorRadio.setSpacing(10);

        tf_search = new TextField("");

        search = new Button("Search");
        back = new Button("Back");
        plus=new Button("+");
        
        HBox contenedor1 = new HBox();

        table.setEditable(true);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        //ArrayList
        TableColumn telefonoCol = new TableColumn("Telefono");
        telefonoCol.setMinWidth(100);
        telefonoCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("telefono"));   // incluir telefono como una Lista de String (en este caso sólo 1)

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));

        table.getColumns().addAll(nameCol, telefonoCol, emailCol);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {

            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                System.out.println(newValue.getFirstName());
                personTemp = newValue;
                info.setVisible(true);
            }
        });

        final ScrollPane sp = new ScrollPane();
        sp.setVmax(3);
        sp.setPrefSize(400, 375);
        sp.setContent(table);

        final ScrollPane sp1 = new ScrollPane();
        sp1.setVmax(3);
        sp1.setPrefSize(400, 375);

       
        
        
        Label label = new Label("Datos");
        label.setFont(new Font("Arial", 20));
        ingresar=new Button ("Ingresar");
        Label ruc = new Label("RUC : ");
        ruc.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label nombre = new Label("Nombre : ");
        nombre.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label direccion = new Label("Direccion : ");
        direccion.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label telefono = new Label("Telefono : ");
        telefono.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label email = new Label("Email : ");
        email.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        
        dataRuc = new TextField();
        dataNombre = new TextField();
        dataDireccion = new TextField();
        dataTelefono = new TextField();
        dataEmail = new TextField();
       

        
        
        hBoxRuc.getChildren().addAll(ruc);
        hBoxNombre.getChildren().addAll(nombre);
        hBoxDireccion.getChildren().addAll(direccion);
        hBoxTelefono.getChildren().addAll(telefono);
        hBoxEmail.getChildren().addAll(email);
        

        VBox info1 = new VBox();
        info1.getChildren().addAll(new Separator(),hBoxRuc, hBoxNombre, hBoxDireccion, hBoxTelefono, hBoxEmail, new Separator());

        info = new VBox();
        info.getChildren().addAll(info1, sp1);
        
        contenedor1.getChildren().addAll(contenedorRadio, tf_search, search, back,plus);
        VBox contenedorGeneral = new VBox();
        HBox contenedor2 = new HBox();
        contenedor2.getChildren().addAll(sp, info);
        contenedorGeneral.getChildren().addAll(contenedor1, contenedor2);
        rootPane.getChildren().addAll(fondo, contenedorGeneral);
        
        setButtons();
        info.setVisible(false);

        

        

        
    }

    public StackPane getRootPane() {
        return rootPane;
    }

   
        public void setButtons() {
        this.search.setOnAction(e -> {
            table.getItems().clear();
            data.clear();
            switch (this.option) {
                case "":
                    System.out.println("ingrese algo");
                    break;
                case "A":
                    System.out.println("opcion A");
                    if (this.tf_search.textProperty().get().equals("")) {

                    } else {
                        try {
                            ResultSet rs = this.conect.getSta().executeQuery("select * from proveedor where ruc="
                                    + "'" + this.tf_search.textProperty().get() + "'" + ";");

                            while (rs.next()) {
                                personTemp = new Person(rs.getString("nombre"),  rs.getString("telefono"), rs.getString("email"), 1);
                                this.data.add(personTemp);
                                System.out.println(rs.getString("nombre"));

                            }
                            if (data.isEmpty()) {
                                this.selectPlus = false;   
                            } else {
                                table.setItems(data);
                            }
                        } catch (Exception error) {
                        }
                    }
                    break;
                case "B":
                    System.out.println("opcion B");
                    if (this.tf_search.textProperty().get().equals("")) {
                    } else {
                        try {
                            ResultSet rs = this.conect.getSta().executeQuery("select * from proveedor where nombre like "
                                    + "'%" + this.tf_search.textProperty().get() + "%'" + ";");
                            int size = 0;
                            while (rs.next()) {
                                personTemp = new Person(rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"), 1);
                                this.data.add(personTemp);
                                System.out.println(rs.getString("nombre"));
                                size++;

                            }
                            if (data.isEmpty()) {
                                this.selectPlus = false; 
                            } else {
                                if (size != 1) {
                                    System.out.println("OK");
                                    table.setItems(data);
                                }

                            }
                        } catch (Exception error) {
                        }
                    }
                    break;
                
                default:
                    System.out.println("invalido");
                    break;
            }
        });
        this.back.setOnAction(e->{
            this.selectBack=false;
        });
        this.plus.setOnAction(e->{
            if (this.selectPlus==false){
                hBoxRuc.getChildren().add( dataRuc);
                hBoxNombre.getChildren().add( dataNombre);
                hBoxDireccion.getChildren().add(dataDireccion);
                hBoxTelefono.getChildren().add( dataTelefono);
                hBoxEmail.getChildren().add(dataEmail);
                
                info.getChildren().add(ingresar);
                info.setVisible(true);
            }
                    
            
        });
    
        ingresar.setOnAction(e->{
            System.out.println("Entro1");
            if (!this.dataRuc.textProperty().get().equals("") && !this.dataNombre.textProperty().get().equals("")
                    && !this.dataDireccion.textProperty().get().equals("") && !this.dataTelefono.textProperty().get().equals("")
                    && !this.dataEmail.textProperty().get().equals("") ) {
                if ((this.dataRuc.textProperty().get().length() <= 10) && (this.dataNombre.textProperty().get().length() <= 30) && (this.dataTelefono.textProperty().get().length() <= 10)
                        && (this.dataDireccion.textProperty().get().length() <= 50) && this.dataEmail.textProperty().get().length() <= 30 ) {
                    try {

                        PreparedStatement pps =this.conect.getCo().prepareStatement("INSERT INTO proveedor(ruc,nombre,direccion,telefono,email) VALUES(?,?,?,?,?)");
                        pps.setString(1, this.dataRuc.textProperty().get());
                        pps.setString(2, this.dataNombre.textProperty().get());
                        pps.setString(3, this.dataDireccion.textProperty().get());
                        pps.setString(4, this.dataTelefono.textProperty().get());
                        pps.setString(5, this.dataEmail.textProperty().get());

                        pps.executeUpdate();

                        System.out.println("Entro2");
                    } catch (SQLException ex) {
                        System.out.println(ex);

                    }
                    
                }
            }

        });
        
 

    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getSelectPlus() {
        return selectPlus;
    }

    public void setSelectPlus(Boolean selectPlus) {
        this.selectPlus = selectPlus;
    }

    public Boolean getSelectBack() {
        return selectBack;
    }

    public void setSelectBack(Boolean selectBack) {
        this.selectBack = selectBack;
    }

    public Button getSearch() {
        return search;
    }

    public void setSearch(Button search) {
        this.search = search;
    }

    public Button getIngresar() {
        return ingresar;
    }

    public void setIngresar(Button ingresar) {
        this.ingresar = ingresar;
    }

    
        
    public void setPerson(Person persona) {
        this.personTemp = persona;
    }

    

}