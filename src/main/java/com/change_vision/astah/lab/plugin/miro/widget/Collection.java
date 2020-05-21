package com.change_vision.astah.lab.plugin.miro.widget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collection {

    private String type;

    private List<IWidget> data;

    private long size;

    public Collection() {
        data = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<IWidget> getData() {
        return data;
    }

    public void setData(List<IWidget> data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
