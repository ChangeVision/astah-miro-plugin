package com.change_vision.astah.lab.plugin.miro.widget;

public class MindmapInfo {
    private String theme;
    private String layout;
    private Boolean mindmap;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Boolean isMindmap() {
        return mindmap;
    }

    public void setMindmap(Boolean mindmap) {
        this.mindmap = mindmap;
    }

    @Override
    public String toString() {
        return "{" +
                "theme:'" + theme + '\'' +
                ", layout:'" + layout + '\'' +
                ", mindmap:" + mindmap +
                '}';
    }
}
