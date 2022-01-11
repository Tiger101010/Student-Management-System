package controller.subcontrol;

import controller.Controller;
import controller.ControllerManager;
import controller.MainController;
import view.subview.CoursesForStuWin;
import model.service.ManagementService;

public class CourseForStuController implements Controller {
    private CoursesForStuWin win = null;
    private ManagementService service = null;

    public CourseForStuController(ManagementService service, CoursesForStuWin win) {
        this.win = win;
        this.service = service;
        setAction();
    }

    private void setAction() {
        win.searchBtn.setOnAction(actionEvent -> {
            String stuId = win.stuIdInput.getText();
            if(service != null) {
                MainController mainController = (MainController) ControllerManager.controllerHashMap.get("main");
                mainController.addSQLResult(service.selectEnrolledCoursesByStudentId(stuId));
            }
            close();
        });
        win.cancelBtn.setOnAction(actionEvent -> {
            close();
        });
    }

    @Override
    public void show() {
        win.show();
    }

    @Override
    public void close() {
        win.close();
    }
}
