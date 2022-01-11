package controller.subcontrol;

import controller.Controller;
import controller.ControllerManager;
import controller.MainController;
import view.subview.EnrollCourseWin;
import model.service.ManagementService;

public class EnrollCourseController implements Controller {
    private EnrollCourseWin win = null;
    private ManagementService service = null;

    public EnrollCourseController(ManagementService service, EnrollCourseWin win) {
        this.win = win;
        this.service = service;
        setAction();
    }

    private void setAction() {
        win.addBtn.setOnAction(actionEvent -> {
            String stuId = win.stuIdInput.getText();
            String courseId = win.courseIdInput.getText();
            if(service != null) {
                service.enrollInCourse(stuId, courseId);
            }
            close();
            MainController mainController = (MainController) ControllerManager.controllerHashMap.get("main");
            mainController.addSQLResult(service.selectStuCourse());
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
