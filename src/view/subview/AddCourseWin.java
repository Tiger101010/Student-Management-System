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

public class AddCourseWin extends Win {

    public VBox root = null;
    public GridPane inputBar = null;

    public Label courseNameLabel = null;
    public TextField courseNameInput = null;

    public Label courseStartLabel = null;
    public ChoiceBox<String> courseStartChoice = null;

    public Label courseEndLabel = null;
    public ChoiceBox<String> courseEndChoice = null;

    public Label weekDayChoiceLabel = null;
    public ChoiceBox<String> weekDayChoice = null;

    public HBox controlBar = null;
    public Button cancelBtn = null;
    public Button addBtn = null;

    public AddCourseWin(String winName) {
        super(winName);
        root = new VBox();

        inputBar = new GridPane();
        root.getChildren().add(inputBar);
        inputBar.setAlignment(Pos.CENTER);
        inputBar.setHgap(10);
        inputBar.setVgap(10);
        inputBar.setPadding(new Insets(25, 25, 25, 25));

        courseNameLabel = new Label("Course Name:");
        inputBar.add(courseNameLabel, 0, 0);
        courseNameInput = new TextField();
        inputBar.add(courseNameInput, 1, 0);

        weekDayChoiceLabel = new Label("WeekDay");
        inputBar.add(weekDayChoiceLabel, 0, 1);
        weekDayChoice = new ChoiceBox<>();
        inputBar.add(weekDayChoice, 1 , 1);

        courseStartLabel = new Label("Course Start Time");
        inputBar.add(courseStartLabel, 0, 2);
        courseStartChoice = new ChoiceBox<>();
        inputBar.add(courseStartChoice, 1, 2);

        courseEndLabel = new Label("Course End Time");
        inputBar.add(courseEndLabel, 0, 3);
        courseEndChoice = new ChoiceBox<>();
        inputBar.add(courseEndChoice, 1, 3);

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
            stage.setTitle("Course Adding");
        }
        stage.show();
    }
}
