package com.example.splashscreen;

public class Model {
    String TaskDate;
    String Description;
    String ltask;
    String Priority;

    public String getPriority() {
        return Priority;
    }

    public String getLtask() {
        return ltask;
    }


    public String isCheckboxStatus() {
        return checkboxStatus;
    }

    String TaskName;
    String Time;
    String checkboxStatus;
    public String getTaskName() {
        return TaskName;
    }
    public String getDescription() {
        return Description;
    }
    public String getTaskDate() {
        return TaskDate;
    }
    public String getTime() {
        return Time;
    }




    public Model(){

    }


}
