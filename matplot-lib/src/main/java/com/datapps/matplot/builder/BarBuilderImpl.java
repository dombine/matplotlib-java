package com.datapps.matplot.builder;

import java.util.List;

import com.datapps.matplot.kwargs.Line2DBuilder;
import com.datapps.matplot.kwargs.Line2DBuilderImpl;
import com.datapps.matplot.kwargs.LineColorBuilder;
import com.datapps.matplot.kwargs.LineColorBuilderImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BarBuilderImpl implements BarBuilder {

    private final static Logger LOGGER = LoggerFactory.getLogger(BarBuilderImpl.class);

    private CompositeBuilder<BarBuilder> innerBuilder = new CompositeBuilder<>(this);
    private Line2DBuilder<BarBuilder> line2DBuilder = new Line2DBuilderImpl<>(innerBuilder);
    private LineColorBuilder<BarBuilder> colorBuilder = new LineColorBuilderImpl<>(innerBuilder);

    @Override
    public BarBuilder add(List<? extends Number> x) {
        return innerBuilder.addToArgs(x);
    }

    @Override
    public BarBuilder add(List<? extends Number> x, List<? extends Number> y) {
        innerBuilder.addToArgs(x);
        return innerBuilder.addToArgs(y);
    }

    @Override
    public BarBuilder add(List<? extends Number> x, List<? extends Number> y, String fmt) {
        innerBuilder.addToArgs(x);
        innerBuilder.addToArgs(y);
        return innerBuilder.addToArgsWithoutQuoting(fmt);
    }

    @Override
    public BarBuilder linestyle(String arg) {
        return line2DBuilder.linestyle(arg);
    }

    @Override
    public BarBuilder ls(String arg) {
        return line2DBuilder.ls(arg);
    }

    @Override
    public BarBuilder linewidth(String arg) {
        return line2DBuilder.linewidth(arg);
    }

    @Override
    public BarBuilder lw(String arg) {
        return line2DBuilder.lw(arg);
    }

    @Override
    public BarBuilder label(String arg) {
        return line2DBuilder.label(arg);
    }

    @Override
    public String build() {
        return innerBuilder.build();
    }

    @Override
    public String getMethodName() {
        return "bar";
    }

    @Override
    public BarBuilder color(String arg) {
        return colorBuilder.color(arg);
    }

    @Override
    public BarBuilder color(LineColor arg) {
        return colorBuilder.color(arg.getShortName());
    }
}
