/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Constantes.Settings;
import Recursos.ConexionSQL;
import Recursos.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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
 * @author Flores
 */
public class Usuario {

    Boolean state = false;
    Boolean selectPlus = true;
    Boolean selectBack = true;

    StackPane rootPane;
    private TableView<Usuarios> table = new TableView<Usuarios>();
    Usuarios usuarioTemp = null;
    private ObservableList<Usuarios> data = FXCollections.observableArrayList();

    Button search;
    Button back;
    Button plus;

    ImageView fondo;
    
    String option = "";
    TextField tf_search;
    ConexionSQL conect;
    VBox info;
    VBox info1;
    VBox datosUsuario;

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
    Button modif;

    public Usuario(ConexionSQL conect) {
        this.conect = conect;
        this.rootPane = new StackPane();
        
        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo2.jpg"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 130);

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
        contenedor1.setPadding(new Insets(0, 10, 0, 0));
        contenedor1.setSpacing(110);
        contenedor1.setAlignment(Pos.CENTER_RIGHT);

        table.setEditable(false);

        TableColumn idUsuario = new TableColumn("idUsuario");
        idUsuario.impl_setReorderable(false);
        idUsuario.setMinWidth(100);
        idUsuario.setCellValueFactory(
                new PropertyValueFactory<Usuarios, String>("idUsuario"));

        TableColumn nombre = new TableColumn("nombre");
        nombre.impl_setReorderable(false);
        nombre.setMinWidth(100);
        nombre.setCellValueFactory(
                new PropertyValueFactory<Usuarios, String>("nombre"));

        TableColumn apellido = new TableColumn("apellido");
        apellido.impl_setReorderable(false);
        apellido.setMinWidth(180);
        apellido.setCellValueFactory(
                new PropertyValueFactory<Usuarios, String>("apellido"));

//        TableColumn direccion = new TableColumn("direccion");
//        nombre.impl_setReorderable(false);
//        direccion.setMinWidth(200);
//        direccion.setCellValueFactory(
//                new PropertyValueFactory<Usuarios, String>("direccion"));
//
//        TableColumn emailCol = new TableColumn("Email");
//        emailCol.impl_setReorderable(false);
//        emailCol.setMinWidth(200);
//        emailCol.setCellValueFactory(
//                new PropertyValueFactory<Usuarios, String>("email"));
//
//        TableColumn profesion = new TableColumn("profesion");
//        profesion.impl_setReorderable(false);
//        profesion.setMinWidth(200);
//        profesion.setCellValueFactory(
//                new PropertyValueFactory<Usuarios, String>("profesion"));
//
//        TableColumn cargo = new TableColumn("cargo");
//        cargo.impl_setReorderable(false);
//        cargo.setMinWidth(200);
//        cargo.setCellValueFactory(
//                new PropertyValueFactory<Usuarios, String>("cargo"));
//
//        TableColumn idConsultorio = new TableColumn("idConsultorio");
//        idConsultorio.impl_setReorderable(false);
//        idConsultorio.setMinWidth(200);
//        idConsultorio.setCellValueFactory(
//                new PropertyValueFactory<Usuarios, String>("idConsultorio"));
//
//        TableColumn idUsuario2 = new TableColumn("idUsuario2");
//        idUsuario2.impl_setReorderable(false);
//        idUsuario2.setMinWidth(200);
//        idUsuario2.setCellValueFactory(
//                new PropertyValueFactory<Usuarios, String>("idUsuario2"));
        // table.getColumns().addAll(idUsuario, nombre, apellido, direccion, emailCol, profesion, cargo, idConsultorio, idUsuario2);
        table.getColumns().addAll(idUsuario, nombre, apellido);

        final ScrollPane sp = new ScrollPane();
        sp.setVmax(3);
        sp.setPrefSize(400, 375);
        sp.setContent(table);

        final ScrollPane sp1 = new ScrollPane();
        sp1.setVmax(3);
        sp1.setPrefSize(400, 375);

        Label lb_idUsuario = new Label("idUsuario: ");
        Label lb_nombre = new Label("Nombre: ");
        Label lb_apellido = new Label("Apellido: ");
        Label lb_direccion = new Label("Dirección: ");
        Label lb_email = new Label("Email: ");
        Label lb_profesion = new Label("Profesión: ");
        Label lb_cargo = new Label("Cargo: ");
        Label lb_consultorio = new Label("Consultorio: ");
        Label lb_idUsuario2 = new Label("idJefe: ");

        lb_idUsuario.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_nombre.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_apellido.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_direccion.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_email.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_profesion.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_cargo.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_consultorio.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_idUsuario2.setFont(Font.font("Cambria", FontWeight.BOLD, 13));

        Label res_idUsuario = new Label();
        Label res_nombre = new Label();
        Label res_apellido = new Label();
        Label res_direccion = new Label();
        Label res_email = new Label();
        Label res_profesion = new Label();
        Label res_cargo = new Label();
        Label res_consultorio = new Label();
        Label res_idUsuario2 = new Label();

        HBox hBoxIdUsuario = new HBox();
        HBox hBoxNombre = new HBox();
        HBox hBoxApellido = new HBox();
        HBox hBoxDireccion = new HBox();
        HBox hBoxEmail = new HBox();
        HBox hBoxProfesion = new HBox();
        HBox hBoxCargo = new HBox();
        HBox hBoxConsultorio = new HBox();
        HBox hBoxIdUsuario2 = new HBox();

        hBoxIdUsuario.getChildren().addAll(lb_idUsuario, res_idUsuario);
        hBoxNombre.getChildren().addAll(lb_nombre, res_nombre);
        hBoxApellido.getChildren().addAll(lb_apellido, res_apellido);
        hBoxDireccion.getChildren().addAll(lb_direccion, res_direccion);
        hBoxEmail.getChildren().addAll(lb_email, res_email);
        hBoxProfesion.getChildren().addAll(lb_profesion, res_profesion);
        hBoxCargo.getChildren().addAll(lb_cargo, res_cargo);
        hBoxConsultorio.getChildren().addAll(lb_consultorio, res_consultorio);
        hBoxIdUsuario2.getChildren().addAll(lb_idUsuario2, res_idUsuario2);

        info1 = new VBox();
        info1.getChildren().addAll(new Separator(), hBoxIdUsuario, hBoxNombre, hBoxApellido, hBoxDireccion, hBoxEmail,
                hBoxProfesion, hBoxCargo, hBoxConsultorio, hBoxIdUsuario2, new Separator());
        info1.setSpacing(1);
        info1.setPadding(new Insets(10, 10, 10, 10));

        info = new VBox();

        info.getChildren().addAll(info1, sp1);
        info.setPadding(new Insets(5, 10, 5, 10));

        info1.setVisible(false);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuarios>() {

            @Override
            public void changed(ObservableValue<? extends Usuarios> observable, Usuarios oldValue, Usuarios newValue) {
                try{
                    usuarioTemp = newValue;

                    res_idUsuario.setText(usuarioTemp.getIdUsuario());
                    res_nombre.setText(usuarioTemp.getNombre());
                    res_apellido.setText(usuarioTemp.getApellido());
                    res_direccion.setText(usuarioTemp.getDireccion());
                    res_email.setText(usuarioTemp.getEmail());
                    res_profesion.setText(usuarioTemp.getProfesion());
                    res_cargo.setText(usuarioTemp.getCargo());
                    res_consultorio.setText(usuarioTemp.getIdConsultorio());
                    res_idUsuario2.setText(usuarioTemp.getIdUsuario2());
                    datosUsuario.setVisible(false);
                    info1.setVisible(true);
                }catch(NullPointerException e){
                    info1.setVisible(false);
                    datosUsuario.setVisible(false);
                }
            }
        });

