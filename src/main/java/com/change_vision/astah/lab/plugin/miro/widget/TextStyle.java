package com.change_vision.astah.lab.plugin.miro.widget;

public class TextStyle {

    private String backgroundColor;

    private Float backgroundOpacity;

    private String borderColor;

    private Float borderOpacity;

    private String borderStyle;

    private Float borderWidth;

    private String fontFamily;

    private Long fontSize;

    private String textAlign;

    private String textColor;

    public static TextStyle createInstance() {
        final TextStyle style = new TextStyle();
        style.backgroundColor = "#ffffff";
        style.backgroundOpacity = 1.0f;
        style.borderColor = "#ffffff";
        style.borderOpacity = 1.0f;
        style.borderStyle = "normal";
        style.borderWidth = 2.0f;
        style.fontFamily = "OpenSans";
        style.fontSize = 14L;
        style.textAlign = "center";
        style.textColor = "#1a1a1a";
        return style;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Float getBackgroundOpacity() {
        return backgroundOpacity;
    }

    public void setBackgroundOpacity(Float backgroundOpacity) {
        this.backgroundOpacity = backgroundOpacity;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public Float getBorderOpacity() {
        return borderOpacity;
    }

    public void setBorderOpacity(Float borderOpacity) {
        this.borderOpacity = borderOpacity;
    }

    public String getBorderStyle() {
        return borderStyle;
    }

    public void setBorderStyle(String borderStyle) {
        this.borderStyle = borderStyle;
    }

    public Float getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public Long getFontSize() {
        return fontSize;
    }

    public void setFontSize(Long fontSize) {
        this.fontSize = fontSize;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
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
                ", textAlign:'" + textAlign + '\'' +
                ", textColor:'" + textColor + '\'' +
                '}';
    }
}
