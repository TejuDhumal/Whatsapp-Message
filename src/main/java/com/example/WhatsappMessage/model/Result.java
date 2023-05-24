package com.example.WhatsappMessage.model;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

public class Result {

    private List<Data> data;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Result() {
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Result(List<Data> data) {
        this.data = data;
    }
}
