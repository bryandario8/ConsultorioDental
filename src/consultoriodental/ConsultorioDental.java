/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultoriodental;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Flores
 */
public class ConsultorioDental extends Application {
    
    private Stage stagePrincipal;
    SceneOrganizer organizer;

    @Override
    public void start(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
        organizer = new SceneOrganizer();
        organizer.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new Quit());
        stagePrincipal.setScene(organizer.getScene());
        stagePrincipal.setTitle("Consultorio Dental");
        //stagePrincipal.getIcons().add(new Image("file:shark-icon-png-4.png"));
        stagePrincipal.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private class Quit implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (!organizer.getState()) {
                stagePrincipal.close();
            }

        }

    }
}
