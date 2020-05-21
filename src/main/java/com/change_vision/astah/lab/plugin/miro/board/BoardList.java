package com.change_vision.astah.lab.plugin.miro.board;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardList {
    private List<Board> data;

    public List<Board> getData() {
        return data;
    }
}
