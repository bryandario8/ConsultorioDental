/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escenarios;

import Recursos.ConexionSQL;
import Recursos.Person;
import java.sql.ResultSet;
import java.time.LocalDate;
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
public class EnCita {
    Boolean state=false;
    StackPane rootPane;
    private TableView<Person> table = new TableView<Person>();
    Person personTemp = null;
    private ObservableList<Person> data = FXCollections.observableArrayList();
    Button search;
    String option = "";
    TextField tf_search;
    ConexionSQL conect;
    VBox info;
    VBox trata;
    Button plusReceta;
    VBox vBoxRecetas;
    Button ingresar;
    DatePicker tf_fecha1;
    DatePicker tf_fecha2;
    LocalDate fechaElegida1;
    LocalDate fechaElegida2;
    Button back;
    
    public EnCita(ConexionSQL conect) {
        this.conect = conect;
        //this.personTemp=persona;
        this.rootPane = new StackPane();

        Label label = new Label("Datos");
        label.setFont(new Font("Arial", 20));

        Label nombre = new Label("Nombre : ");
        nombre.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label apellido = new Label("Apellido : ");
        apellido.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label direccion = new Label("Direccion : ");
        direccion.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label fechaNacimiento = new Label("Fecha de nacimiento : ");
        fechaNacimiento.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label estadoCivil = new Label("Estado Civil : ");
        estadoCivil.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label sexo = new Label("Sexo : ");
        sexo.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label email = new Label("Email : ");
        email.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label tipoDeSangre = new Label("Tipo de sangre : ");
        tipoDeSangre.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Label dataNombre = new Label();
        Label dataApellido = new Label();
        Label dataDireccion = new Label();
        Label dataFechaNacimiento = new Label();
        Label dataEstadoCivil = new Label();
        Label dataSexo = new Label();
        Label dataEmail = new Label();
        Label dataTipoDeSangre = new Label();

        HBox hBoxNombre = new HBox();
        HBox hBoxApellido = new HBox();
        HBox hBoxDireccion = new HBox();
        HBox hBoxFechaNacimiento = new HBox();
        HBox hBoxEstadoCivil = new HBox();
        HBox hBoxSexo = new HBox();
        HBox hBoxEmail = new HBox();
        HBox hBoxTipoDeSangre = new HBox();

        hBoxNombre.getChildren().addAll(nombre, dataNombre);
        hBoxApellido.getChildren().addAll(apellido, dataApellido);
        hBoxDireccion.getChildren().addAll(direccion, dataDireccion);
        hBoxFechaNacimiento.getChildren().addAll(fechaNacimiento, dataFechaNacimiento);
        hBoxEstadoCivil.getChildren().addAll(estadoCivil, dataEstadoCivil);
        hBoxSexo.getChildren().addAll(sexo, dataSexo);
        hBoxEmail.getChildren().addAll(email, dataEmail);
        hBoxTipoDeSangre.getChildren().addAll(tipoDeSangre, dataTipoDeSangre);

        VBox info1 = new VBox();
        info1.getChildren().addAll(new Separator(), hBoxNombre, hBoxApellido, hBoxDireccion, hBoxFechaNacimiento, hBoxEstadoCivil, hBoxSexo, hBoxEmail, hBoxTipoDeSangre, new Separator());

        Tab tab1 = new Tab();
        tab1.setGraphic(label);
        tab1.setClosable(false);
        tab1.setContent(info1);

        Label label1 = new Label("Historial clinico");
        label1.setFont(new Font("Arial", 20));

        Tab tab2 = new Tab();
        tab2.setGraphic(label1);
        tab2.setClosable(false);

        Label label2 = new Label("Diagnostico y Prescripcion");
        label2.setFont(new Font("Arial", 20));

        Label label3 = new Label("Diagnostico");
        label3.setFont(new Font("Arial", 20));

        TextArea textDiagnostico = new TextArea();
        textDiagnostico.setMinSize(790, 70);
        textDiagnostico.setMaxSize(790, 70);

        Label label4 = new Label("Receta");
        label4.setFont(new Font("Arial", 20));

        plusReceta = new Button("+");

        HBox temp1 = new HBox();
        temp1.getChildren().addAll(label4, plusReceta);

        vBoxRecetas = new VBox();

        ScrollPane sp = new ScrollPane();
        sp.setVmax(3);
        sp.setMaxSize(790, 70);
        sp.setMinSize(790, 70);
        //sp.setPrefSize(400, 120);
        sp.setContent(vBoxRecetas);

        Label label5 = new Label("Tratamiento");
        label5.setFont(new Font("Arial", 20));

        TextArea textTratamiento = new TextArea();
        textTratamiento.setMinSize(300, 100);
        textTratamiento.setMaxSize(300, 100);

        Label fechatemp1 = new Label("Fecha de inicio");
        Label fechatemp2 = new Label("Fecha final");

        HBox temp2 = new HBox();
        temp2.getChildren().addAll(fechatemp1, fechatemp2);
        temp2.setAlignment(Pos.CENTER);
        temp2.setSpacing(100);

        Label fecha1 = new Label("Fecha(dd/mm/yyyy): ");
        tf_fecha1 = new DatePicker();
        tf_fecha2 = new DatePicker();

        HBox temp3 = new HBox();
        temp3.getChildren().addAll(tf_fecha1, tf_fecha2);

        Label costo = new Label("Costo");
        TextField tf_costo = new TextField("");
        
        HBox temp4 = new HBox();
        temp4.getChildren().addAll(costo, tf_costo);        

        VBox temp5 = new VBox();
        temp5.getChildren().addAll(temp2, fecha1,temp3,temp4);
        temp5.setAlignment(Pos.CENTER);

        Label label6 = new Label("Detalle");
        TextArea textTratamientoDetalle = new TextArea();
        textTratamientoDetalle.setMinSize(200, 100);
        textTratamientoDetalle.setMaxSize(200, 100);

        VBox temp6 = new VBox();
        temp6.getChildren().addAll(label6, textTratamientoDetalle);

        Label label7 = new Label("Plan");
        TextArea textTratamientoPlan = new TextArea();
        textTratamientoPlan.setMinSize(200, 100);
        textTratamientoPlan.setMaxSize(200, 100);

        VBox temp7 = new VBox();
        temp7.getChildren().addAll(label7, textTratamientoPlan);

        HBox temp9 = new HBox();
        temp9.getChildren().addAll(temp5, temp6, temp7);
        

        ingresar = new Button("Ingresar");

        VBox vBoxDiagnosticoCita = new VBox();
        vBoxDiagnosticoCita.getChildren().addAll(label3, textDiagnostico, temp1, sp, label5, temp9, ingresar);

        Tab tab3 = new Tab();
        tab3.setGraphic(label2);
        tab3.setClosable(false);
        tab3.setContent(vBoxDiagnosticoCita);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab1, tab2, tab3);

