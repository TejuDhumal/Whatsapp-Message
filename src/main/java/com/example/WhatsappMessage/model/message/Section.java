package com.example.WhatsappMessage.model.message;

import com.example.WhatsappMessage.model.Data;

import java.util.List;

public class Section {
    public String title;
    public List<Row> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }


}