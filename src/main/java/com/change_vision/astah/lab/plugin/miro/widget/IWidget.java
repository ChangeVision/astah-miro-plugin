package com.change_vision.astah.lab.plugin.miro.widget;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Shape.class, name = "shape"),
        @JsonSubTypes.Type(value = Line.class, name = "line"),
        @JsonSubTypes.Type(value = Text.class, name = "text")
})
public interface IWidget {

    String getId();

    String getType();
}
