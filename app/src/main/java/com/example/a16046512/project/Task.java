package com.example.a16046512.project;

import java.io.Serializable;

public class Task implements Serializable{

    int id,time;
    String taskname,taskdescription;

    public Task(int id, String taskname, String taskdescription,int time) {
        this.id = id;
        this.taskname = taskname;
        this.taskdescription = taskdescription;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskdescription() {
        return taskdescription;
    }

    public void setTaskdescription(String taskdescription) {
        this.taskdescription = taskdescription;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