        Label lb_idUsuario1 = new Label("idUsuario: ");
        Label lb_nombre1 = new Label("Nombre: ");
        Label lb_apellido1 = new Label("Apellido: ");
        Label lb_direccion1 = new Label("Dirección: ");
        Label lb_email1 = new Label("Email: ");
        Label lb_profesion1 = new Label("Profesión: ");
        Label lb_cargo1 = new Label("Cargo: ");
        Label lb_consultorio1 = new Label("Consultorio: ");
        Label lb_idUsuario21 = new Label("idJefe: ");

        lb_idUsuario1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_nombre1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_apellido1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_direccion1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_email1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_profesion1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_cargo1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_consultorio1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        lb_idUsuario21.setFont(Font.font("Cambria", FontWeight.BOLD, 13));

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

        HBox hBoxIdUsuario1 = new HBox();
        HBox hBoxNombre1 = new HBox();
        HBox hBoxApellido1 = new HBox();
        HBox hBoxDireccion1 = new HBox();
        HBox hBoxEmail1 = new HBox();
        HBox hBoxProfesion1 = new HBox();
        HBox hBoxCargo1 = new HBox();
        HBox hBoxConsultorio1 = new HBox();
        HBox hBoxIdUsuario21 = new HBox();

        hBoxIdUsuario1.getChildren().addAll(lb_idUsuario1, dataIdUsuario);
        hBoxNombre1.getChildren().addAll(lb_nombre1, dataNombre);
        hBoxApellido1.getChildren().addAll(lb_apellido1, dataApellido);
        hBoxDireccion1.getChildren().addAll(lb_direccion1, dataDireccion);
        hBoxEmail1.getChildren().addAll(lb_email1, dataEmail);
        hBoxProfesion1.getChildren().addAll(lb_profesion1, dataProfesion);
        hBoxCargo1.getChildren().addAll(lb_cargo1, dataCargo);
        hBoxConsultorio1.getChildren().addAll(lb_consultorio1, tf_consultorios);
        hBoxIdUsuario21.getChildren().addAll(lb_idUsuario21, dataIdUsuario2);

