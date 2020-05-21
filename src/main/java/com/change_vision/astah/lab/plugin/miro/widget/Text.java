package com.change_vision.astah.lab.plugin.miro.widget;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@JsonFilter("textFilter")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Text extends WidgetBase implements IWritableWidget {

    private Float x;

    private Float y;

    private Float width;

    private Float rotation;

    private Float scale;

    private TextStyle style;

    private String text;

    private MindmapInfo mindmap;

    public Text() {
        super();
        type = "text";
    }

    public static Text createNewInstance() {
        final Text text = new Text();
        text.x = 0.0f;
        text.y = 0.0f;
        text.width = 100.0f;
        text.rotation = 0.0f;
        text.scale = null;
        text.style = TextStyle.createInstance();
        text.text = "";
        return text;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getRotation() {
        return rotation;
    }

    public void setRotation(Float rotation) {
        this.rotation = rotation;
    }

    public Float getScale() {
        return scale;
    }

    public void setScale(Float scale) {
        this.scale = scale;
    }

    public TextStyle getStyle() {
        return style;
    }

    public void setStyle(TextStyle style) {
        this.style = style;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MindmapInfo getMindmap() {
        return mindmap;
    }

    public void setMindmap(MindmapInfo mindmap) {
        this.mindmap = mindmap;
    }

    public String getName() {
        return getText().replaceAll("<[a-zA-Z0-9 /]+(=\".*?)?>", "");
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", type:'" + type + '\'' +
                ", x:" + x +
                ", y:" + y +
                ", width:" + width +
                ", rotation:" + rotation +
                ", scale:" + scale +
                ", style:" + style +
                ", text:'" + text + '\'' +
                ", mindmap:" + mindmap +
                '}';
    }

    @Override
    public String toJSON() {
        try {
            final SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("textFilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                    "type", "x", "y", "width", "rotation", "scale", "style", "text"
            ));
            final ObjectMapper mapper = new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .setFilterProvider(filterProvider);
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String toPartialJSON() {
        try {
            final SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("textFilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                    "x", "y", "width", "rotation", "scale", "style", "text"
            ));
            final ObjectMapper mapper = new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .setFilterProvider(filterProvider);
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    public boolean isMindmap() {
        return mindmap != null;
    }
}
