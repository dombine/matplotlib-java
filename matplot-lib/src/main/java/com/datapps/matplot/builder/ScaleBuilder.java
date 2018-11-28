package com.datapps.matplot.builder;

import com.datapps.matplot.kwargs.TextArgsBuilder;

/**
 * matplotlib.pyplot.xscale(scale, **kwargs) matplotlib.pyplot.yscale(scale, **kwargs)
 */
public interface ScaleBuilder extends Builder, TextArgsBuilder<ScaleBuilder> {

    enum Scale {
        linear,
        log,
        logit,
        symlog;
    }

}