        /*hBoxIdUsuario1.setAlignment(Pos.CENTER);
        hBoxNombre1.setAlignment(Pos.CENTER);
        hBoxApellido1.setAlignment(Pos.CENTER);
        hBoxDireccion1.setAlignment(Pos.CENTER);
        hBoxEmail1.setAlignment(Pos.CENTER);
        hBoxProfesion1.setAlignment(Pos.CENTER);
        hBoxCargo1.setAlignment(Pos.CENTER);
        hBoxConsultorio1.setAlignment(Pos.CENTER);
        hBoxIdUsuario21.setAlignment(Pos.CENTER);*/

        clear = new Button("Clear");
        Image imageClear = new Image(getClass().getResourceAsStream("/Img/clear.png"));
        clear.setGraphic(new ImageView(imageClear));
        
        save = new Button("Save");
        Image imageSave = new Image(getClass().getResourceAsStream("/Img/save.png"));
        save.setGraphic(new ImageView(imageSave));
        
        cancel = new Button("Cancel");
        Image imageCancel = new Image(getClass().getResourceAsStream("/Img/cancel.gif"));
        cancel.setGraphic(new ImageView(imageCancel));

        HBox hBoxButtons = new HBox();
        hBoxButtons.getChildren().addAll(clear, save, cancel);
        hBoxButtons.setAlignment(Pos.CENTER);
        hBoxButtons.setPadding(new Insets(10, 10, 10, 10));
        hBoxButtons.setSpacing(40);

        datosUsuario = new VBox();
        datosUsuario.getChildren().addAll(hBoxIdUsuario1, hBoxNombre1, hBoxApellido1,
                hBoxDireccion1, hBoxEmail1, hBoxProfesion1, hBoxCargo1, hBoxConsultorio1, hBoxIdUsuario21, hBoxButtons);
        datosUsuario.setVisible(false);
        datosUsuario.setSpacing(1);
        datosUsuario.setPadding(new Insets(5, 10, 5, 10));
        sp1.setContent(datosUsuario);

