package com.change_vision.astah.lab.plugin.miro.widget;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IWritableWidget extends IWidget {

    public String toJSON() throws JsonProcessingException;

    public String toPartialJSON();

}
