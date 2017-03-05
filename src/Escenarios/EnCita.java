package Escenarios;

import Constantes.Settings;
import Recursos.ConexionSQL;
import Recursos.Person;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    ImageView fondo;
    
    public EnCita(ConexionSQL conect) {
        this.conect = conect;
        //this.personTemp=persona;
        this.rootPane = new StackPane();
        
        fondo = new ImageView();
        fondo.setImage(new Image("Img/fondo2.jpg"));
        fondo.setFitWidth(Settings.SCENE_WIDTH + 20);
        fondo.setFitHeight(Settings.SCENE_HEIGHT + 130);

        Label label = new Label("DATOS");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 18));

        Label nombre = new Label("Nombre: ");
        nombre.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label apellido = new Label("Apellido : ");
        apellido.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label direccion = new Label("Dirección : ");
        direccion.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label fechaNacimiento = new Label("Fecha de Nacimiento : ");
        fechaNacimiento.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label estadoCivil = new Label("Estado Civil: ");
        estadoCivil.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label sexo = new Label("Sexo: ");
        sexo.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label email = new Label("Email: ");
        email.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label tipoDeSangre = new Label("Tipo de Sangre: ");
        tipoDeSangre.setFont(Font.font("Cambria", FontWeight.BOLD, 13));

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

        Label label1 = new Label("HISTORIAL CLÍNICO");
        label1.setFont(Font.font("Cambria", FontWeight.BOLD, 18));

        Tab tab2 = new Tab();
        tab2.setGraphic(label1);
        tab2.setClosable(false);

        Label label2 = new Label("INFORMACIÓN");
        label2.setFont(Font.font("Cambria", FontWeight.BOLD, 18));

        Label label3 = new Label("Diagnóstico");
        label3.setFont(Font.font("Cambria", FontWeight.BOLD, 18));

        TextArea textDiagnostico = new TextArea();
        textDiagnostico.setMinSize(790, 70);
        textDiagnostico.setMaxSize(790, 70);

        Label label4 = new Label("Receta");
        label4.setFont(Font.font("Cambria", FontWeight.BOLD, 18));

        plusReceta = new Button("Add");
        Image imagePlus = new Image(getClass().getResourceAsStream("/Img/add.png"));
        plusReceta.setGraphic(new ImageView(imagePlus));

        HBox temp1 = new HBox();
        temp1.getChildren().addAll(label4, plusReceta);
        temp1.setSpacing(20);

        vBoxRecetas = new VBox();

        ScrollPane sp = new ScrollPane();
        sp.setVmax(3);
        sp.setMaxSize(790, 70);
        sp.setMinSize(790, 70);
        //sp.setPrefSize(400, 120);
        sp.setContent(vBoxRecetas);

        Label label5 = new Label("Tratamiento");
        label5.setFont(Font.font("Cambria", FontWeight.BOLD, 18));

        TextArea textTratamiento = new TextArea();
        textTratamiento.setMinSize(300, 100);
        textTratamiento.setMaxSize(300, 100);

        Label fechatemp1 = new Label("Fecha de Inicio");
        fechatemp1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        Label fechatemp2 = new Label("Fecha de Fin");
        fechatemp2.setFont(Font.font("Cambria", FontWeight.BOLD, 13));

        HBox temp2 = new HBox();
        temp2.getChildren().addAll(fechatemp1, fechatemp2);
        temp2.setAlignment(Pos.CENTER);
        temp2.setSpacing(100);
        temp2.setPadding(new Insets(10, 10, 10, 10));

        Label fecha1 = new Label("Fecha: ");
        fecha1.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        tf_fecha1 = new DatePicker();
        tf_fecha2 = new DatePicker();

        HBox temp3 = new HBox();
        temp3.getChildren().addAll(tf_fecha1, tf_fecha2);
        temp3.setPadding(new Insets(10, 10, 10, 10));

        Label costo = new Label("Costo");
        costo.setFont(Font.font("Cambria", FontWeight.BOLD, 13));
        TextField tf_costo = new TextField("");
        
        HBox temp4 = new HBox();
        temp4.getChildren().addAll(costo, tf_costo);
        temp4.setAlignment(Pos.CENTER);
        temp4.setPadding(new Insets(10, 10, 10, 10));        

        VBox temp5 = new VBox();
        temp5.getChildren().addAll(temp2,temp3,temp4); //fecha1
        temp5.setAlignment(Pos.CENTER);
        //temp5.setPadding(new Insets(10, 10, 10, 10));

        Label label6 = new Label("Detalle");
        label6.setFont(Font.font("Cambria", FontWeight.BOLD, 18));
        TextArea textTratamientoDetalle = new TextArea();
        textTratamientoDetalle.setMinSize(200, 100);
        textTratamientoDetalle.setMaxSize(200, 100);

        VBox temp6 = new VBox();
        temp6.getChildren().addAll(label6, textTratamientoDetalle);
        temp6.setPadding(new Insets(10, 10, 10, 10));

        Label label7 = new Label("Plan");
        label7.setFont(Font.font("Cambria", FontWeight.BOLD, 18));
        TextArea textTratamientoPlan = new TextArea();
        textTratamientoPlan.setMinSize(200, 100);
        textTratamientoPlan.setMaxSize(200, 100);

        VBox temp7 = new VBox();
        temp7.getChildren().addAll(label7, textTratamientoPlan);
        temp7.setPadding(new Insets(10, 10, 10, 10));

        HBox temp9 = new HBox();
        temp9.getChildren().addAll(temp5, temp6, temp7);
        temp9.setPadding(new Insets(10, 10, 10, 10));
        

        ingresar = new Button("Ingresar");
        Image imageIng = new Image(getClass().getResourceAsStream("/Img/check.png"));
        ingresar.setGraphic(new ImageView(imageIng));

        VBox vBoxDiagnosticoCita = new VBox();
        vBoxDiagnosticoCita.getChildren().addAll(label3, textDiagnostico, temp1, sp, label5, temp9, ingresar);
        vBoxDiagnosticoCita.setPadding(new Insets(5, 10, 10, 5));
        
        Tab tab3 = new Tab();
        tab3.setGraphic(label2);
        tab3.setClosable(false);
        tab3.setContent(vBoxDiagnosticoCita);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        tabPane.setPadding(new Insets(0, 10, 0, 10));

        rootPane.getChildren().addAll(fondo, tabPane);
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
            Label labeltempMedicamento = new Label("Medicamento: ");
            TextField tf_medicamento = new TextField("");
            Label labeltempDosificacion = new Label("Dosificación: ");
            TextField tf_disificacion = new TextField("");
            Label labeltempCantidad = new Label("Cantidad: ");
            TextField tf_cantidad = new TextField("");
            HBox hboxtemp = new HBox();
            hboxtemp.getChildren().addAll(labeltempMedicamento, tf_medicamento, labeltempDosificacion, tf_disificacion, labeltempCantidad, tf_cantidad);
            this.vBoxRecetas.getChildren().addAll(hboxtemp);
        });
        this.ingresar.setOnAction(e -> {
            if (this.vBoxRecetas.getChildren().isEmpty()) {
                System.out.println("Vacío");
            } else {
                System.out.println("Entró");

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
