package view.subview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Win;

public class CoursesOnWDWin extends Win {

    public VBox root = null;
    public GridPane inputBar = null;

    public Label stuIdLabel = null;
    public TextField stuIdInput = null;

    public Label weekDayChoiceLabel = null;
    public ChoiceBox<String> weekDayChoice = null;

    public HBox controlBar = null;
    public Button cancelBtn = null;
    public Button addBtn = null;

    public CoursesOnWDWin(String winName) {
        super(winName);
        root = new VBox();

        inputBar = new GridPane();
        root.getChildren().add(inputBar);
        inputBar.setAlignment(Pos.CENTER);
        inputBar.setHgap(10);
        inputBar.setVgap(10);
        inputBar.setPadding(new Insets(25, 25, 25, 25));

        stuIdLabel = new Label("Student Id:");
        inputBar.add(stuIdLabel, 0, 0);
        stuIdInput = new TextField();
        inputBar.add(stuIdInput, 1, 0);

        weekDayChoiceLabel = new Label("WeekDay");
        inputBar.add(weekDayChoiceLabel, 0, 1);
        weekDayChoice = new ChoiceBox<>();
        inputBar.add(weekDayChoice, 1 , 1);

        controlBar = new HBox(10);
        controlBar.setPadding(new Insets(0, 25, 0, 25));

        root.getChildren().add(controlBar);
        addBtn = new Button("Confirm");
        controlBar.getChildren().add(addBtn);
        cancelBtn = new Button("Cancel");
        controlBar.getChildren().add(cancelBtn);

        scene = new Scene(root);
    }

    @Override
    public void show() {
        if (stage == null) {
            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Courses On Weekday");
        }
        stage.show();
    }
}
