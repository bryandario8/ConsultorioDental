package consultoriodental;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
        stagePrincipal.setTitle("SADCO - Sistema de Administración de Consultorio Odontológico");
        stagePrincipal.getIcons().add(new Image("/Img/muelita.gif"));
        stagePrincipal.setResizable(false);
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
