package com.change_vision.astah.lab.plugin.miro.widget;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IWidgetTest {

    @Test
    public void deserializeShape() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String json = "{\n" +
                "  \"type\" : \"shape\",\n" +
                "  \"x\" : 267.5,\n" +
                "  \"y\" : 228.5,\n" +
                "  \"height\" : 85.0,\n" +
                "  \"width\" : 89.0,\n" +
                "  \"rotation\" : 0.0,\n" +
                "  \"text\" : \"Item\",\n" +
                "  \"id\" : \"3074457347692227180\",\n" +
                "  \"style\" : {\n" +
                "    \"backgroundColor\" : \"#ffffff\",\n" +
                "    \"backgroundOpacity\" : 1.0,\n" +
                "    \"borderColor\" : \"#1a1a1a\",\n" +
                "    \"borderOpacity\" : 1.0,\n" +
                "    \"borderStyle\" : \"normal\",\n" +
                "    \"borderWidth\" : 2.0,\n" +
                "    \"fontFamily\" : \"OpenSans\",\n" +
                "    \"fontSize\" : 14,\n" +
                "    \"shapeType\" : \"rectangle\",\n" +
                "    \"textAlign\" : \"center\",\n" +
                "    \"textAlignVertical\" : \"middle\",\n" +
                "    \"textColor\" : \"#1a1a1a\"\n" +
                "  },\n" +
                "  \"modifiedAt\" : \"2020-04-22T03:20:29Z\",\n" +
                "  \"modifiedBy\" : {\n" +
                "    \"type\" : \"user\",\n" +
                "    \"name\" : \"山田太郎\",\n" +
                "    \"id\" : \"3074457347500810429\"\n" +
                "  },\n" +
                "  \"createdAt\" : \"2020-04-22T03:20:29Z\",\n" +
                "  \"createdBy\" : {\n" +
                "    \"type\" : \"user\",\n" +
                "    \"name\" : \"山田太郎\",\n" +
                "    \"id\" : \"3074457347500810429\"\n" +
                "  },\n" +
                "  \"capabilities\" : {\n" +
                "    \"editable\" : true\n" +
                "  }\n" +
                "}";

        final Shape shape = mapper.readValue(json, Shape.class);
        assertThat(shape.toString(), is("{" +
                "id:'3074457347692227180'," +
                " type:'shape'," +
                " text:`Item'," +
                " x:267.5," +
                " y:228.5," +
                " rotation:0.0," +
                " width:89.0," +
                " height:85.0," +
                " style:{" +
                "backgroundColor:'#ffffff'," +
                " backgroundOpacity:1.0," +
                " borderColor:'#1a1a1a'," +
                " borderOpacity:1.0," +
                " borderStyle:'normal'," +
                " borderWidth:2.0," +
                " fontFamily:'OpenSans'," +
                " fontSize:14," +
                " shapeType:'rectangle'," +
                " textAlign:'center'," +
                " textAlignVertical:'middle'," +
                " textColor:'#1a1a1a'}" +
                "}"));
    }

    @Test
    public void deserializeLine() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String json = "{\n" +
                "  \"type\" : \"line\",\n" +
                "  \"startWidget\" : {\n" +
                "    \"id\" : \"3074457347690020214\"\n" +
                "  },\n" +
                "  \"endWidget\" : {\n" +
                "    \"id\" : \"3074457347689908354\"\n" +
                "  },\n" +
                "  \"style\" : {\n" +
                "    \"borderColor\" : \"#000000\",\n" +
                "    \"borderStyle\" : \"normal\",\n" +
                "    \"borderWidth\" : 1.0,\n" +
                "    \"lineEndType\" : \"block\",\n" +
                "    \"lineStartType\" : \"none\",\n" +
                "    \"lineType\" : \"bezier\"\n" +
                "  },\n" +
                "  \"modifiedAt\" : \"2020-04-22T03:01:56Z\",\n" +
                "  \"modifiedBy\" : {\n" +
                "    \"type\" : \"user\",\n" +
                "    \"name\" : \"山田太郎\",\n" +
                "    \"id\" : \"3074457347500810429\"\n" +
                "  },\n" +
                "  \"id\" : \"3074457347692388984\",\n" +
                "  \"createdAt\" : \"2020-04-22T03:01:53Z\",\n" +
                "  \"createdBy\" : {\n" +
                "    \"type\" : \"user\",\n" +
                "    \"name\" : \"山田太郎\",\n" +
                "    \"id\" : \"3074457347500810429\"\n" +
                "  }\n" +
                "}";

        final Line line = mapper.readValue(json, Line.class);
        assertThat(line.toString(), is("{" +
                "id:'3074457347692388984'," +
                " type:'line'," +
                " startWidget:{id:'3074457347690020214'}," +
                " endWidget:{id:'3074457347689908354'}," +
                " style:{" +
                "borderColor:'#000000'," +
                " borderStyle:'normal'," +
                " borderWidth:1.0," +
                " lineEndType:'block'," +
                " lineStartType:'none'," +
                " lineType:'bezier'" +
                "}," +
                " mindmap:null" +
                "}"));
    }

    @Test
    public void deserializeMindmapLine() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final String json = "{\n" +
                "    \"type\" : \"line\",\n" +
                "    \"startWidget\" : {\n" +
                "      \"id\" : \"3074457347692986314\"\n" +
                "    },\n" +
                "    \"endWidget\" : {\n" +
                "      \"id\" : \"3074457347692986532\"\n" +
                "    },\n" +
                "    \"style\" : {\n" +
                "      \"borderColor\" : \"#7a28ff\",\n" +
                "      \"borderStyle\" : \"normal\",\n" +
                "      \"borderWidth\" : 2.0,\n" +
                "      \"lineEndType\" : \"none\",\n" +
                "      \"lineStartType\" : \"none\",\n" +
                "      \"lineType\" : \"mind_map_bezier\"\n" +
                "    },\n" +
                "    \"modifiedAt\" : \"2020-04-22T05:31:22Z\",\n" +
                "    \"modifiedBy\" : {\n" +
                "      \"type\" : \"user\",\n" +
                "      \"name\" : \"齋五澤宏知\",\n" +
                "      \"id\" : \"3074457347500810429\"\n" +
                "    },\n" +
                "    \"id\" : \"3074457347692986533\",\n" +
                "    \"createdAt\" : \"2020-04-22T05:31:19Z\",\n" +
                "    \"createdBy\" : {\n" +
                "      \"type\" : \"user\",\n" +
                "      \"name\" : \"齋五澤宏知\",\n" +
                "      \"id\" : \"3074457347500810429\"\n" +
                "    },\n" +
                "    \"mindmap\" : {\n" +
                "      \"mindmap\" : true\n" +
                "    }\n" +
                "  }";

        final Line line = mapper.readValue(json, Line.class);
        assertThat(line.toString(), is("{" +
                "id:'3074457347692986533'," +
                " type:'line'," +
                " startWidget:{id:'3074457347692986314'}," +
                " endWidget:{id:'3074457347692986532'}," +
                " style:{" +
                "borderColor:'#7a28ff'," +
                " borderStyle:'normal'," +
                " borderWidth:2.0," +
                " lineEndType:'none'," +
                " lineStartType:'none'," +
                " lineType:'mind_map_bezier'" +
                "}," +
                " mindmap:{" +
                "theme:'null'," +
                " layout:'null'," +
                " mindmap:true" +
                "}" +
                "}"));
    }

    @Test
    public void deserializeText() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        final String json = "{\n" +
                "  \"type\" : \"text\",\n" +
                "  \"y\" : -113.0,\n" +
                "  \"x\" : -175.745,\n" +
                "  \"rotation\" : 0.0,\n" +
                "  \"scale\" : 1.0,\n" +
                "  \"width\" : 140.06,\n" +
                "  \"style\" : {\n" +
                "    \"backgroundColor\" : \"#ffffffff\",\n" +
                "    \"backgroundOpacity\" : 1.0,\n" +
                "    \"borderColor\" : \"#7a28ff\",\n" +
                "    \"borderOpacity\" : 1.0,\n" +
                "    \"borderStyle\" : \"normal\",\n" +
                "    \"borderWidth\" : 2.0,\n" +
                "    \"fontFamily\" : \"OpenSans\",\n" +
                "    \"fontSize\" : 14,\n" +
                "    \"textAlign\" : \"center\",\n" +
                "    \"textColor\" : \"#1a1a1a\"\n" +
                "  },\n" +
                "  \"text\" : \"<p>Miro Mind Map</p>\",\n" +
                "  \"id\" : \"3074457347667121321\",\n" +
                "  \"modifiedAt\" : \"2020-04-20T10:42:42Z\",\n" +
                "  \"modifiedBy\" : {\n" +
                "    \"type\" : \"user\",\n" +
                "    \"name\" : \"山田太郎\",\n" +
                "    \"id\" : \"3074457347500810429\"\n" +
                "  },\n" +
                "  \"createdAt\" : \"2020-04-20T10:42:39Z\",\n" +
                "  \"createdBy\" : {\n" +
                "    \"type\" : \"user\",\n" +
                "    \"name\" : \"山田太郎\",\n" +
                "    \"id\" : \"3074457347500810429\"\n" +
                "  }\n" +
                "  }\n" +
                "}";

        final Text text = mapper.readValue(json, Text.class);
        assertThat(text.toString(), is("{" +
                "id:'3074457347667121321', " +
                "type:'text', " +
                "x:-175.745, " +
                "y:-113.0, " +
                "width:140.06, " +
                "rotation:0.0, " +
                "scale:1.0, " +
                "style:{" +
                "backgroundColor:'#ffffffff', " +
                "backgroundOpacity:1.0, " +
                "borderColor:'#7a28ff', " +
                "borderOpacity:1.0, " +
                "borderStyle:'normal', " +
                "borderWidth:2.0, " +
                "fontFamily:'OpenSans', " +
                "fontSize:14, " +
                "textAlign:'center', " +
                "textColor:'#1a1a1a'" +
                "}, " +
                "text:'<p>Miro Mind Map</p>'," +
                " mindmap:null" +
                "}"));
    }

    @Test
    public void deserializeMindmapText() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        final String json = "{\n" +
                "    \"type\" : \"text\",\n" +
                "    \"x\" : 107.59004322436311,\n" +
                "    \"y\" : 73.80361274388864,\n" +
                "    \"width\" : 8.459999999999999,\n" +
                "    \"style\" : {\n" +
                "      \"backgroundColor\" : \"#ffffffff\",\n" +
                "      \"backgroundOpacity\" : 1.0,\n" +
                "      \"borderColor\" : \"#ffffffff\",\n" +
                "      \"borderOpacity\" : 1.0,\n" +
                "      \"borderStyle\" : \"normal\",\n" +
                "      \"borderWidth\" : 2.0,\n" +
                "      \"fontFamily\" : \"OpenSans\",\n" +
                "      \"fontSize\" : 14,\n" +
                "      \"textAlign\" : \"right\",\n" +
                "      \"textColor\" : \"#1a1a1a\"\n" +
                "    },\n" +
                "    \"rotation\" : 0.0,\n" +
                "    \"scale\" : 1.0,\n" +
                "    \"text\" : \"<p>b</p>\",\n" +
                "    \"id\" : \"3074457347692986319\",\n" +
                "    \"modifiedAt\" : \"2020-04-22T05:41:08Z\",\n" +
                "    \"modifiedBy\" : {\n" +
                "      \"type\" : \"user\",\n" +
                "      \"name\" : \"齋五澤宏知\",\n" +
                "      \"id\" : \"3074457347500810429\"\n" +
                "    },\n" +
                "    \"createdAt\" : \"2020-04-22T05:30:42Z\",\n" +
                "    \"createdBy\" : {\n" +
                "      \"type\" : \"user\",\n" +
                "      \"name\" : \"齋五澤宏知\",\n" +
                "      \"id\" : \"3074457347500810429\"\n" +
                "    },\n" +
                "    \"mindmap\" : {\n" +
                "      \"theme\" : \"colorBranch\",\n" +
                "      \"layout\" : \"butterfly\"\n" +
                "    }\n" +
                "  }";

        final Text text = mapper.readValue(json, Text.class);
        assertThat(text.toString(), is("{" +
                "id:'3074457347692986319'," +
                " type:'text'," +
                " x:107.59004," +
                " y:73.80361," +
                " width:8.46," +
                " rotation:0.0," +
                " scale:1.0," +
                " style:{" +
                "backgroundColor:'#ffffffff'," +
                " backgroundOpacity:1.0," +
                " borderColor:'#ffffffff'," +
                " borderOpacity:1.0," +
                " borderStyle:'normal'," +
                " borderWidth:2.0," +
                " fontFamily:'OpenSans'," +
                " fontSize:14," +
                " textAlign:'right'," +
                " textColor:'#1a1a1a'}," +
                " text:'<p>b</p>'," +
                " mindmap:{" +
                "theme:'colorBranch'," +
                " layout:'butterfly'," +
                " mindmap:null" +
                "}" +
                "}"));
    }
}