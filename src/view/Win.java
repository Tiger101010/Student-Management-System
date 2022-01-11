package view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Win {
    public Stage stage = null;
    public Scene scene = null;
    public String name = null;
    public Win(String winName) {
        name = winName;
    }
    public abstract void show();
    public void close() {
        if(stage == null)
            return;
        stage.close();
    }
}
