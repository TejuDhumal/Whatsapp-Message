package com.example.WhatsappMessage.model;

import org.apache.catalina.LifecycleState;

import java.util.List;

public class ResultEmployee {
    public List<DataEmployee> data;

    public int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataEmployee> getData() {
        return data;
    }

    public void setData(List<DataEmployee> data) {
        this.data = data;
    }

    public ResultEmployee() {
    }

    public ResultEmployee(List<DataEmployee> data) {
        this.data = data;
    }
}
