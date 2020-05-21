package com.change_vision.astah.lab.plugin.miro.widget;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LineTest {

    @Test
    public void toJSON() {
        final Shape client = Shape.createNewInstance();
        client.setId("0");
        final Shape supplier = Shape.createNewInstance();
        supplier.setId("1");
        final Line line = Line.createDependency(client, supplier);
        line.setId("0");
        final String expected = "{" +
                "\"type\":\"line\"," +
                "\"startWidget\":{\"id\":\"0\"}," +
                "\"endWidget\":{\"id\":\"1\"}," +
                "\"style\":{" +
                "\"borderColor\":\"#000000\"," +
                "\"borderStyle\":\"dashed\"," +
                "\"borderWidth\":1.0," +
                "\"lineEndType\":\"open_arrow\"," +
                "\"lineStartType\":\"none\"," +
                "\"lineType\":\"straight\"}" +
                "}";
        assertThat(line.toJSON(), is(expected));
    }

    @Test
    public void toPartialJSON() {
        final Shape client = Shape.createNewInstance();
        client.setId("0");
        final Shape supplier = Shape.createNewInstance();
        supplier.setId("1");
        final Line line = Line.createDependency(client, supplier);
        line.setId("0");
        final String expected = "{" +
                "\"style\":{" +
                "\"borderColor\":\"#000000\"," +
                "\"borderStyle\":\"dashed\"," +
                "\"borderWidth\":1.0," +
                "\"lineEndType\":\"open_arrow\"," +
                "\"lineStartType\":\"none\"," +
                "\"lineType\":\"straight\"}" +
                "}";
        assertThat(line.toPartialJSON(), is(expected));
    }

    @Test
    public void isMindmap_true() {
        final Line line = new Line();
        final MindmapInfo mindmap = new MindmapInfo();
        mindmap.setMindmap(true);
        line.setMindmap(mindmap);
        assertThat(line.isMindmap(), is(true));
    }

    @Test
    public void isMindmap_false() {
        final Line line = new Line();
        assertThat(line.isMindmap(), is(false));
    }
}