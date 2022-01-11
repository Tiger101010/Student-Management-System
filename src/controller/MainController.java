package controller;

import controller.subcontrol.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import view.*;
import model.result.SQLResult;
import model.result.SQLResultTable;
import model.service.ManagementService;
import view.subview.*;

import java.util.Map;

public class MainController implements Controller {
    private MainWin mainWin = null;
    private ManagementService service = null;

    public MainController(MainWin mainWin) {
        this.mainWin = mainWin;
        initMenu();
        initControlBar();
    }

    protected void initMenu() {
        mainWin.conMenuItem.setOnAction(actionEvent -> {
            if(service == null) {
                service = new ManagementService();
            }
        });
    }

    protected void initControlBar() {
        mainWin.addStuButton.setOnAction(actionEvent -> {
            AddStudentWin addStudentWin = new AddStudentWin("Student Add");
            AddStudentController addStudentController = new AddStudentController(service, addStudentWin);
            addStudentController.show();
        });
        mainWin.addCourseButton.setOnAction(actionEvent -> {
            AddCourseWin addCourseWin = new AddCourseWin("Course Add");
            AddCourseController addCourseController = new AddCourseController(service, addCourseWin);
            addCourseController.show();
        });
        mainWin.enrollCourse.setOnAction(actionEvent -> {
            EnrollCourseWin enrollCourseWin = new EnrollCourseWin("Enroll Course");
            EnrollCourseController enrollCourseController = new EnrollCourseController(service, enrollCourseWin);
            enrollCourseController.show();
        });
        mainWin.courseForStu.setOnAction((actionEvent -> {
            CoursesForStuWin coursesForStuWin = new CoursesForStuWin("Courses For a Student");
            CourseForStuController courseForStuController = new CourseForStuController(service, coursesForStuWin);
            courseForStuController.show();
        }));
        mainWin.stuForCourse.setOnAction(actionEvent -> {
            StuForCourseWin stuForCourseWin = new StuForCourseWin("Students For a Course");
            StuForCourseController stuForCourseController = new StuForCourseController(service, stuForCourseWin);
            stuForCourseController.show();
        });
        mainWin.courseOnWeekDay.setOnAction((actionEvent -> {
            CoursesOnWDWin coursesOnWDWin = new CoursesOnWDWin("Course on Weekday");
            CoursesOnWDController coursesOnWDController = new CoursesOnWDController(service, coursesOnWDWin);
            coursesOnWDController.show();
        }));
        mainWin.showStu.setOnAction((actionEvent -> {
            addSQLResult(service.selectStu());
        }));
        mainWin.showCourse.setOnAction((actionEvent -> {
            addSQLResult(service.selectCourse());
        }));

    }

    protected Tab createSQLResultTableTab(SQLResultTable table) {
        TableView<Map<String, String>> tableView = new TableView<>();
        // add column, see https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
        for (String columnName : table.columns) {
            TableColumn<Map<String, String>, String> column = new TableColumn<>(columnName);
            column.setCellValueFactory(new MapValueFactory(columnName));
            tableView.getColumns().add(column);
        }
        // add data
        tableView.setItems(table.content);
        Tab tab = new Tab(table.name);
        tab.setContent(tableView);
        return tab;
    }

    public void addSQLResult(SQLResult res) {
        // set sql info
        mainWin.sqlInfoOutput.setText(res.info);
        // set tabs
        mainWin.sqlResTabPane.getTabs().clear();
        mainWin.sqlResTabPane.getTabs().add(mainWin.sqlInfoTab);
        for (SQLResultTable table : res.tables) {
            mainWin.sqlResTabPane.getTabs().add(createSQLResultTableTab(table));
        }
    }

    @Override
    public void show() {
        mainWin.show();
    }

    @Override
    public void close() {
        if(service != null) {
            service.close();
        }
        mainWin.close();
    }
}
