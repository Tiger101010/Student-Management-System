package controller.subcontrol;

import controller.Controller;
import controller.ControllerManager;
import controller.MainController;
import view.subview.StuForCourseWin;
import model.service.ManagementService;

public class StuForCourseController implements Controller {
    private StuForCourseWin win = null;
    private ManagementService service = null;

    public StuForCourseController(ManagementService service, StuForCourseWin win) {
        this.win = win;
        this.service = service;
        setAction();
    }

    private void setAction() {
        win.searchBtn.setOnAction(actionEvent -> {
            String courseId = win.courseIdInput.getText();
            if(service != null) {
                MainController mainController = (MainController) ControllerManager.controllerHashMap.get("main");
                mainController.addSQLResult(service.selectEnrolledStudentsByCourseId(courseId));
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
