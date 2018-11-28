package com.datapps.matplot.kwargs;

import com.datapps.matplot.builder.Builder;
import com.datapps.matplot.builder.CompositeBuilder;

public class TextArgsBuilderImpl<T extends Builder> implements TextArgsBuilder<T> {

    private final CompositeBuilder<T> innerBuilder;

    public TextArgsBuilderImpl(CompositeBuilder<T> innerBuilder) {
        this.innerBuilder = innerBuilder;
    }

}
