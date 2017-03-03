/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Recursos.DataPaciente;
import Recursos.ConexionSQL;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Flores
 */
public class CrearPaciente {

    StackPane rootPane;
    private TableView<DataPaciente> table = new TableView<DataPaciente>();

    private ObservableList<DataPaciente> data = FXCollections.observableArrayList();

    DataPaciente pacienteTemp = null;
    DataPaciente pacienteOld = null;

    TextField tf_search;
    Button search;
    Button plus;
    Button modif;
    Button back;

    Button clear;
    Button ingresar;
    Button cancel;

    String option = "";

    ConexionSQL conect;

    VBox info;
    VBox info1;
    VBox trata;
    VBox vBoxRecetas;

    DatePicker dataFechaNacimiento;

    TextField dataCedula;
    TextField dataNombre;
    TextField dataApellido;
    TextField dataDireccion;
    TextField dataEstadoCivil;
    TextField dataEmail;
    ComboBox cB_idUsuario;
    ComboBox cB_idConsultorio;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Boolean state=false;
    Boolean selectBack=true;
    public CrearPaciente(ConexionSQL conect) {
        this.conect = conect;
        //this.personTemp=persona;
        this.rootPane = new StackPane();

        tf_search = new TextField();
        search = new Button("Buscar");
        plus = new Button("+");
        modif = new Button("Modificar");
        back = new Button("Volver");
        clear = new Button("Limpiar");
        ingresar = new Button("Guardar");
        cancel = new Button("Cancelar");

        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Cedula");
        rb1.setToggleGroup(group);
        rb1.setUserData("A");

        RadioButton rb2 = new RadioButton("Nombre");
        rb2.setToggleGroup(group);
        rb2.setUserData("B");

        RadioButton rb3 = new RadioButton("Apellido");
        rb3.setToggleGroup(group);
        rb3.setUserData("C");

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
        contenedorRadio.getChildren().add(rb3);
        contenedorRadio.setSpacing(10);

        Label label = new Label("Paciente");
        label.setFont(new Font("Arial", 20));

        table.setEditable(false);

        TableColumn cedula = new TableColumn("cedula");
        cedula.impl_setReorderable(false);
        cedula.setMinWidth(100);
        cedula.setCellValueFactory(
                new PropertyValueFactory<DataPaciente, String>("cedula"));

        TableColumn nombre = new TableColumn("nombre");
        nombre.impl_setReorderable(false);
        nombre.setMinWidth(100);
        nombre.setCellValueFactory(
                new PropertyValueFactory<DataPaciente, String>("nombre"));

        TableColumn apellido = new TableColumn("apellido");
        apellido.impl_setReorderable(false);
        apellido.setMinWidth(180);
        apellido.setCellValueFactory(
                new PropertyValueFactory<DataPaciente, String>("apellido"));

        table.getColumns().addAll(cedula, nombre, apellido);

        final ScrollPane sp = new ScrollPane();
        sp.setVmax(3);
        sp.setPrefSize(400, 375);
        sp.setContent(table);

        final ScrollPane sp1 = new ScrollPane();
        sp1.setVmax(3);
        sp1.setPrefSize(400, 375);

        Label cedula2 = new Label("Cedula : ");
        Label nombre2 = new Label("Nombre : ");
        Label apellido2 = new Label("Apellido : ");
        Label direccion2 = new Label("Direccion : ");
        Label fechaNacimiento2 = new Label("Fecha de nacimiento : ");
        Label estadoCivil2 = new Label("Estado Civil : ");
        Label email2 = new Label("Email : ");
        Label idUsuario2 = new Label("IdUsuario : ");
        Label idConsultorio2 = new Label("IdConsultorio : ");

        cedula2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        nombre2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        apellido2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        direccion2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        fechaNacimiento2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        estadoCivil2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        email2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        idUsuario2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        idConsultorio2.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label res_cedula1 = new Label();
        Label res_nombre1 = new Label();
        Label res_apellido1 = new Label();
        Label res_direccion1 = new Label();
        Label res_fechaNacimiento1 = new Label();
        Label res_estadoCivil1 = new Label();
        Label res_email1 = new Label();
        Label res_idUsuario1 = new Label();
        Label res_idConsultorio1 = new Label();

        HBox hBoxCedula1 = new HBox();
        HBox hBoxNombre1 = new HBox();
        HBox hBoxApellido1 = new HBox();
        HBox hBoxDireccion1 = new HBox();
        HBox hBoxFechaNacimiento1 = new HBox();
        HBox hBoxEstadoCivil1 = new HBox();
        HBox hBoxEmail1 = new HBox();
        HBox hBoxIdUsuario1 = new HBox();
        HBox hBoxIdConsultorio1 = new HBox();

        hBoxCedula1.getChildren().addAll(cedula2, res_cedula1);
        hBoxNombre1.getChildren().addAll(nombre2, res_nombre1);
        hBoxApellido1.getChildren().addAll(apellido2, res_apellido1);
        hBoxDireccion1.getChildren().addAll(direccion2, res_direccion1);
        hBoxFechaNacimiento1.getChildren().addAll(fechaNacimiento2, res_fechaNacimiento1);
        hBoxEstadoCivil1.getChildren().addAll(estadoCivil2, res_estadoCivil1);
        hBoxEmail1.getChildren().addAll(email2, res_email1);
        hBoxIdUsuario1.getChildren().addAll(idUsuario2, res_idUsuario1);
        hBoxIdConsultorio1.getChildren().addAll(idConsultorio2, res_idConsultorio1);

        VBox info2 = new VBox();
        info2.getChildren().addAll(new Separator(), hBoxCedula1, hBoxNombre1, hBoxApellido1, hBoxDireccion1,
                hBoxFechaNacimiento1, hBoxEstadoCivil1, hBoxEmail1, hBoxIdUsuario1, hBoxIdConsultorio1, new Separator());

        info = new VBox();
        info.getChildren().addAll(info2, sp1);

        info2.setVisible(false);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DataPaciente>() {

            @Override
            public void changed(ObservableValue<? extends DataPaciente> observable, DataPaciente oldValue, DataPaciente newValue) {

                pacienteTemp = newValue;
                pacienteOld = oldValue;
                if (pacienteTemp != null) {
                    res_cedula1.setText(pacienteTemp.getCedula());
                    res_nombre1.setText(pacienteTemp.getNombre());
                    res_apellido1.setText(pacienteTemp.getApellido());
                    res_direccion1.setText(pacienteTemp.getDirecion());
                    res_fechaNacimiento1.setText(pacienteTemp.getFechaNacimiento().toString());
                    res_estadoCivil1.setText(pacienteTemp.getEstadoCivil());
                    res_email1.setText(pacienteTemp.getEmail());
                    res_idUsuario1.setText(pacienteTemp.getIdUsuario());
                    res_idConsultorio1.setText(pacienteTemp.getIdConsultorio());
                }


                info1.setVisible(false);
                info2.setVisible(true);
            }
        });

