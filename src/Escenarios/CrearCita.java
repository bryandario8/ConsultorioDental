/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Recursos.CitaData;
import Recursos.ConexionSQL;
import Recursos.DataPaciente;
import Recursos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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

/**
 *
 * @author Flores
 */
public class CrearCita {

    Boolean state = false;
    Boolean selectBack = true;
    StackPane rootPane;
    private TableView<DataPaciente> table = new TableView<DataPaciente>();
    private TableView<CitaData> tableCitas = new TableView<CitaData>();
    DataPaciente pacienteTemp = null;
    CitaData citaTemp = null;
    private ObservableList<DataPaciente> data = FXCollections.observableArrayList();
    private ObservableList<CitaData> dataCitas = FXCollections.observableArrayList();
    Button search;
    String option = "";
    TextField tf_search;
    ConexionSQL conect;
    VBox info;
    VBox trata;
    DatePicker tf_fecha;
    ComboBox tf_hora;
    LocalDate fechaElegida;
    Button clean;

    Label dataFechaCita;
    Label dataHoraCita;
    Label dataPacienteCita;

    Button check;
    Button back;

    TextArea detalle;

    private Login login;

    public CrearCita(ConexionSQL conect) {
        this.conect = conect;
        this.rootPane = new StackPane();
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

        tf_search = new TextField("");

        search = new Button("Search");
        back = new Button("Back");

        HBox contenedor1 = new HBox();

        table.setEditable(true);

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

        final Label label = new Label("Pacientes");
        label.setFont(new Font("Arial", 20));

        final ScrollPane sp = new ScrollPane();
        sp.setVmax(3);
        sp.setPrefSize(400, 375);
        sp.setContent(table);

        final ScrollPane sp2 = new ScrollPane();
        sp2.setVmax(3);
        sp2.setPrefSize(400, 375);
        sp2.setContent(table);

        Label fecha = new Label("Fecha(dd/mm/yyyy): ");
        tf_fecha = new DatePicker();

        Label hora = new Label("Hora(hh:mm): ");
        tf_hora = new ComboBox();
        llenarComboBoxHora();
        tf_hora.setPromptText("Horas Disponibles");
        tf_hora.setEditable(true);
        HBox temp1 = new HBox();
        temp1.getChildren().addAll(fecha, tf_fecha);
        temp1.setAlignment(Pos.CENTER);
        HBox temp2 = new HBox();
        temp2.getChildren().addAll(hora, tf_hora);
        temp2.setAlignment(Pos.CENTER);

        final Label label1 = new Label("Fecha de cita");
        label1.setFont(new Font("Arial", 20));

        VBox info1 = new VBox();
        info1.getChildren().addAll(label1, temp1, temp2);
        info1.setSpacing(10);
        info1.setAlignment(Pos.CENTER);

        Label fechaCita = new Label("Dia : ");
        Label horaCita = new Label("Hora : ");
        Label pacienteCita = new Label("Paciente : ");
        detalle = new TextArea();
        detalle.setMinSize(390, 100);
        detalle.setMaxSize(390, 100);

        dataFechaCita = new Label();
        dataHoraCita = new Label();
        dataPacienteCita = new Label();

        HBox hBoxFechaCita = new HBox();
        HBox hBoxHoraCita = new HBox();
        HBox hBoxpacienteCita = new HBox();
        HBox hBoxDetalle = new HBox();

        hBoxFechaCita.getChildren().addAll(fechaCita, dataFechaCita);
        hBoxHoraCita.getChildren().addAll(horaCita, dataHoraCita);
        hBoxpacienteCita.getChildren().addAll(pacienteCita, dataPacienteCita);
        hBoxDetalle.getChildren().addAll(detalle);

        Label label2 = new Label("Resumen de cita");
        label2.setFont(new Font("Arial", 20));
        Label label3 = new Label("Detalle de cita");
        label3.setFont(new Font("Arial", 20));

        VBox resumen = new VBox();
        resumen.getChildren().addAll(label2, hBoxFechaCita, hBoxHoraCita, hBoxpacienteCita, label3, hBoxDetalle);
        resumen.setAlignment(Pos.CENTER);

        final ScrollPane sp1 = new ScrollPane();
        sp1.setVmax(3);
        sp1.setPrefSize(400, 375);

        clean = new Button("Limpiar campos");
        check = new Button("Connfirmar cita");

        HBox botonesResumen = new HBox();
        botonesResumen.getChildren().addAll(clean, check);
        botonesResumen.setSpacing(20);
        botonesResumen.setAlignment(Pos.CENTER);

        info = new VBox();
        info.getChildren().addAll(info1, resumen, botonesResumen);
        info.setSpacing(10);
        info.setMinSize(390, 300);
        info.setMaxSize(390, 370);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DataPaciente>() {

            @Override
            public void changed(ObservableValue<? extends DataPaciente> observable, DataPaciente oldValue, DataPaciente newValue) {
                //System.out.println(newValue.getFirstName());
                pacienteTemp = newValue;
                if (pacienteTemp != null) {

                    dataPacienteCita.setText(pacienteTemp.getCedula() + " " + pacienteTemp.getNombre() + " " + pacienteTemp.getApellido());

                }
            }
        });

