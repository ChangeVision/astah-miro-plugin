package com.change_vision.astah.lab.plugin.miro.widget;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TextTest {

    @Test
    public void createNewInstance() {
        final Text text = Text.createNewInstance();
        assertThat(text.toString(), is("{" +
                "id:'null'" +
                ", type:'text'" +
                ", x:0.0" +
                ", y:0.0" +
                ", width:100.0" +
                ", rotation:0.0" +
                ", scale:null" +
                ", style:{" +
                "backgroundColor:'#ffffff'" +
                ", backgroundOpacity:1.0" +
                ", borderColor:'#ffffff'" +
                ", borderOpacity:1.0" +
                ", borderStyle:'normal'" +
                ", borderWidth:2.0" +
                ", fontFamily:'OpenSans'" +
                ", fontSize:14" +
                ", textAlign:'center'" +
                ", textColor:'#1a1a1a'" +
                "}" +
                ", text:''" +
                ", mindmap:null" +
                "}"));
    }

    @Test
    public void toJSON() throws JSONException {

        final Text text = Text.createNewInstance();
        text.setText("text");
        final String expected = "{" +
                "type:\"text\"," +
                "x:0.0," +
                "y:0.0," +
                "width:100.0," +
                "rotation:0.0," +
                "style:{" +
                "backgroundColor:\"#ffffff\"," +
                "backgroundOpacity:1.0," +
                "borderColor:\"#ffffff\"," +
                "borderOpacity:1.0," +
                "borderStyle:\"normal\"," +
                "borderWidth:2.0," +
                "fontFamily:\"OpenSans\"," +
                "fontSize:14," +
                "textAlign:\"center\"," +
                "textColor:\"#1a1a1a\"" +
                "}," +
                "text:\"text\"" +
                "}";
        JSONAssert.assertEquals(expected, text.toJSON(), JSONCompareMode.STRICT);
    }

    @Test
    public void toPartialJSON() throws JSONException {
        final Text text = Text.createNewInstance();
        final String expected = "{" +
                "x:0.0," +
                "y:0.0," +
                "width:100.0," +
                "rotation:0.0," +
                "style:{" +
                "backgroundColor:\"#ffffff\"," +
                "backgroundOpacity:1.0," +
                "borderColor:\"#ffffff\"," +
                "borderOpacity:1.0," +
                "borderStyle:\"normal\"," +
                "borderWidth:2.0," +
                "fontFamily:\"OpenSans\"," +
                "fontSize:14," +
                "textAlign:\"center\"," +
                "textColor:\"#1a1a1a\"" +
                "}," +
                "text:\"\"" +
                "}";
        JSONAssert.assertEquals(expected, text.toPartialJSON(), JSONCompareMode.STRICT);
    }

    @Test
    public void isMindmap_true() {
        final Text text = Text.createNewInstance();
        final MindmapInfo mindmap = new MindmapInfo();
        mindmap.setTheme("colorBranch");
        mindmap.setLayout("butterfly");
        text.setMindmap(mindmap);
        assertThat(text.isMindmap(), is(true));
    }

    @Test
    public void isMindmap_false() {
        final Text text = Text.createNewInstance();
        assertThat(text.isMindmap(), is(false));
    }

    @Test
    public void getName() {
        final Text text = Text.createNewInstance();
        text.setText("<p>Text</p>");
        assertThat(text.getName(), is("Text"));
    }

    @Test
    public void getName_bold() {
        final Text text = Text.createNewInstance();
        text.setText("<p><strong>Bold</strong></p>");
        assertThat(text.getName(), is("Bold"));
    }

    @Test
    public void getName_italic() {
        final Text text = Text.createNewInstance();
        text.setText("<i>Italic</i>");
        assertThat(text.getName(), is("Italic"));
    }

    @Test
    public void getName_underline() {
        final Text text = Text.createNewInstance();
        text.setText("<p><u>Underline</u></p>");
        assertThat(text.getName(), is("Underline"));
    }

}