        Label cedula1 = new Label("Cedula : ");
        Label nombre1 = new Label("Nombre : ");
        Label apellido1 = new Label("Apellido : ");
        Label direccion1 = new Label("Direccion : ");
        Label fechaNacimiento1 = new Label("Fecha de nacimiento : ");
        Label estadoCivil1 = new Label("Estado Civil : ");
        Label email1 = new Label("Email : ");
        Label idUsuario1 = new Label("IdUsuario : ");
        Label idConsultorio1 = new Label("IdConsultorio : ");

        cedula1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        nombre1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        apellido1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        direccion1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        fechaNacimiento1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        estadoCivil1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        email1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        idUsuario1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        idConsultorio1.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        dataCedula = new TextField();
        dataNombre = new TextField();
        dataApellido = new TextField();
        dataDireccion = new TextField();
        dataEstadoCivil = new TextField();
        dataEmail = new TextField();

        dataFechaNacimiento = new DatePicker();

        cB_idUsuario = new ComboBox();
        llenarComboBoxIdUsuario();
        cB_idUsuario.setPromptText("Usuarios Disponibles");
        cB_idUsuario.setEditable(true);

        cB_idConsultorio = new ComboBox();
        llenarComboBoxConsultorio();
        cB_idConsultorio.setPromptText("Consultorios Disponibles");
        cB_idConsultorio.setEditable(true);

