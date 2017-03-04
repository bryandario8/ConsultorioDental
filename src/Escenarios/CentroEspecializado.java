/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Constantes.Settings;
import Recursos.ConexionSQL;
import Recursos.Person;
import Recursos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class CentroEspecializado {
StackPane rootPane;
 Boolean state=false;
    Boolean selectPlus=true;
    Boolean selectBack=true;
    
    private TableView<Person> table = new TableView<Person>();
    Person personTemp = null;
    private ObservableList<Person> data = FXCollections.observableArrayList();
    Button search;
    Button plus;
    Button back;
    Button modif;
    String option = "";
    TextField tf_search;
    ConexionSQL conect;
    VBox info;
   
    ImageView fondo;
    
    Button ingresar;
    
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

    Login login;

    public CentroEspecializado(ConexionSQL conect) {
        this.conect = conect;
        //this.personTemp=persona;
        this.rootPane = new StackPane();
        
        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo2.jpg"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 130);
       
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Código");
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
        
        HBox contenedor1 = new HBox();

        table.setEditable(true);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("nombre"));

        TableColumn direccionCol = new TableColumn("Direccion");
        direccionCol.setMinWidth(100);
        direccionCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("direccion"));   

        TableColumn telefonoCol = new TableColumn("Telefono");
        telefonoCol.setMinWidth(200);
        telefonoCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("telefono"));

        table.getColumns().addAll(nameCol, direccionCol, telefonoCol);

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
        
        
        Label label = new Label("DATOS");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
        ingresar=new Button ("Ingresar");
        Label ruc = new Label("Código: ");
        ruc.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
        Label nombre = new Label("Nombre: ");
        nombre.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
        Label direccion = new Label("Dirección: ");
        direccion.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
        Label email = new Label("Email: ");
        email.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
        Label telefono = new Label("Teléfono: ");
        telefono.setFont(Font.font("Cambria", FontWeight.BOLD, 15));

        dataRuc = new TextField();
        dataNombre = new TextField();
        dataDireccion = new TextField();
        dataTelefono = new TextField();
        dataEmail = new TextField();
        
        
        
        hBoxRuc.getChildren().addAll(ruc, dataRuc);
        hBoxNombre.getChildren().addAll(nombre, dataNombre);
        hBoxDireccion.getChildren().addAll(direccion, dataDireccion);
        hBoxTelefono.getChildren().addAll(telefono, dataTelefono);
        hBoxEmail.getChildren().addAll(email, dataEmail);

        VBox info1 = new VBox();
        info1.getChildren().addAll(new Separator(),hBoxRuc, hBoxNombre, hBoxDireccion, hBoxTelefono, hBoxEmail, new Separator(),ingresar);

        info = new VBox();
        info.getChildren().addAll(info1);
        contenedor1.getChildren().addAll(contenedorRadio, tf_search, search, plus, modif, back);
        contenedor1.setSpacing(50);
        
        VBox contenedorGeneral = new VBox();
        HBox contenedor2 = new HBox();
        contenedor2.getChildren().addAll(sp, info);
        contenedorGeneral.getChildren().addAll(contenedor1, contenedor2);
        rootPane.getChildren().addAll(fondo, contenedorGeneral);
        setButtons();
        info.setVisible(false);
    }
    
    public CentroEspecializado(ConexionSQL conect, Login login){
        this(conect);
        this.login = login;
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
                            ResultSet rs = this.conect.getSta().executeQuery("select * from centro_especializado where ruc="
                                    + "'" + this.tf_search.textProperty().get() + "'" + ";");

                            while (rs.next()) {
                                personTemp = new Person(rs.getString("nombre"),  rs.getString("direccion"), rs.getString("telefono"));
                                this.data.add(personTemp);
                                System.out.println(rs.getString("nombre"));

                            }
                            if (data.isEmpty()) {
                                this.selectPlus = false;    //*****
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
                            ResultSet rs = this.conect.getSta().executeQuery("select * from centro_especializado where nombre like "
                                    + "'%" + this.tf_search.textProperty().get() + "%'" + ";");
                            int size = 0;
                            while (rs.next()) {
                                personTemp = new Person(rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"));
                                this.data.add(personTemp);
                                System.out.println(rs.getString("nombre"));
                                size++;

                            }
                            if (data.isEmpty()) {
                                this.selectPlus = false;      //***
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
            if (!this.dataRuc.textProperty().get().equals("") && !this.dataNombre.textProperty().get().equals("")
                    && !this.dataDireccion.textProperty().get().equals("") && !this.dataTelefono.textProperty().get().equals("")
                    && !this.dataEmail.textProperty().get().equals("") ) {
                if ((this.dataRuc.textProperty().get().length() <= 10) && (this.dataNombre.textProperty().get().length() <= 30) && (this.dataTelefono.textProperty().get().length() <= 10)
                        && (this.dataDireccion.textProperty().get().length() <= 50) && this.dataEmail.textProperty().get().length() <= 30 ) {
                    try {

                        PreparedStatement pps =this.conect.getCo().prepareStatement("INSERT INTO centro_especializado(ruc,nombre,direcion,telefono,email,idUsuario) VALUES(?,?,?,?,?,?)");
                        pps.setString(1, this.dataRuc.textProperty().get());
                        pps.setString(2, this.dataNombre.textProperty().get());
                        pps.setString(3, this.dataDireccion.textProperty().get());
                        pps.setString(4, this.dataTelefono.textProperty().get());
                        pps.setString(5, this.dataEmail.textProperty().get());
                        pps.setString(6, getUsuario().getIdUsuario());


                        pps.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(CrearPaciente.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
            }

        });
        
    }

    public Usuario getUsuario(){
        return login.getUsuarioTemp();
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

    public Button getPlus() {
        return plus;
    }

    public void setPlus(Button plus) {
        this.plus = plus;
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

    public void llenarHistorial(Person person) {

    }

}
