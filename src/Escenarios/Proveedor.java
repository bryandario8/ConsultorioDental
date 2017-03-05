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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    Boolean state = false;
    Boolean selectPlus = true;
    Boolean selectBack = true;
    
    StackPane rootPane;
    private TableView<Person> table = new TableView<Person>();
    Person personTemp = null;
    private ObservableList<Person> data = FXCollections.observableArrayList();
    Button search;
    Button back;
    Button plus;
    Button modif;
    Button clear;
    Button save;
    Button cancel;
    String option = "";
    TextField tf_search;
    ConexionSQL conect;
    VBox info;
    VBox info1;
    VBox info2;
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

        RadioButton rb1 = new RadioButton("RUC");
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
        Image imageSearch = new Image(getClass().getResourceAsStream("/Img/search.png"));
        search.setGraphic(new ImageView(imageSearch));
        
        plus = new Button("Add");
        Image imagePlus = new Image(getClass().getResourceAsStream("/Img/user.png"));
        plus.setGraphic(new ImageView(imagePlus));
        
        modif = new Button("Edit");
        Image imageEdit = new Image(getClass().getResourceAsStream("/Img/edit.png"));
        modif.setGraphic(new ImageView(imageEdit));
        
        back = new Button("Back");
        Image imageBack = new Image(getClass().getResourceAsStream("/Img/back.gif"));
        back.setGraphic(new ImageView(imageBack));
        
        clear = new Button("Clear");
        Image imageClear = new Image(getClass().getResourceAsStream("/Img/clear.png"));
        clear.setGraphic(new ImageView(imageClear));
        
        save = new Button("Save");
        Image imageSave = new Image(getClass().getResourceAsStream("/Img/save.png"));
        save.setGraphic(new ImageView(imageSave));
        
        cancel = new Button("Cancel");
        Image imageCancel = new Image(getClass().getResourceAsStream("/Img/cancel.gif"));
        cancel.setGraphic(new ImageView(imageCancel));
        
        
        
        

        table.setEditable(true);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        //ArrayList
        TableColumn telefonoCol = new TableColumn("Telefono");
        telefonoCol.setMinWidth(100);
        telefonoCol.setCellValueFactory(
                new PropertyValueFactory<Person, String >("telefonos"));   // incluir telefono como una Lista de String (en este caso sólo 1)

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));

        table.getColumns().addAll(nameCol, telefonoCol, emailCol);

        
        final ScrollPane sp = new ScrollPane();
        sp.setVmax(3);
        sp.setPrefSize(400, 375);
        sp.setContent(table);

        final ScrollPane sp1 = new ScrollPane();
        sp1.setVmax(3);
        sp1.setPrefSize(400, 375);

             
        Label label = new Label("DATOS");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
        ingresar = new Button ("Ingresar");
        Label ruc = new Label("RUC: ");
        ruc.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label nombre = new Label("Nombre: ");
        nombre.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label direccion = new Label("Dirección: ");
        direccion.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label telefono = new Label("Teléfono: ");
        telefono.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label email = new Label("Email: ");
        email.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        
        Label ruc1 = new Label("RUC: ");
        ruc1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label nombre1 = new Label("Nombre: ");
        nombre1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label direccion1 = new Label("Dirección: ");
        direccion1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label telefono1 = new Label("Teléfono: ");
        telefono1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label email1 = new Label("Email: ");
        email1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        
        
        Label res_ruc = new Label();
        Label res_nombre = new Label();
        Label res_direccion = new Label();
        Label res_telefono = new Label();
        Label res_email = new Label();
        
        dataRuc = new TextField();
        dataNombre = new TextField();
        dataDireccion = new TextField();
        dataTelefono = new TextField();
        dataEmail = new TextField();
       

        
        
        hBoxRuc.getChildren().addAll(ruc,res_ruc);
        hBoxNombre.getChildren().addAll(nombre,res_nombre);
        hBoxDireccion.getChildren().addAll(direccion,res_direccion);
        hBoxTelefono.getChildren().addAll(telefono,res_telefono);
        hBoxEmail.getChildren().addAll(email,res_email);
        
        HBox hboxRuc = new HBox();
        HBox hboxNombre = new HBox();
        HBox hboxDireccion = new HBox();
        HBox hboxTelefono = new HBox();
        HBox hboxEmail = new HBox();
        
        hboxRuc.getChildren().addAll(ruc1, dataRuc);
        hboxNombre.getChildren().addAll(nombre1, dataNombre);
        hboxDireccion.getChildren().addAll(direccion1, dataDireccion);
        hboxTelefono.getChildren().addAll(telefono1, dataTelefono);
        hboxEmail.getChildren().addAll(email1, dataEmail);

        HBox hboxOptions = new HBox();
        hboxOptions.getChildren().addAll(clear, save, cancel);
        hboxOptions.setAlignment(Pos.CENTER);
        hboxOptions.setSpacing(40);
        
        
        info1 = new VBox();
        info1.getChildren().addAll(new Separator(),hBoxRuc, hBoxNombre, hBoxDireccion, hBoxTelefono, hBoxEmail, new Separator());
        info1.setPadding(new Insets(10, 10, 10, 10));
        
        info2 = new VBox();
        info2.getChildren().addAll(hboxRuc, hboxNombre, hboxDireccion, hboxTelefono, hboxEmail, hboxOptions);
        info2.setSpacing(5);
        info2.setPadding(new Insets(15, 10, 10, 10));
        
        sp1.setContent(info2);
        info2.setVisible(false);
        
        info = new VBox();
        info.getChildren().addAll(info1, sp1);
        
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {

            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                //System.out.println(newValue.getFirstName());
                try{
                    personTemp = newValue;
                    res_ruc.setText(personTemp.getRuc());
                    res_nombre.setText(personTemp.getFirstName());
                    res_direccion.setText(personTemp.getDireccion());
                    res_telefono.setText(personTemp.getTelefonos());
                    res_email.setText(personTemp.getEmail());
                    
                    info1.setVisible(true);
                    info2.setVisible(false);
                }catch(NullPointerException e){
                    info1.setVisible(false);
                    info2.setVisible(false);
                }
                
                
                
                
            }
        });

        HBox contenedor1 = new HBox();
        contenedor1.getChildren().addAll(contenedorRadio, tf_search, search, plus, modif, back);
        contenedor1.setSpacing(57);
        
        VBox contenedorGeneral = new VBox();
        HBox contenedor2 = new HBox();
        contenedor2.getChildren().addAll(sp, info);
        contenedorGeneral.getChildren().addAll(contenedor1, contenedor2);
        rootPane.getChildren().addAll(fondo, contenedorGeneral);
        
        setButtons();
        info1.setVisible(false);

    }

    public StackPane getRootPane() {
        return rootPane;
    }

   
    public void setButtons() {
        this.cancel.setOnAction(e ->{
            info2.setVisible(false);
            clear();
        });
        this.clear.setOnAction(e ->{
            clear(); 
        });
        
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
                                personTemp = new Person(rs.getString("ruc"), rs.getString("nombre"), rs.getString("direccion"),  rs.getString("telefono"), rs.getString("email"), 1);
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
                                    + "'" + this.tf_search.textProperty().get() + "%'" + ";");
                            int size = 0;
                            while (rs.next()) {
                                personTemp = new Person(rs.getString("ruc"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("email"), 1);
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
        this.modif.setOnAction(e -> {
            if (this.personTemp != null) {
                info2.setVisible(true);

                dataRuc.setText(personTemp.getRuc());
                dataNombre.setText(personTemp.getFirstName());
                dataDireccion.setText(personTemp.getDireccion());
                dataTelefono.setText(personTemp.getTelefonos());
                dataEmail.setText(personTemp.getEmail());
                
            }
        });
        this.plus.setOnAction(e->{
            clear();
            info2.setVisible(true);
                    
            
        });
    
        save.setOnAction(e->{
            System.out.println("Entro1");
            if (!this.dataRuc.textProperty().get().equals("") && !this.dataNombre.textProperty().get().equals("")
                    && !this.dataDireccion.textProperty().get().equals("") && !this.dataTelefono.textProperty().get().equals("")
                    && !this.dataEmail.textProperty().get().equals("") ) {
                if ((this.dataRuc.textProperty().get().length() <= 13) && (this.dataNombre.textProperty().get().length() <= 40) && (this.dataTelefono.textProperty().get().length() <= 10)
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

    public void clear() {
        dataRuc.clear();
        dataNombre.clear();
        dataDireccion.clear();
        dataTelefono.clear();
        dataEmail.clear();
        
    }
        
    public void llenarTabla(){
        table.getItems().clear();
            data.clear();

            try {
                ResultSet rs = this.conect.getSta().executeQuery("select * from proveedor;");

                while (rs.next()) {
                    personTemp = new Person(rs.getString("ruc"), rs.getString("nombre"), 
                            rs.getString("direccion"), rs.getString("telefono"),
                            rs.getString("email"), 1);
                    this.data.add(personTemp);
                    //System.out.println(rs.getString("nombre"));

                }
                if (data.isEmpty()) {
                } else {
                    table.setItems(data);
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