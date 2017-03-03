/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import java.time.LocalTime;
import Recursos.ConexionSQL;
import Recursos.Person;
import Recursos.SuministroData;
import Recursos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
 * @author FABKME
 */
public class Suministro {
    StackPane rootPane;

    ComboBox tf_proveedores = new ComboBox();
    ComboBox tf_consultorios = new ComboBox();
    DatePicker tf_fecha = new DatePicker();
    LocalDate fechaElegida;
    java.sql.Date date1;
    java.sql.Time time1;
    java.util.Date date;
    
    Boolean state=false;
    Boolean selectPlus=true;
    Boolean selectBack=true;
    
    private TableView<SuministroData> table = new TableView<SuministroData>();
    SuministroData sumin = null;
    private ObservableList<SuministroData> data = FXCollections.observableArrayList();
    Button search;
    Button back;
    Button plus;
    String option = "";
    TextField tf_search;
    ConexionSQL conect;
    VBox info;
    Button ingresar;
    
    
    TextField dataCodigo;
        TextField dataNombre;
        TextField dataCantidad;
        //TextField dataFechaVence;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
    HBox hBoxCodigo = new HBox();
        HBox hBoxNombre = new HBox();
        HBox hBoxCantidad = new HBox();
        HBox hBoxFechaVence = new HBox();  // numero de dias a partir de hoy? 
        HBox hBoxProveedor= new HBox();
        HBox hBoxConsultorio = new HBox();
        
    private Login login;
    
    public Suministro(ConexionSQL conect) {
this.conect = conect;
        //this.personTemp=persona;
        this.rootPane = new StackPane();
       
        ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Codigo");
        rb1.setToggleGroup(group);
        rb1.setUserData("A");

        RadioButton rb2 = new RadioButton("Nombre");
        rb2.setToggleGroup(group);
        rb2.setUserData("B");

        RadioButton rb3 = new RadioButton("Cantidad");
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
        contenedorRadio.setSpacing(10);

        tf_search = new TextField("");

        search = new Button("Search");
        back = new Button("Back");
        plus=new Button("+");
        
        HBox contenedor1 = new HBox();

        table.setEditable(true);

        TableColumn nameCol = new TableColumn("Nombre");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("name"));

