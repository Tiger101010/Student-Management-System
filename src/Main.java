import controller.Controller;
import controller.ControllerManager;
import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MainWin;


public class Main extends Application {
    private Controller controller = null;

    @Override
    public void start(Stage stage) throws Exception {
        MainWin mainWin = new MainWin("main");
        MainController mainController = new MainController(mainWin);
        this.controller = mainController;

        ControllerManager.controllerHashMap.put(mainWin.name, mainController);
        stage.setScene(mainWin.scene);
        stage.setTitle("Student Management System");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        this.controller.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
