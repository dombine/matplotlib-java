package com.datapps.matplot.builder;

import java.util.List;

import com.datapps.matplot.kwargs.Line2DBuilder;
import com.datapps.matplot.kwargs.LineColorBuilder;
import com.datapps.matplot.kwargs.MarkerBuilder;


/**
 * matplotlib.pyplot.plot(*args, **kwargs)
 */
public interface BarBuilder extends Builder, Line2DBuilder<BarBuilder>, LineColorBuilder<BarBuilder> {

    BarBuilder add(List<? extends Number> nums);

    BarBuilder add(List<? extends Number> x, List<? extends Number> y);

    BarBuilder add(List<? extends Number> x, List<? extends Number> y, String fmt);
}
