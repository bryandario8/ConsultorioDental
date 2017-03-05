/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Constantes.Settings;
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
import java.sql.Date;
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
    Button modif;
    Button clear;
    Button save;
    Button cancel;
    
    ImageView fondo;
    
    String option = "";
    TextField tf_search;
    ConexionSQL conect;
    VBox info;
    VBox info1;
    VBox info2;
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
        
        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo2.jpg"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 130);
       
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
        modif = new Button("Modificar");
        clear = new Button(" Clear ");
        save = new Button(" Save ");
        cancel = new Button(" Cancel ");
        
        HBox contenedor1 = new HBox();

        table.setEditable(true);

        TableColumn nameCol = new TableColumn(" Nombre ");
        nameCol.impl_setReorderable(false);
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<SuministroData, String>("name"));

        TableColumn cantidadCol = new TableColumn("Cantidad");
        cantidadCol.impl_setReorderable(false);
        cantidadCol.setMinWidth(100);
        cantidadCol.setCellValueFactory(
                new PropertyValueFactory<SuministroData, Integer>("cantidad"));   

        TableColumn fechaCol = new TableColumn("Fecha de Vencimiento");
        fechaCol.impl_setReorderable(false);
        fechaCol.setMinWidth(200);
        fechaCol.setCellValueFactory(
                new PropertyValueFactory<SuministroData, Date>("fechaVencimiento"));

        table.getColumns().addAll(nameCol, cantidadCol, fechaCol);

        Label label = new Label("Datos");
        label.setFont(new Font("Arial", 20));
        ingresar=new Button ("Ingresar");
        Label codigo1 = new Label("Codigo : ");
        codigo1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label nombre1 = new Label("Nombre : ");
        nombre1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label cantidad1 = new Label("Cantidad : ");
        cantidad1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label fechaVence1= new Label("Fecha de Vencimiento : ");
        fechaVence1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label fechaRegistro1= new Label("Fecha de Registro : ");
        fechaRegistro1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label proveedor1 = new Label("Nombre de Proveedor : ");       
        proveedor1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label usuario1 = new Label("Nombre de Usuario : ");       
        usuario1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label consultorio1 = new Label("Nombre del Consultorio : ");       
        consultorio1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        
        
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
        
        
        
        
        Label res_idsuministro1 = new Label();
        Label res_nombre1 = new Label();
        Label res_cantidad1 = new Label();
        Label res_fechaVencimiento1 = new Label();
        Label res_fechaRegistro1 = new Label();
        Label res_idproveedor1 = new Label();
        Label res_idUsuario1 = new Label();
        Label res_idConsultorio1 = new Label();

        
        HBox hboxIdSuministro1 = new HBox();
        HBox hboxNombre1 = new HBox();
        HBox hboxCantidad1 = new HBox();
        HBox hboxFechaVence1 = new HBox();
        HBox hboxFechaRegistro1 = new HBox();
        HBox hboxIdProveedor1 = new HBox();
        HBox hboxIdUsuario1 = new HBox();
        HBox hboxIdConsultorio1 = new HBox();
        
        HBox hboxNombre = new HBox();
        HBox hboxCantidad = new HBox();
        HBox hboxFechaVence = new HBox();
        HBox hboxIdProveedor = new HBox();
        HBox hboxIdConsultorio = new HBox();
        
        hboxIdSuministro1.getChildren().addAll(codigo1,res_idsuministro1);
        hboxNombre1.getChildren().addAll(nombre1, res_nombre1);
        hboxCantidad1.getChildren().addAll(cantidad1, res_cantidad1);
        hboxFechaVence1.getChildren().addAll(fechaVence1, res_fechaVencimiento1);
        hboxFechaRegistro1.getChildren().addAll(fechaRegistro1, res_fechaRegistro1);
        hboxIdProveedor1.getChildren().addAll(proveedor1, res_idproveedor1);
        hboxIdUsuario1.getChildren().addAll(usuario1, res_idUsuario1);
        hboxIdConsultorio1.getChildren().addAll(consultorio1, res_idConsultorio1);
        
        
        final ScrollPane sp = new ScrollPane();
        sp.setVmax(3);
        sp.setPrefSize(400, 375);
        sp.setContent(table);

        final ScrollPane sp1 = new ScrollPane();
        sp1.setVmax(3);
        sp1.setPrefSize(400, 375);
       
        
        
        
       
        
        dataCodigo = new TextField();
        dataNombre = new TextField();
        dataCantidad = new TextField();
        
       
        llenarComboBoxProveedores();
        tf_proveedores.setPromptText(" Proveedores ");
        tf_proveedores.setEditable(true);
        
        
        llenarComboBoxConsultorios();
        tf_consultorios.setPromptText(" Consultorios Disponibles ");
        tf_consultorios.setEditable(true);
        
        
        
        hboxNombre.getChildren().addAll(nombre);
        hboxCantidad.getChildren().addAll(cantidad);
        hboxFechaVence.getChildren().addAll(fechaVence);
        hboxIdProveedor.getChildren().addAll(proveedor);  // nombre del proveedor
        hboxIdConsultorio.getChildren().addAll(consultorio);

        info1 = new VBox();
        info1.getChildren().addAll(new Separator(),hboxIdSuministro1, hboxNombre1, hboxCantidad1, hboxFechaVence1,hboxFechaRegistro1,hboxIdProveedor1,hboxIdUsuario1,hboxIdConsultorio1, new Separator());

        info = new VBox();
                
        HBox hboxOptions = new HBox();
        hboxOptions.getChildren().addAll(clear, save, cancel);
        
        info2 = new VBox();
        info2.getChildren().addAll(hboxNombre, hboxCantidad, hboxFechaVence, hboxIdProveedor, hboxIdConsultorio, hboxOptions);
        sp1.setContent(info2);
        info.getChildren().addAll(info1,sp1);
        
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SuministroData>() {

            @Override
            public void changed(ObservableValue<? extends SuministroData> observable, SuministroData oldValue, SuministroData newValue) {
                //System.out.println(newValue.getName());
                try{
                    sumin = newValue;
                    res_idsuministro1.setText(sumin.getCodigo());
                    res_nombre1.setText(sumin.getName());
                    res_cantidad1.setText(sumin.getCantidad().toString());
                    res_fechaVencimiento1.setText(sumin.getFechaVencimiento().toLocalDate().toString());
                    res_fechaRegistro1.setText(sumin.getFechaRegistro().toLocalDate().toString());
                    res_idproveedor1.setText(sumin.getIdProveedor());
                    res_idUsuario1.setText(sumin.getIdProveedor());
                    res_idConsultorio1.setText(sumin.getIdConsultorio());
                    info2.setVisible(false);
                    info1.setVisible(true);
                }catch(NullPointerException e){
                    info2.setVisible(false);               // ocurre un excepcion por eso ambos false
                    info1.setVisible(false);
                }
                
                
                
            }
        });

        
                
        contenedor1.getChildren().addAll(contenedorRadio, tf_search, search, back,plus,modif);
        VBox contenedorGeneral = new VBox();
        HBox contenedor2 = new HBox();
        contenedor2.getChildren().addAll(sp, info);
        contenedorGeneral.getChildren().addAll(contenedor1, contenedor2);
        rootPane.getChildren().addAll(fondo, contenedorGeneral);
        setButtons();
        //info1.setVisible(false);

        
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
                            ResultSet rs = this.conect.getSta().executeQuery("select * from suministro,inventario,compra where "
                        + "suministro.idSuministro= inventario.idSuministro and suministro.idSuministro= compra.idSuministro and"
                                    + " suministro.idSuministro= '" + this.tf_search.textProperty().get() + "'" + ";");
                            int cont=0;
                            while (rs.next()) {
                                sumin = new SuministroData(rs.getString("idSuministro"), rs.getString("nombre"), rs.getInt("cantidad"),
                            rs.getDate("fechaVencimiento"), rs.getDate("fechaRegistro"), rs.getString("idProveedor"),
                            rs.getString("idUsuario"),rs.getString("idConsultorio"));
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
                            ResultSet rs = this.conect.getSta().executeQuery("select * from suministro,inventario,compra where "
                        + "suministro.idSuministro= inventario.idSuministro and suministro.idSuministro= compra.idSuministro and"
                                    +  " suministro.nombre like '" + this.tf_search.textProperty().get() + "%'" + ";");
                            int size = 0;
                            while (rs.next()) {
                                sumin = new SuministroData(rs.getString("idSuministro"), rs.getString("nombre"), rs.getInt("cantidad"),
                            rs.getDate("fechaVencimiento"), rs.getDate("fechaRegistro"), rs.getString("idProveedor"),
                            rs.getString("idUsuario"),rs.getString("idConsultorio"));
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
        this.cancel.setOnAction(e -> {
            clear();
            info2.setVisible(false);
        });
        this.plus.setOnAction(e -> {
            clear();
            info2.setVisible(true);
        });
        this.clear.setOnAction(e -> {

            clear();

        });
        this.back.setOnAction(e ->{
           this.selectBack=false; 
        });
        this.modif.setOnAction(e -> {
            if (this.sumin != null) {
                info2.setVisible(true);

                dataCodigo.setText(sumin.getCodigo());
                dataNombre.setText(sumin.getName());
                dataCantidad.setText(sumin.getCantidad().toString());
                tf_fecha.setValue(sumin.getFechaVencimiento().toLocalDate());  // puede ser null
                tf_proveedores.setValue(sumin.getIdProveedor());
                tf_consultorios.setValue(sumin.getIdConsultorio());

            }
        });
        
        save.setOnAction(e->{
            System.out.println("Entro1");
            date1 = java.sql.Date.valueOf(fechaElegida.format(DateTimeFormatter.ISO_DATE));  // puede ser null
            if (!this.dataCodigo.textProperty().get().equals("") && !this.dataNombre.textProperty().get().equals("")
                    && !this.dataCantidad.textProperty().get().equals("")  ) {
                if ((this.dataCodigo.textProperty().get().length() <= 5) && (this.dataNombre.textProperty().get().length() <= 30)
                        && (this.dataCantidad.textProperty().get().length() <= 11) ) {
                    try {
                        String cantSumin = cantidadSuministros();
                        PreparedStatement pps =this.conect.getCo().prepareStatement("INSERT INTO suministro(idSuministro,nombre,cantidad,fechaVencimiento,idConsultorio) VALUES(?,?,?,?,?)");
                        pps.setString(1, cantSumin);
                        pps.setString(2, this.dataNombre.textProperty().get());
                        pps.setInt(3, Integer.parseInt(this.dataCantidad.textProperty().get()));
                        pps.setDate(4, date1);
                        pps.setString(5, (String)tf_consultorios.getValue());
                        
                        PreparedStatement ps =this.conect.getCo().prepareStatement("INSERT INTO compra(idCompra,idUsuario,idSuministro,idProveedor) VALUES(?,?,?,?);");
                        ps.setString(1, cantidadCompras());
                        ps.setString(2, getUsuario().getIdUsuario());
                        ps.setString(3, cantSumin);
                        ps.setString(4, (String) tf_proveedores.getValue());
                
                        PreparedStatement s =this.conect.getCo().prepareStatement("INSERT INTO inventario(idInventario,idUsuario,idSuministro,fechaRegistro) VALUES(?,?,?,?);");
                        s.setString(1, numInventario());
                        s.setString(2, getUsuario().getIdUsuario());
                        s.setString(3, cantSumin);
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

   
    public void clear() {
        dataCodigo.clear();
        dataNombre.clear();
        dataCantidad.clear();
        tf_fecha.setValue(null);
        tf_proveedores.getItems().clear();
        llenarComboBoxProveedores();
        tf_consultorios.getItems().clear();
        llenarComboBoxConsultorios();
    }
    
    public void llenarTabla(){
        table.getItems().clear();
        data.clear();

            try {
                ResultSet rs = this.conect.getSta().executeQuery("select * from suministro,inventario,compra where "
                        + "suministro.idSuministro= inventario.idSuministro and suministro.idSuministro= compra.idSuministro;");

                while (rs.next()) {
                    sumin = new SuministroData(rs.getString("idSuministro"), rs.getString("nombre"), rs.getInt("cantidad"),
                            rs.getDate("fechaVencimiento"), rs.getDate("fechaRegistro"), rs.getString("idProveedor"),
                            rs.getString("idUsuario"),rs.getString("idConsultorio"));
                    this.data.add(sumin);
                    //System.out.println(rs.getString("nombre"));

                }
                if (data.isEmpty()) {
                } else {
                    table.setItems(data);
                }
            } catch (Exception error) {
            }
    }
    
    public Usuario getUsuario(){
        return login.getUsuarioTemp();
    }
        
    public String cantidadSuministros(){
        int cont=0;
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select * from suministro where idConsultorio = "+
                    (String)tf_consultorios.getValue() +" ;");
            while(rs.next()){
                cont++;
            }
            cont++;
            if(cont<10) return "S00"+cont;
            else return "S0"+cont;
        } catch (Exception error) {
            return "";
        }
    }
    
    public String cantidadCompras(){
        int cont=0;
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select * from compra;");
            while(rs.next()){
                cont++;
            }
            cont++;
            if(cont<10) return "P00"+cont;
            else return "P0"+cont;
        } catch (Exception error) {
            return "";
        } 
    }
    
    public String numInventario(){
        int cont=0;
        try {
            ResultSet rs = this.conect.getSta().executeQuery("select *from inventario where idConsultorio = "+
                    (String)tf_consultorios.getValue() +" ;");
            while(rs.next()){
               cont++; 
            }
            cont++;
            if(cont<10) return "I00"+cont;
            else return "I0"+cont;
        } catch (Exception error) {
            return "";
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