package controller.subcontrol;

import controller.Controller;
import controller.ControllerManager;
import controller.MainController;
import view.subview.CoursesOnWDWin;
import model.service.ManagementService;

public class CoursesOnWDController implements Controller {
    private CoursesOnWDWin win = null;
    private ManagementService service = null;

    public CoursesOnWDController(ManagementService service, CoursesOnWDWin win) {
        this.service = service;
        this.win = win;
        initChoiceBox();
        setAction();
    }

    private void setAction() {
        win.addBtn.setOnAction(actionEvent -> {
            String stuId = win.stuIdInput.getText();
            String courseWeekday = win.weekDayChoice.getValue();
            if(service != null) {
                MainController mainController = (MainController) ControllerManager.controllerHashMap.get("main");
                mainController.addSQLResult(service.selectCourseByStuNWeekday(stuId, courseWeekday));
            }
            close();
        });
        win.cancelBtn.setOnAction(actionEvent -> {
            close();
        });
    }

    private void initChoiceBox(){
        String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri"};
        for(String weekday : weekdays) {
            win.weekDayChoice.getItems().add(weekday);
        }
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
