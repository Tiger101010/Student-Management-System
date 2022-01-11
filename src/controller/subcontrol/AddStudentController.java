package controller.subcontrol;

import controller.Controller;
import controller.ControllerManager;
import controller.MainController;
import model.service.ManagementService;
import view.subview.AddStudentWin;

public class AddStudentController implements Controller {
    private AddStudentWin win = null;
    private ManagementService service = null;

    public AddStudentController(ManagementService service, AddStudentWin win){
        this.win = win;
        this.service = service;
        setAction();
    }

    protected void setAction() {
        win.addBtn.setOnAction(actionEvent -> {
            String stuName = win.stuNameInput.getText();
            if(service != null) {
                service.addStu(stuName);
            }
            close();
            MainController mainController = (MainController) ControllerManager.controllerHashMap.get("main");
            mainController.addSQLResult(service.selectStu());
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
