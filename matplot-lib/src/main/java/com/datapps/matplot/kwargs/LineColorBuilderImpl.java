package com.datapps.matplot.kwargs;

import com.datapps.matplot.builder.Builder;
import com.datapps.matplot.builder.CompositeBuilder;

/**
 * created by <icedong> on <2018/11/27 16:19>
 */
public class LineColorBuilderImpl<T extends Builder> implements LineColorBuilder<T> {

    private final CompositeBuilder<T> innerBuilder;

    public LineColorBuilderImpl(CompositeBuilder<T> innerBuilder) {
        this.innerBuilder = innerBuilder;
    }

    @Override
    public T color(String arg) {
        return innerBuilder.addToKwargs("color", arg);
    }

    @Override
    public T color(LineColor arg) {
        return color(arg.getShortName());
    }
}