        TableColumn cantidadCol = new TableColumn("Cantidad");
        cantidadCol.setMinWidth(100);
        cantidadCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("cantidad"));   

        TableColumn fechaCol = new TableColumn("Fecha de Vencimiento");
        fechaCol.setMinWidth(200);
        fechaCol.setCellValueFactory(
                new PropertyValueFactory<Person, Date>("fechaVencimiento"));

        table.getColumns().addAll(nameCol, cantidadCol, fechaCol);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SuministroData>() {

            @Override
            public void changed(ObservableValue<? extends SuministroData> observable, SuministroData oldValue, SuministroData newValue) {
                System.out.println(newValue.getName());
                sumin = newValue;
                info.setVisible(true);
            }
        });

        final ScrollPane sp = new ScrollPane();
        sp.setVmax(3);
        sp.setPrefSize(400, 375);
        sp.setContent(table);

        /*final ScrollPane sp1 = new ScrollPane();
        sp1.setVmax(3);
        sp1.setPrefSize(400, 375);*/

       
        
        
        Label label = new Label("Datos");
        label.setFont(new Font("Arial", 20));
        ingresar=new Button ("Ingresar");
        Label codigo = new Label("Codigo : ");
        codigo.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label nombre = new Label("Nombre : ");
        nombre.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label cantidad = new Label("Cantidad : ");
        cantidad.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label fechaVence= new Label("Fecha de Vencimiento : ");
        fechaVence.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label proveedor = new Label("Nombre de Proveedor : ");       
        proveedor.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label consultorio = new Label("Nombre del Consultorio : ");       
        consultorio.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        
       
        
        dataCodigo = new TextField();
        dataNombre = new TextField();
        dataCantidad = new TextField();
        
       
        llenarComboBoxProveedores();
        tf_proveedores.setPromptText(" Proveedores ");
        tf_proveedores.setEditable(true);
        
        
        llenarComboBoxConsultorios();
        tf_consultorios.setPromptText(" Consultorios Disponibles ");
        tf_consultorios.setEditable(true);
        
        
        
        hBoxCodigo.getChildren().addAll(codigo);
        hBoxNombre.getChildren().addAll(nombre);
        hBoxCantidad.getChildren().addAll(cantidad);
        hBoxFechaVence.getChildren().addAll(fechaVence);
        hBoxProveedor.getChildren().addAll(proveedor);  // nombre del proveedor
        hBoxConsultorio.getChildren().addAll(consultorio);

        VBox info1 = new VBox();
        info1.getChildren().addAll(new Separator(),hBoxCodigo, hBoxNombre, hBoxCantidad, hBoxFechaVence,hBoxProveedor,hBoxConsultorio, new Separator());

        info = new VBox();
        info.getChildren().addAll(info1);
        
        contenedor1.getChildren().addAll(contenedorRadio, tf_search, search, back,plus);
        VBox contenedorGeneral = new VBox();
        HBox contenedor2 = new HBox();
        contenedor2.getChildren().addAll(sp, info);
        contenedorGeneral.getChildren().addAll(contenedor1, contenedor2);
        rootPane.getChildren().addAll(contenedorGeneral);
        setButtons();
        info.setVisible(false);

        
    }

    public Suministro(ConexionSQL conect, Login login){
        this(conect);
        this.login = login;
    }
    
    public StackPane getRootPane() {
        return rootPane;
    }

   
    public void setButtons() {
            
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
                            ResultSet rs = this.conect.getSta().executeQuery("select * from usuario where idUsuario="
                                    + "'" + this.tf_search.textProperty().get() + "'" + ";");
                            int cont=0;
                            while (rs.next()) {
                                sumin = new SuministroData(rs.getString("nombre"),  rs.getInt("cantidad"), rs.getDate("fechaVencimiento"));
                                this.data.add(sumin);
                                System.out.println(rs.getString("nombre"));
                                cont++;
                            }
                            if (data.isEmpty()) {
                                this.selectPlus = false; 
                                System.out.println(data.isEmpty());
                            } /*else if(personTemp ){
                            }*/else {
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
                            ResultSet rs = this.conect.getSta().executeQuery("select * from usuario where nombre like "
                                    + "'%" + this.tf_search.textProperty().get() + "%'" + ";");
                            int size = 0;
                            while (rs.next()) {
                                sumin = new SuministroData(rs.getString("nombre"), rs.getInt("cantidad"), rs.getDate("fechaVencimiento"));
                                this.data.add(sumin);
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
                hBoxCodigo.getChildren().add( dataCodigo);
                hBoxNombre.getChildren().add( dataNombre);
                hBoxCantidad.getChildren().add(dataCantidad);
                hBoxFechaVence.getChildren().add( tf_fecha );
                hBoxProveedor.getChildren().add(tf_proveedores);
                hBoxConsultorio.getChildren().add(tf_consultorios);
                info.getChildren().add(ingresar);
                info.setVisible(true);
            }
                    
            
        });
    
        ingresar.setOnAction(e->{
            System.out.println("Entro1");
            date1 = java.sql.Date.valueOf(fechaElegida.format(DateTimeFormatter.ISO_DATE));
            if (!this.dataCodigo.textProperty().get().equals("") && !this.dataNombre.textProperty().get().equals("")
                    && !this.dataCantidad.textProperty().get().equals("")  ) {
                if ((this.dataCodigo.textProperty().get().length() <= 5) && (this.dataNombre.textProperty().get().length() <= 30)
                        && (this.dataCantidad.textProperty().get().length() <= 11) ) {
                    try {
                
                        PreparedStatement pps =this.conect.getCo().prepareStatement("INSERT INTO suministro(idSuministro,nombre,cantidad,fechaVencimiento,idConsultorio) VALUES(?,?,?,?,?)");
                        pps.setString(1, this.dataCodigo.textProperty().get());
                        pps.setString(2, this.dataNombre.textProperty().get());
                        pps.setInt(3, Integer.parseInt(this.dataCantidad.textProperty().get()));
                        pps.setDate(4, date1);
                        pps.setString(5, (String)tf_consultorios.getValue());
                        
                        PreparedStatement ps =this.conect.getCo().prepareStatement("INSERT INTO compra(idCompra,idUsuario,idSuministro,idProveedor) VALUES(?,?,?,?);");
                        ps.setString(1, "com"+cantidadCompras());
                        ps.setString(2, getUsuario().getIdUsuario());
                        ps.setString(3, this.dataCodigo.textProperty().get());
                        ps.setString(4, (String) tf_proveedores.getValue());
                
                        PreparedStatement s =this.conect.getCo().prepareStatement("INSERT INTO inventario(idInventario,idUsuario,idSuministro,fechaRegistro) VALUES(?,?,?,?);");
                        s.setString(1, "inv"+numInventario());
                        s.setString(2, getUsuario().getIdUsuario());
                        s.setString(3, this.dataCodigo.textProperty().get());
                        LocalDate localTime=LocalDate.now();
                        java.sql.Date dateDB = java.sql.Date.valueOf(localTime);
                        s.setDate(4,dateDB ); 
                        
                        pps.executeUpdate();
                        ps.executeUpdate();
                        s.executeUpdate();
                        System.out.println("Entro2");
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        
                    }
                    
                }
            }
        });
        
 

    }

       
    public Usuario getUsuario(){
        return login.getUsuarioTemp();
    }
        
    public int cantidadCompras(){
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select COUNT(*) as cantidad from compra;");
            return rs.getInt("cantidad") +1;
        } catch (Exception error) {
            return 0;
        } 
    }
    
    public int numInventario(){
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select COUNT(*) as cantidad from inventario;");
            return rs.getInt("cantidad") +1;
        } catch (Exception error) {
            return 0;
        }
    }
    
    private void llenarComboBoxProveedores() {
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select * from proveedor;");
            while (rs.next()) {
                this.tf_proveedores.getItems().add(rs.getString("ruc"));
                System.out.println(rs.toString());
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

        
    
        
    public void setPerson(SuministroData persona) {
        this.sumin = persona;
    }

    

}