        HBox hBoxCedula = new HBox();
        HBox hBoxNombre = new HBox();
        HBox hBoxApellido = new HBox();
        HBox hBoxDireccion = new HBox();
        HBox hBoxFechaNacimiento = new HBox();
        HBox hBoxEstadoCivil = new HBox();
        HBox hBoxEmail = new HBox();
        HBox hBoxIdUsuario = new HBox();
        HBox hBoxIdConsultorio = new HBox();

        hBoxCedula.getChildren().addAll(cedula1, dataCedula);
        hBoxNombre.getChildren().addAll(nombre1, dataNombre);
        hBoxApellido.getChildren().addAll(apellido1, dataApellido);
        hBoxDireccion.getChildren().addAll(direccion1, dataDireccion);
        hBoxFechaNacimiento.getChildren().addAll(fechaNacimiento1, dataFechaNacimiento);
        hBoxEstadoCivil.getChildren().addAll(estadoCivil1, dataEstadoCivil);
        hBoxEmail.getChildren().addAll(email1, dataEmail);
        hBoxIdUsuario.getChildren().addAll(idUsuario1, cB_idUsuario);
        hBoxIdConsultorio.getChildren().addAll(idConsultorio1, cB_idConsultorio);

        HBox hBoxButtons = new HBox();
        hBoxButtons.getChildren().addAll(clear, ingresar, cancel);
        hBoxButtons.setAlignment(Pos.CENTER);

        info1 = new VBox();
        info1.getChildren().addAll(new Separator(), hBoxCedula, hBoxNombre, hBoxApellido, hBoxDireccion, hBoxFechaNacimiento, hBoxEstadoCivil, hBoxEmail, hBoxIdUsuario, hBoxIdConsultorio, new Separator(), hBoxButtons);

        sp1.setContent(info1);
        info1.setVisible(false);

        HBox contenedor1 = new HBox();
        contenedor1.getChildren().addAll(contenedorRadio, tf_search, search, back, plus, modif);

        HBox contenedor2 = new HBox();
        contenedor2.getChildren().addAll(sp, info);

        VBox contenedorGeneral = new VBox();
        contenedorGeneral.getChildren().addAll(contenedor1, contenedor2);

        Tab tab1 = new Tab();
        tab1.setGraphic(label);
        tab1.setClosable(false);
        tab1.setContent(contenedorGeneral);

        Label label1 = new Label("Historial clinico");
        label1.setFont(new Font("Arial", 20));

