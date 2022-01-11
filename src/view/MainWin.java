package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainWin extends Win {
    public BorderPane root = null;

    // menu
    public MenuBar menuBar = null;
    public Menu fileMenu = null;
    public MenuItem conMenuItem = null;
    public Menu aboutMenu = null;
    public MenuItem helpMenuItem = null;
    public MenuItem aboutMenuItem = null;

    //button part
    public VBox mainPane = null;
    //main part - control bar
    public FlowPane addBar = null;
    public FlowPane showBar = null;
    public FlowPane searchBar = null;

    // TODO
    public Button addStuButton = null;
    public Button addCourseButton = null;
    public Button courseForStu = null;
    public Button stuForCourse = null;
    public Button enrollCourse = null;
    public Button courseOnWeekDay = null;
    public Button showStu = null;
    public Button showCourse = null;

    //main part - sql result part
    public TabPane sqlResTabPane = null;
    public Tab sqlInfoTab = null;
    public TextArea sqlInfoOutput = null;

    public MainWin(String winName) {
        super(winName);
        initRoot();
        initMenu();
        initMainPart();
        scene = new Scene(root);
    }

    protected void initRoot() {root = new BorderPane();}

    protected void initMenu() {
        menuBar = new MenuBar();
        fileMenu = new Menu("File");
        conMenuItem = new MenuItem("Connect");
        fileMenu.getItems().addAll(conMenuItem);
        aboutMenu = new Menu("About");
        helpMenuItem = new MenuItem("Help");
        aboutMenuItem = new MenuItem("About");
        aboutMenu.getItems().addAll(helpMenuItem, new SeparatorMenuItem(), aboutMenuItem);

        menuBar.getMenus().addAll(fileMenu, aboutMenu);
        root.setTop(menuBar);
    }

    protected void initMainPart() {
        mainPane = new VBox();
        // add bar
        addBar = new FlowPane();
        addBar.setPadding(new Insets(10, 10, 10, 10));
        addBar.setVgap(15);
        addBar.setHgap(15);

        // button
        addStuButton = new Button("Add Student");
        addCourseButton = new Button("Add Course");
        enrollCourse = new Button("Enroll Courses");
        addBar.getChildren().addAll(addStuButton, addCourseButton, enrollCourse);

        // show bar
        showBar = new FlowPane();
        showBar.setPadding(new Insets(10, 10, 10, 10));
        showBar.setVgap(15);
        showBar.setHgap(15);
        showStu = new Button("Show All Students");
        showCourse = new Button("Show All Courses");
        showBar.getChildren().addAll(showStu, showCourse);

        // Search Bar
        searchBar = new FlowPane();
        searchBar.setPadding(new Insets(10, 10, 10, 10));
        searchBar.setVgap(15);
        searchBar.setHgap(15);
        courseForStu = new Button("Look up Courses");
        stuForCourse = new Button("Look up Students");
        courseOnWeekDay = new Button("Courses On one day");
        searchBar.getChildren().addAll(courseForStu, stuForCourse, courseOnWeekDay);

        //sql result part
        sqlResTabPane = new TabPane();
        sqlInfoTab = new Tab("Info");
        sqlInfoOutput = new TextArea();
        sqlInfoOutput.setEditable(false);
        sqlInfoTab.setContent(sqlInfoOutput);
        sqlResTabPane.getTabs().addAll(sqlInfoTab);

        mainPane.getChildren().addAll(showBar, addBar, searchBar, sqlResTabPane);
        root.setCenter(mainPane);
    }

    public void show() {
        if(stage == null) {
            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Student Management System");
        }
        stage.show();
    }
}