        contenedor1.getChildren().addAll(contenedorRadio, tf_search, search, back);
        VBox contenedorGeneral = new VBox();
        HBox contenedor2 = new HBox();
        VBox contenedorTablas = new VBox();
        contenedorTablas.getChildren().addAll(label, sp);
        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);
        contenedor2.getChildren().addAll(contenedorTablas, separator1, info);
        contenedorGeneral.getChildren().addAll(contenedor1, new Separator(), contenedor2);
        rootPane.getChildren().addAll(contenedorGeneral);
        setButtons();
    }

    private void llenarComboBoxHora() {
        tf_hora.getItems().addAll("09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45",
                "12:00", "12:15", "12:30", "12:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45",
                "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00", "19:15");
    }

    public StackPane getRootPane() {
        return rootPane;
    }

    public void ponerLogin(Login login) {

        this.login = login;
    }
       public Usuario getUsuario(){
        return login.getUsuarioTemp();
    }

    public void setButtons() {
        this.back.setOnAction(e->{
            this.selectBack = false;
        });
        this.check.setOnAction(e -> {

            if (pacienteTemp != null && !this.tf_hora.getValue().equals(null) && tf_fecha.getValue() != null && !detalle.getText().equals("")) {
                
                    try {

                        ResultSet rs = conect.getSta().executeQuery("select * from cita;");
                        int size = 0;
                        while (rs.next()) {
                            java.sql.Date dateDB = java.sql.Date.valueOf(tf_fecha.getValue());
                            String hora=(String)tf_hora.getValue();
                            String cargado[]=hora.split(":");
                            LocalTime localTime=LocalTime.of(Integer.parseInt(cargado[0]),Integer.parseInt(cargado[1]));
                            if(rs.getDate("fecha").equals(dateDB)&&rs.getTime("hora").equals(localTime)){
                                size++;
                            }
                            

                        }
                        
                        if (size == 0) {
                            PreparedStatement pps = this.conect.getCo().prepareStatement("INSERT INTO cita(idCita, hora, fecha, detalle, idUsuario, idPaciente) VALUES(?,?,?,?,?,?)");
                            pps.setString(1, ""+cantidadCompras());
                            String hora=(String)tf_hora.getValue();
                            String cargado[]=hora.split(":");
                            java.sql.Time localTime=java.sql.Time.valueOf(LocalTime.of(Integer.parseInt(cargado[0]),Integer.parseInt(cargado[1])));
                            pps.setTime(2, localTime);
                            java.sql.Date dateDB = java.sql.Date.valueOf(tf_fecha.getValue());
                            pps.setDate(3, dateDB);
                            pps.setString(4, detalle.getText());
                            pps.setString(5, getUsuario().getIdUsuario());
                            pps.setString(6, pacienteTemp.getCedula());    
                            pps.executeUpdate();
                        }

                        System.out.println("Entro1");

                        System.out.println("Entro2");
                    } catch (SQLException ex) {
                        System.out.println(ex);

                    }

                }
            

        });
        tf_fecha.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("1");
                fechaElegida = tf_fecha.getValue();
                dataFechaCita.setText(fechaElegida.toString());
            }

        });
        clean.setOnAction(e -> {

        });
        tf_fecha.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                fechaElegida = tf_fecha.getValue();
            }

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
                            ResultSet rs = this.conect.getSta().executeQuery("select * from paciente where cedula="
                                    + "'" + this.tf_search.textProperty().get() + "'" + ";");
                            while (rs.next()) {
                                pacienteTemp = new DataPaciente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
                                        rs.getString("direcion"), rs.getDate("fechaNacimiento"), rs.getString("estadoCivil"), rs.getString("email"), rs.getString("idUsuario"), rs.getString("idConsultorio"));
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
                            ResultSet rs = this.conect.getSta().executeQuery("select * from paciente where nombre="
                                    + "'" + this.tf_search.textProperty().get() + "'" + ";");
                            while (rs.next()) {
                                pacienteTemp = new DataPaciente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
                                        rs.getString("direcion"), rs.getDate("fechaNacimiento"), rs.getString("estadoCivil"), rs.getString("email"), rs.getString("idUsuario"), rs.getString("idConsultorio"));
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
                case "C":
                    System.out.println("opcion C");
                    if (this.tf_search.textProperty().get().equals("")) {
                    } else {
                        try {
                            ResultSet rs = this.conect.getSta().executeQuery("select * from paciente where apellido="
                                    + "'" + this.tf_search.textProperty().get() + "'" + ";");
                            while (rs.next()) {
                                pacienteTemp = new DataPaciente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
                                        rs.getString("direcion"), rs.getDate("fechaNacimiento"), rs.getString("estadoCivil"), rs.getString("email"), rs.getString("idUsuario"), rs.getString("idConsultorio"));
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
    }

    public int cantidadCompras() {
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select COUNT(*) as cantidad from compra;");
            return rs.getInt("cantidad") + 1;
        } catch (Exception error) {
            return 0;
        }
    }

//    public Boolean checkCita(){
//        if (this.tf_search.textProperty().get().equals("")) {
//                    } else {
//                        try {
//                            ResultSet rs = this.conect.getSta().executeQuery("select * from paciente where nombre="
//                                    + "'" + this.tf_search.textProperty().get() + "'" + ";");
//                            while (rs.next()) {
//                                pacienteTemp = new DataPaciente(rs.getString("cedula"), rs.getString("nombre"), rs.getString("apellido"),
//                                        rs.getString("direcion"), rs.getDate("fechaNacimiento"), rs.getString("estadoCivil"), rs.getString("email"), rs.getString("idUsuario"), rs.getString("idConsultorio"));
//                                this.data.add(pacienteTemp);
//                                System.out.println(rs.getString("nombre"));
//
//                            }
//                            if (data.isEmpty()) {
//
//                            } else {
//                                table.setItems(data);
//                            }
//                        } catch (Exception error) {
//                        }
//                    }
//    }

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