        Tab tab2 = new Tab();
        tab2.setGraphic(label1);
        tab2.setClosable(false);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab1, tab2);

        rootPane.getChildren().addAll(tabPane);
        setButtons();

    }

    public StackPane getRootPane() {
        return rootPane;
    }

    public void setButtons() {
        this.cancel.setOnAction(e -> {
            clear();
            info1.setVisible(false);
        });
        this.plus.setOnAction(e -> {
            clear();
            info1.setVisible(true);
        });
        this.clear.setOnAction(e -> {

            clear();

        });

        this.modif.setOnAction(e -> {
            if (this.pacienteTemp != null) {
                info1.setVisible(true);

                dataCedula.setText(pacienteTemp.getCedula());
                dataNombre.setText(pacienteTemp.getNombre());
                dataApellido.setText(pacienteTemp.getApellido());
                dataDireccion.setText(pacienteTemp.getDirecion());
                dataFechaNacimiento.setValue(pacienteTemp.getFechaNacimiento().toLocalDate());
                dataEstadoCivil.setText(pacienteTemp.getEstadoCivil());
                dataEmail.setText(pacienteTemp.getEmail());
                cB_idUsuario.setValue(pacienteTemp.getIdUsuario());
                cB_idConsultorio.setValue(pacienteTemp.getIdConsultorio());

            }
        });

        this.search.setOnAction(e -> {
            table.getItems().clear();
            data.clear();
            pacienteTemp = null;
            switch (this.option) {
                case "":
                    System.out.println("ingrese algo");
                    break;
                case "A":
                    System.out.println("opcion A");
                    if (this.tf_search.textProperty().get().equals("")) {

                    } else {
                        try {
                            ResultSet rs = this.conect.getSta().executeQuery("select * from paciente where cedula="
                                    + "'" + this.tf_search.textProperty().get() + "'" + ";");

                            while (rs.next()) {
                                pacienteTemp = new DataPaciente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"),
                                        rs.getDate("fechaNacimiento"), rs.getString("estadoCivil"), rs.getString("email"), rs.getString("idUsuario"), rs.getString("idConsultorio"));
                                this.data.add(pacienteTemp);
                                System.out.println(rs.getString("nombre"));

                            }
                            if (data.isEmpty()) {
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
                            ResultSet rs = this.conect.getSta().executeQuery("select * from paciente where nombre like "
                                    + "'%" + this.tf_search.textProperty().get() + "%'" + ";");
                            int size = 0;
                            while (rs.next()) {
                                pacienteTemp = new DataPaciente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"),
                                        rs.getDate("fechaNacimiento"), rs.getString("estadoCivil"), rs.getString("email"), rs.getString("idUsuario"), rs.getString("idConsultorio"));
                                this.data.add(pacienteTemp);
                                System.out.println(rs.getString("nombre"));
                                size++;

                            }
                            if (data.isEmpty()) {

                            } else {

                                System.out.println("OK");
                                table.setItems(data);

                            }
                        } catch (Exception error) {
                        }
                    }
                    break;
                case "C":
                    System.out.println("opcion C");
                    if (this.tf_search.textProperty().get().equals("")) {
                    } else {
                        try {
                            ResultSet rs = this.conect.getSta().executeQuery("select * from paciente where apellido like "
                                    + "'%" + this.tf_search.textProperty().get() + "%'" + ";");

                            while (rs.next()) {
                                pacienteTemp = new DataPaciente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"),
                                        rs.getDate("fechaNacimiento"), rs.getString("estadoCivil"), rs.getString("email"), rs.getString("idUsuario"), rs.getString("idConsultorio"));
                                this.data.add(pacienteTemp);
                                System.out.println(rs.getString("nombre"));

                            }
                            if (data.isEmpty()) {
                            } else {
                                table.setItems(data);
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
        this.back.setOnAction(e -> {
               this.selectBack=false;
        });

        ingresar.setOnAction(e -> {

            save();

        });


    }

    public void save() {
        if (!this.dataCedula.textProperty().get().equals("") && !this.dataNombre.textProperty().get().equals("")
                && !this.dataApellido.textProperty().get().equals("") && !this.dataDireccion.textProperty().get().equals("")
                && !this.dataEmail.textProperty().get().equals("") && dataFechaNacimiento.getValue() != null
                && !this.dataEstadoCivil.textProperty().get().equals("") && this.cB_idUsuario.getValue() != null && this.cB_idConsultorio.getValue() != null) {
            if ((this.dataCedula.textProperty().get().length() <= 10) && (this.dataNombre.textProperty().get().length() <= 30)
                    && (this.dataApellido.textProperty().get().length() <= 30) && this.dataDireccion.textProperty().get().length() <= 30
                    && this.dataEmail.textProperty().get().length() <= 30
                    && this.dataEstadoCivil.textProperty().get().length() <= 30) {
                try {

                    ResultSet rs = conect.getSta().executeQuery("select * from paciente where cedula="
                            + "'" + dataCedula.textProperty().get() + "'" + ";");
                    int size = 0;
                    while (rs.next()) {

                        size++;

                    }

                    if (size == 1) {

                        PreparedStatement pps = this.conect.getCo().prepareStatement("UPDATE paciente "
                                + "SET cedula = ? ,"
                                + "nombre = ? ,"
                                + "apellido = ? ,"
                                + "direcion = ? ,"
                                + "fechaNacimiento = ? ,"
                                + "estadoCivil = ? ,"
                                + "email = ? ,"
                                + "idUsuario = ? ,"
                                + "idConsultorio = ? "
                                + "where cedula = ? ");


                        java.sql.Date dateDB = java.sql.Date.valueOf(dataFechaNacimiento.getValue());

                        // pps = this.conect.getCo().prepareStatement("INSERT INTO paciente(cedula,nombre,apellido,direcion,fechaNacimiento,estadoCivil,email,idUsuario,idConsultorio) VALUES(?,?,?,?,?,?,?,?,?)");
                        pps.setString(1, this.dataCedula.textProperty().get());
                        pps.setString(2, this.dataNombre.textProperty().get());
                        pps.setString(3, this.dataApellido.textProperty().get());
                        pps.setString(4, this.dataDireccion.textProperty().get());
                        pps.setDate(5, dateDB);
                        pps.setString(6, this.dataEstadoCivil.textProperty().get());
                        pps.setString(7, this.dataEmail.textProperty().get());
                        pps.setString(8, (String) this.cB_idUsuario.getValue());
                        pps.setString(9, (String) this.cB_idConsultorio.getValue());
                        pps.setString(10, this.dataCedula.textProperty().get());

                        pps.executeUpdate();
                    }
                    if (size == 0) {
                        java.sql.Date dateDB = java.sql.Date.valueOf(dataFechaNacimiento.getValue());

                        PreparedStatement pps = this.conect.getCo().prepareStatement("INSERT INTO paciente(cedula,nombre,apellido,direcion,fechaNacimiento,estadoCivil,email,idUsuario,idConsultorio) VALUES(?,?,?,?,?,?,?,?,?)");
                        pps.setString(1, this.dataCedula.textProperty().get());
                        pps.setString(2, this.dataNombre.textProperty().get());
                        pps.setString(3, this.dataApellido.textProperty().get());
                        pps.setString(4, this.dataDireccion.textProperty().get());
                        pps.setDate(5, dateDB);
                        pps.setString(6, this.dataEstadoCivil.textProperty().get());
                        pps.setString(7, this.dataEmail.textProperty().get());
                        pps.setString(8, (String) this.cB_idUsuario.getValue());
                        pps.setString(9, (String) this.cB_idConsultorio.getValue());

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

    public void llenarTabla(){
        table.getItems().clear();
            data.clear();

            try {
                ResultSet rs = this.conect.getSta().executeQuery("select * from paciente;");

                while (rs.next()) {
                    pacienteTemp = new DataPaciente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
                            rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("estadoCivil"),
                            rs.getString("email"), rs.getString("idUsuario"), rs.getString("idConsultorio"));
                    this.data.add(pacienteTemp);
                    //System.out.println(rs.getString("nombre"));

                }
                if (data.isEmpty()) {
                } else {
                    table.setItems(data);
                }
            } catch (Exception error) {
            }
    }
    
    public void llenarComboBoxIdUsuario() {
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select * from usuario;");
            while (rs.next()) {
                if (rs.getString("cargo").equals("Profesional")) {
                    this.cB_idUsuario.getItems().add(rs.getString("idUsuario"));
                    System.out.println(rs.toString());
                }
            }
        } catch (Exception error) {
        }

    }

    public void llenarComboBoxConsultorio() {
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select * from consultorio;");
            while (rs.next()) {
                this.cB_idConsultorio.getItems().add(rs.getString("idConsultorio"));
                System.out.println(rs.toString());
            }
        } catch (Exception error) {
        }

    }

    public void clear() {
        dataCedula.clear();
        dataNombre.clear();
        dataApellido.clear();
        dataDireccion.clear();
        dataFechaNacimiento.setValue(null);
        dataEstadoCivil.clear();
        dataEmail.clear();
        cB_idUsuario.getItems().clear();
        llenarComboBoxIdUsuario();
        cB_idConsultorio.getItems().clear();
        llenarComboBoxConsultorio();
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
