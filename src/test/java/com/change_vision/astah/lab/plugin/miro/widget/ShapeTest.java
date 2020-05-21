package com.change_vision.astah.lab.plugin.miro.widget;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShapeTest {

    @Test
    public void toJSON() {
        final Shape shape = Shape.createNewInstance();
        shape.setId("0");
        final String expected = "{" +
                "\"type\":\"shape\"," +
                "\"x\":0.0," +
                "\"y\":0.0," +
                "\"rotation\":0.0," +
                "\"width\":100.0," +
                "\"height\":131.0," +
                "\"style\":{" +
                "\"backgroundColor\":\"#ffffff\"," +
                "\"backgroundOpacity\":1.0," +
                "\"borderColor\":\"#1a1a1a\"," +
                "\"borderOpacity\":1.0," +
                "\"borderStyle\":\"normal\"," +
                "\"borderWidth\":2.0," +
                "\"fontFamily\":\"OpenSans\"," +
                "\"fontSize\":14," +
                "\"shapeType\":\"rectangle\"," +
                "\"textAlign\":\"center\"," +
                "\"textAlignVertical\":\"middle\"," +
                "\"textColor\":\"#1a1a1a\"}" +
                "}";
        assertThat(shape.toJSON(), is(expected));
    }

    @Test
    public void toPartialJSON() {
        final Shape shape = Shape.createNewInstance();
        shape.setId("0");
        final String expected = "{" +
                "\"x\":0.0," +
                "\"y\":0.0," +
                "\"rotation\":0.0," +
                "\"width\":100.0," +
                "\"height\":131.0," +
                "\"style\":{" +
                "\"backgroundColor\":\"#ffffff\"," +
                "\"backgroundOpacity\":1.0," +
                "\"borderColor\":\"#1a1a1a\"," +
                "\"borderOpacity\":1.0," +
                "\"borderStyle\":\"normal\"," +
                "\"borderWidth\":2.0," +
                "\"fontFamily\":\"OpenSans\"," +
                "\"fontSize\":14," +
                "\"shapeType\":\"rectangle\"," +
                "\"textAlign\":\"center\"," +
                "\"textAlignVertical\":\"middle\"," +
                "\"textColor\":\"#1a1a1a\"}" +
                "}";
        assertThat(shape.toPartialJSON(), is(expected));
    }
}