        contenedor1.getChildren().addAll(plus, modif, back);
        VBox contenedorGeneral = new VBox();
        HBox contenedor2 = new HBox();
        contenedor2.getChildren().addAll(sp, info);
        contenedorGeneral.getChildren().addAll(contenedor1, contenedor2);
        rootPane.getChildren().addAll(fondo, contenedorGeneral);
        setButtons();
        //info.setVisible(false);
    }

    public StackPane getRootPane() {
        return rootPane;
    }

    public void setButtons() {
        this.cancel.setOnAction(e -> {
            clear();
            datosUsuario.setVisible(false);
        });
        this.clear.setOnAction(e -> {

            clear();

        });
        this.modif.setOnAction(e -> {
            if (this.usuarioTemp != null) {
                datosUsuario.setVisible(true);
                dataIdUsuario.setText(usuarioTemp.getIdUsuario());
                dataNombre.setText(usuarioTemp.getNombre());
                dataApellido.setText(usuarioTemp.getApellido());
                dataDireccion.setText(usuarioTemp.getDireccion());
                dataEmail.setText(usuarioTemp.getEmail());
                dataProfesion.setText(usuarioTemp.getProfesion());
                dataCargo.setText(usuarioTemp.getCargo());
                tf_consultorios.setValue(usuarioTemp.getIdConsultorio());
                dataIdUsuario2.setText(usuarioTemp.getIdUsuario2());
            }
        });
        this.search.setOnAction(e -> {
            table.getItems().clear();
            data.clear();

            try {
                ResultSet rs = this.conect.getSta().executeQuery("select * from usuario;");

                while (rs.next()) {
                    usuarioTemp = new Usuarios(rs.getString("idUsuario"), rs.getString("nombre"), rs.getString("apellido"),
                            rs.getString("direccion"), rs.getString("email"), rs.getString("profesion"),
                            rs.getString("cargo"), rs.getString("idConsultorio"), rs.getString("idUsuario2"));
                    this.data.add(usuarioTemp);
                    //System.out.println(rs.getString("nombre"));

                }
                if (data.isEmpty()) {
                } else {
                    table.setItems(data);
                }
            } catch (Exception error) {
            }

        });
        this.back.setOnAction(e -> {
            info1.setVisible(false);
            datosUsuario.setVisible(false);
            this.selectBack = false;
            
        });
        this.plus.setOnAction(e -> {
            clear();
            datosUsuario.setVisible(true);

        });
        this.save.setOnAction(e -> {
            save();
        });

    }

    public void clear() {
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

    }

    public void save() {
        if (!this.dataIdUsuario.textProperty().get().equals("") && !this.dataNombre.textProperty().get().equals("")
                && !this.dataApellido.textProperty().get().equals("") && !this.dataDireccion.textProperty().get().equals("")
                && !this.dataEmail.textProperty().get().equals("") && !this.dataProfesion.textProperty().get().equals("")
                && !this.dataCargo.textProperty().get().equals("")) {
            if ((this.dataIdUsuario.textProperty().get().length() <= 10) && (this.dataNombre.textProperty().get().length() <= 30)
                    && (this.dataApellido.textProperty().get().length() <= 30) && this.dataDireccion.textProperty().get().length() <= 30
                    && this.dataEmail.textProperty().get().length() <= 30 && this.dataProfesion.textProperty().get().length() <= 30
                    && this.dataCargo.textProperty().get().length() <= 30 && dataIdUsuario2.textProperty().get().length() <= 10) {
                try {

                    ResultSet rs = conect.getSta().executeQuery("select * from usuario where idUsuario="
                            + "'" + dataIdUsuario.textProperty().get() + "'" + ";");
                    int size = 0;
                    while (rs.next()) {

                        size++;

                    }
                    System.out.println(size);

                    if (size == 1) {
                        PreparedStatement pps = this.conect.getCo().prepareStatement("UPDATE usuario "
                                + "SET idUsuario = ? ,"
                                + "nombre = ? ,"
                                + "apellido = ? ,"
                                + "direcion = ? ,"
                                + "email = ? ,"
                                + "profesion = ? ,"
                                + "cargo = ? ,"
                                + "idConsultorio = ? ,"
                                + "idUsuario2 = ? "
                                + "where idUsuario = ? ");
                        pps.setString(1, this.dataIdUsuario.textProperty().get());
                        pps.setString(2, this.dataNombre.textProperty().get());
                        pps.setString(3, this.dataApellido.textProperty().get());
                        pps.setString(4, this.dataDireccion.textProperty().get());
                        pps.setString(5, this.dataEmail.textProperty().get());
                        pps.setString(6, this.dataProfesion.textProperty().get());
                        pps.setString(7, this.dataCargo.textProperty().get());
                        pps.setString(8, (String) tf_consultorios.getValue());
                        pps.setString(9, dataIdUsuario2.textProperty().get());
                        pps.setString(10, this.dataIdUsuario.textProperty().get());
                        pps.executeUpdate();
                    }
                    if (size == 0) {
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
                    }

                    System.out.println("Entro1");

                    System.out.println("Entro2");
                } catch (SQLException ex) {
                    System.out.println(ex);

                }

            }
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

    public void llenarTabla(){
        table.getItems().clear();
            data.clear();

            try {
                ResultSet rs = this.conect.getSta().executeQuery("select * from usuario;");

                while (rs.next()) {
                    usuarioTemp = new Usuarios(rs.getString("idUsuario"), rs.getString("nombre"), rs.getString("apellido"),
                            rs.getString("direccion"), rs.getString("email"), rs.getString("profesion"),
                            rs.getString("cargo"), rs.getString("idConsultorio"), rs.getString("idUsuario2"));
                    this.data.add(usuarioTemp);
                    //System.out.println(rs.getString("nombre"));

                }
                if (data.isEmpty()) {
                } else {
                    table.setItems(data);
                }
            } catch (Exception error) {
            }
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

}
