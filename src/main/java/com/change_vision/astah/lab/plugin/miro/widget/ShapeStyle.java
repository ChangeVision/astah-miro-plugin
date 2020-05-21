package com.change_vision.astah.lab.plugin.miro.widget;

public class ShapeStyle {

    private String backgroundColor;

    private float backgroundOpacity;

    private String borderColor;

    private float borderOpacity;

    private String borderStyle;

    private float borderWidth;

    private String fontFamily;

    private long fontSize;

    private String shapeType;

    private String textAlign;

    private String textAlignVertical;

    private String textColor;

    public static ShapeStyle createNewInstance() {
        ShapeStyle style = new ShapeStyle();
        style.backgroundColor = "#ffffff";
        style.backgroundOpacity = 1.0f;
        style.borderColor = "#1a1a1a";
        style.borderOpacity = 1.0f;
        style.borderStyle = "normal";
        style.borderWidth = 2.0f;
        style.fontFamily = "OpenSans";
        style.fontSize = 14;
        style.shapeType = "rectangle";
        style.textAlign = "center";
        style.textAlignVertical = "middle";
        style.textColor = "#1a1a1a";
        return style;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public float getBackgroundOpacity() {
        return backgroundOpacity;
    }

    public void setBackgroundOpacity(float backgroundOpacity) {
        this.backgroundOpacity = backgroundOpacity;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public float getBorderOpacity() {
        return borderOpacity;
    }

    public void setBorderOpacity(float borderOpacity) {
        this.borderOpacity = borderOpacity;
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

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public long getFontSize() {
        return fontSize;
    }

    public String getShapeType() {
        return shapeType;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public void setFontSize(long fontSize) {
        this.fontSize = fontSize;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public String getTextAlignVertical() {
        return textAlignVertical;
    }

    public void setTextAlignVertical(String textAlignVertical) {
        this.textAlignVertical = textAlignVertical;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    @Override
    public String toString() {
        return "{" +
                "backgroundColor:'" + backgroundColor + '\'' +
                ", backgroundOpacity:" + backgroundOpacity +
                ", borderColor:'" + borderColor + '\'' +
                ", borderOpacity:" + borderOpacity +
                ", borderStyle:'" + borderStyle + '\'' +
                ", borderWidth:" + borderWidth +
                ", fontFamily:'" + fontFamily + '\'' +
                ", fontSize:" + fontSize +
                ", shapeType:'" + shapeType + '\'' +
                ", textAlign:'" + textAlign + '\'' +
                ", textAlignVertical:'" + textAlignVertical + '\'' +
                ", textColor:'" + textColor + '\'' +
                '}';
    }
}
