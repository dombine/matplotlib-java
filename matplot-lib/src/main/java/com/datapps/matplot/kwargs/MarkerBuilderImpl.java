package com.datapps.matplot.kwargs;

import com.datapps.matplot.builder.Builder;
import com.datapps.matplot.builder.CompositeBuilder;

/**
 * created by <icedong> on <2018/11/27 16:47>
 */
public class MarkerBuilderImpl<T extends Builder> implements MarkerBuilder<T> {

    private final CompositeBuilder<T> innerBuilder;

    public MarkerBuilderImpl(CompositeBuilder<T> innerBuilder) {
        this.innerBuilder = innerBuilder;
    }

    @Override
    public T marker(String arg) {
        return innerBuilder.addToKwargs("marker", arg);
    }
}
