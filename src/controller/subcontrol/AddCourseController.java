package controller.subcontrol;

import controller.Controller;
import controller.ControllerManager;
import controller.MainController;
import view.subview.AddCourseWin;
import model.service.ManagementService;

public class AddCourseController implements Controller {
    private AddCourseWin win = null;
    private ManagementService service = null;

    public AddCourseController(ManagementService service, AddCourseWin win){
        this.win = win;
        this.service = service;
        initChoiceBox();
        setAction();
    }

    protected void setAction() {
        win.addBtn.setOnAction(actionEvent -> {
            String courseName = win.courseNameInput.getText();
            String courseWeekday = win.weekDayChoice.getValue();
            String courseStart = win.courseStartChoice.getValue();
            String courseEnd = win.courseEndChoice.getValue();
            if(service != null) {
                service.addCourse(courseName, courseWeekday, courseStart, courseEnd);
            }
            close();
            MainController mainController = (MainController) ControllerManager.controllerHashMap.get("main");
            mainController.addSQLResult(service.selectCourse());
        });
        win.cancelBtn.setOnAction(actionEvent -> {
            close();
        });
    }

    protected void initChoiceBox() {
        String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri"};
        String[] workHours = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
                                "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"};
        for(String weekday : weekdays) {
            win.weekDayChoice.getItems().add(weekday);
        }
        for(String workHour : workHours){
            win.courseStartChoice.getItems().add(workHour);
            win.courseEndChoice.getItems().add(workHour);
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
