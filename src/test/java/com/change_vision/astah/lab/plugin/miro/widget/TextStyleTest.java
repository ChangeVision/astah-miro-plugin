package com.change_vision.astah.lab.plugin.miro.widget;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TextStyleTest {

    @Test
    public void createInstance() {
        final TextStyle style = TextStyle.createInstance();
        assertThat(style.toString(), is("{" +
                "backgroundColor:'#ffffff'," +
                " backgroundOpacity:1.0," +
                " borderColor:'#ffffff'," +
                " borderOpacity:1.0," +
                " borderStyle:'normal'," +
                " borderWidth:2.0," +
                " fontFamily:'OpenSans'," +
                " fontSize:14," +
                " textAlign:'center'," +
                " textColor:'#1a1a1a'}"));
    }
}