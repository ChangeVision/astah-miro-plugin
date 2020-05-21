package com.change_vision.astah.lab.plugin.miro.widget;

public class LineStyle {

    private String borderColor;

    // allowed values: "normal", "dashed", "dotted"
    private String borderStyle;

    // allowed values: 1.0, 2.0, 3.0, 4.0, 5.0, 8.0, 12.0, 16.0, 20.0, 24.0
    private float borderWidth;

    // allowed values: "none", "opaque_block", "rhombus", "opaque_rhombus",
    // "circle", "opaque_circle", "block", "open_arrow", "opaque_arrow"
    private String lineEndType;
    private String lineStartType;

    // allowed values: "straight", "orthogonal", "bezier", "sketch"
    private String lineType;

    public static LineStyle createNewInstance() {
        LineStyle style = new LineStyle();
        style.borderColor = "#000000";
        style.borderStyle = "normal";
        style.borderWidth = 1.0f;
        style.lineEndType = "none";
        style.lineStartType = "none";
        style.lineType = "straight";
        return style;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getBorderStyle() {
        return borderStyle;
    }

    public void setBorderStyle(String borderStyle) {
        this.borderStyle = borderStyle;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getLineEndType() {
        return lineEndType;
    }

    public void setLineEndType(String lineEndType) {
        this.lineEndType = lineEndType;
    }

    public String getLineStartType() {
        return lineStartType;
    }

    public void setLineStartType(String lineStartType) {
        this.lineStartType = lineStartType;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    @Override
    public String toString() {
        return "{" +
                "borderColor:'" + borderColor + '\'' +
                ", borderStyle:'" + borderStyle + '\'' +
                ", borderWidth:" + borderWidth +
                ", lineEndType:'" + lineEndType + '\'' +
                ", lineStartType:'" + lineStartType + '\'' +
                ", lineType:'" + lineType + '\'' +
                '}';
    }
}
