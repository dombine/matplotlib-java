package com.datapps.matplot.builder;

import java.util.List;

import com.datapps.matplot.kwargs.LineColorBuilder;
import com.datapps.matplot.kwargs.Line2DBuilder;
import com.datapps.matplot.kwargs.MarkerBuilder;


/**
 * matplotlib.pyplot.plot(*args, **kwargs)
 */
public interface PlotBuilder extends Builder, Line2DBuilder<PlotBuilder>, LineColorBuilder<PlotBuilder>, MarkerBuilder<PlotBuilder> {

    PlotBuilder add(List<? extends Number> nums);

    PlotBuilder add(List<? extends Number> x, List<? extends Number> y);

    PlotBuilder add(List<? extends Number> x, List<? extends Number> y, String fmt);
}
