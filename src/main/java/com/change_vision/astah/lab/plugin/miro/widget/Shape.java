package com.change_vision.astah.lab.plugin.miro.widget;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.awt.geom.Point2D;

@JsonFilter("shapeFilter")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shape extends WidgetBase implements IWritableWidget {

    private float x;

    private float y;

    private float rotation;

    private float width;

    private float height;

    private ShapeStyle style;

    private String text;

    public Shape() {
        super();
        type = "shape";
    }

    public static Shape createNewInstance() {
        Shape shape = new Shape();
        shape.x = 0.0f;
        shape.y = 0.0f;
        shape.rotation = 0.0f;
        shape.width = 100.0f;
        shape.height = 131.0f;
        shape.style = ShapeStyle.createNewInstance();
        return shape;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public ShapeStyle getStyle() {
        return style;
    }

    public void setStyle(ShapeStyle style) {
        this.style = style;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Point2D getLocation() {
        return new Point2D.Float(
                getX() - getWidth() / 2,
                getY() - getHeight() / 2);
    }

    public String getName() {
        return getText().replaceAll("<[a-zA-Z0-9 /]+(=\".*?)?>", "");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("id:'" + this.id + '\'');
        sb.append(", type:'" + this.type + '\'');
        sb.append(", text:`" + this.text + '\'');
        sb.append(", x:" + this.x);
        sb.append(", y:" + this.y);
        sb.append(", rotation:" + this.rotation);
        sb.append(", width:" + this.width);
        sb.append(", height:" + this.height);
        sb.append(", style:" + this.style);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toJSON() {
        try {
            final SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("shapeFilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                    "type", "x", "y", "rotation", "width", "height", "style", "text"
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
            filterProvider.addFilter("shapeFilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                    "x", "y", "rotation", "width", "height", "style", "text"
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
}
