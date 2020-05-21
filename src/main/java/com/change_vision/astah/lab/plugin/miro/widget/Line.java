package com.change_vision.astah.lab.plugin.miro.widget;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@JsonFilter("lineFilter")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Line extends WidgetBase implements IWritableWidget {

    @JsonSerialize(as = Id.class)
    private Id startWidget;

    @JsonSerialize(as = Id.class)
    private Id endWidget;

    private LineStyle style;

    private MindmapInfo mindmap;

    public Line() {
        super();
        type = "line";
    }

    public Line(final Shape source, final Shape target) {
        this();
        type = "line";
        style = LineStyle.createNewInstance();
        startWidget = source;
        endWidget = target;
    }

    public static Line createAssociation(final Shape source, final Shape target,
                                         final String sourceEndType,
                                         final String targetEndType) {
        final Line line = new Line(source, target);
        line.style.setLineStartType(sourceEndType);
        line.style.setLineEndType(targetEndType);
        return line;
    }

    public static Line createGeneralization(final Shape specific, final Shape general) {
        final Line line = new Line(specific, general);
        line.style.setLineEndType("block");
        line.style.setLineType("orthogonal");
        return line;
    }

    public static Line createDependency(final Shape client, final Shape supplier) {
        final Line line = new Line(client, supplier);
        line.style.setLineEndType("open_arrow");
        line.style.setBorderStyle("dashed");
        return line;
    }

    public static Line createUsage(final Shape client, final Shape supplier) {
        return createDependency(client, supplier);
    }

    public static Line createRealization(final Shape client, final Shape supplier) {
        final Line line = new Line(client, supplier);
        line.style.setLineEndType("block");
        line.style.setLineType("orthogonal");
        line.style.setBorderStyle("dashed");
        return line;
    }

    public Id getStartWidget() {
        return startWidget;
    }

    public void setStartWidget(Id startWidget) {
        this.startWidget = startWidget;
    }

    public Id getEndWidget() {
        return endWidget;
    }

    public void setEndWidget(Id endWidget) {
        this.endWidget = endWidget;
    }

    public LineStyle getStyle() {
        return style;
    }

    public void setStyle(LineStyle style) {
        this.style = style;
    }

    public MindmapInfo getMindmap() {
        return mindmap;
    }

    public void setMindmap(MindmapInfo mindmap) {
        this.mindmap = mindmap;
    }

    public boolean sourceEndTypeIsAggregate() {
        return getStyle().getLineStartType().equals("rhombus");
    }

    public boolean sourceEndTypeIsComposite() {
        return getStyle().getLineStartType().equals("opaque_rhombus");
    }

    public boolean sourceEndTypeIsNavigable() {
        return getStyle().getLineStartType().equals("open_arrow");
    }

    public boolean sourceEndTypeIsNone() {
        return getStyle().getLineStartType().equals("none");
    }

    public boolean targetEndTypeIsAggregate() {
        return getStyle().getLineEndType().equals("rhombus");
    }

    public boolean targetEndTypeIsComposite() {
        return getStyle().getLineEndType().equals("opaque_rhombus");
    }

    public boolean targetEndTypeIsNavigable() {
        return getStyle().getLineEndType().equals("open_arrow");
    }

    public boolean targetEndTypeIsNone() {
        return getStyle().getLineEndType().equals("none");
    }

    public boolean isAssociation() {
        if (!getStyle().getBorderStyle().equals("normal"))
            return false;
        if ((sourceEndTypeIsAggregate()
                || sourceEndTypeIsComposite()
                || sourceEndTypeIsNavigable()
                || sourceEndTypeIsNone()) &&
                (targetEndTypeIsNavigable() || targetEndTypeIsNone()))
            return true;
        if ((targetEndTypeIsAggregate()
                || targetEndTypeIsComposite()
                || targetEndTypeIsNavigable()
                || targetEndTypeIsNone()) &&
                (sourceEndTypeIsNavigable() || sourceEndTypeIsNone()))
            return true;
        return false;
    }

    public boolean isDependency() {
        if (!getStyle().getBorderStyle().equals("dashed"))
            return false;
        if (getStyle().getLineStartType().equals("open_arrow")
                && getStyle().getLineEndType().equals("none")) {
            return true;
        }
        if (getStyle().getLineStartType().equals("none")
                && getStyle().getLineEndType().equals("open_arrow")) {
            return true;
        }
        return false;
    }

    public boolean isGeneralization() {
        if (!getStyle().getBorderStyle().equals("normal"))
            return false;

        if (getStyle().getLineStartType().equals("block")
                && getStyle().getLineEndType().equals("none")) {
            return true;
        }
        if (getStyle().getLineStartType().equals("none")
                && getStyle().getLineEndType().equals("block")) {
            return true;
        }
        return false;
    }

    public boolean isRealization() {
        if (!getStyle().getBorderStyle().equals("dashed"))
            return false;

        if (getStyle().getLineStartType().equals("block")
                && getStyle().getLineEndType().equals("none")) {
            return true;
        }
        if (getStyle().getLineStartType().equals("none")
                && getStyle().getLineEndType().equals("block")) {
            return true;
        }
        return false;
    }

    public Id getSourceClass() {
        if (!isAssociation()) return null;
        return getStartWidget();
    }

    public Id getTargetClass() {
        if (!isAssociation()) return null;
        return getEndWidget();
    }

    public Id getSuperClass() {
        if (!isGeneralization()) return null;
        if (getStyle().getLineEndType().equals("block")) {
            return getEndWidget();
        }
        return getStartWidget();
    }

    public Id getSubClass() {
        if (!isGeneralization()) return null;
        if (getStyle().getLineEndType().equals("block")) {
            return getStartWidget();
        }
        return getEndWidget();
    }

    public Id getClient() {
        if (isDependency()) {
            if (getStyle().getLineEndType().equals("open_arrow")) {
                return getStartWidget();
            }
            return getEndWidget();

        } else if (isRealization()) {
            if (getStyle().getLineEndType().equals("block")) {
                return getStartWidget();
            }
            return getEndWidget();
        }
        return null;
    }

    public Id getSupplier() {
        if (isDependency()) {
            if (getStyle().getLineEndType().equals("open_arrow")) {
                return getEndWidget();
            }
            return getStartWidget();

        } else if (isRealization()) {
            if (getStyle().getLineEndType().equals("block")) {
                return getEndWidget();
            }
            return getStartWidget();
        }
        return null;
    }

    public boolean isMindmap() {
        return this.mindmap != null;
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", type:'" + type + '\'' +
                ", startWidget:" + startWidget.toString() +
                ", endWidget:" + endWidget.toString() +
                ", style:" + style +
                ", mindmap:" + mindmap +
                '}';
    }

    @Override
    public String toJSON() {
        try {
            final SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("lineFilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                    "type", "startWidget", "endWidget", "style"
            ));

            final ObjectMapper mapper = new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .setFilterProvider(filterProvider);
            return mapper
                    // .writerFor(IMiroLine.class)
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String toPartialJSON() {
        try {
            final SimpleFilterProvider filterProvider = new SimpleFilterProvider();
            filterProvider.addFilter("lineFilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                    "style"
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