        rootPane.getChildren().addAll(tabPane);
        setButtons();

    }

    public StackPane getRootPane() {
        return rootPane;
    }

    public void setButtons() {
        tf_fecha1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                fechaElegida1 = tf_fecha1.getValue();
            }

        });
        tf_fecha2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                fechaElegida2 = tf_fecha2.getValue();
            }

        });
        this.plusReceta.setOnAction(e -> {
            Label labeltempMedicamento = new Label("Medicament ");
            TextField tf_medicamento = new TextField("");
            Label labeltempDosificacion = new Label("Dosificacion ");
            TextField tf_disificacion = new TextField("");
            Label labeltempCantidad = new Label("Cantidad ");
            TextField tf_cantidad = new TextField("");
            HBox hboxtemp = new HBox();
            hboxtemp.getChildren().addAll(labeltempMedicamento, tf_medicamento, labeltempDosificacion, tf_disificacion, labeltempCantidad, tf_cantidad);
            this.vBoxRecetas.getChildren().addAll(hboxtemp);
        });
        this.ingresar.setOnAction(e -> {
            if (this.vBoxRecetas.getChildren().isEmpty()) {
                System.out.println("Vacio");
            } else {
                System.out.println("Entro");

                for (int i = 0; i < this.vBoxRecetas.getChildren().size(); i++) {
                    System.out.println(i);
                    HBox hboxtemp = (HBox) this.vBoxRecetas.getChildren().get(i);
                    TextField tf_medicamento = (TextField) hboxtemp.getChildren().get(1);
                    System.out.println(tf_medicamento.textProperty().get());
                }
            }
        });

    }

    public void setPerson(Person persona) {
        this.personTemp = persona;
    }

    public void llenarHistorial(Person person) {

    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
    

}
