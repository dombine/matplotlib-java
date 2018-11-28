package com.datapps.matplot.plot;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.datapps.matplot.PyCommand;
import com.datapps.matplot.PythonExecutionException;
import com.datapps.matplot.builder.ArgsBuilderImpl;
import com.datapps.matplot.builder.Builder;
import com.datapps.matplot.builder.CLabelBuilder;
import com.datapps.matplot.builder.CLabelBuilderImpl;
import com.datapps.matplot.builder.ContourBuilder;
import com.datapps.matplot.builder.ContourBuilderImpl;
import com.datapps.matplot.builder.HistBuilder;
import com.datapps.matplot.builder.HistBuilderImpl;
import com.datapps.matplot.builder.LabelBuilder;
import com.datapps.matplot.builder.LabelBuilderImpl;
import com.datapps.matplot.builder.LegendBuilder;
import com.datapps.matplot.builder.LegendBuilderImpl;
import com.datapps.matplot.builder.PColorBuilder;
import com.datapps.matplot.builder.PColorBuilderImpl;
import com.datapps.matplot.builder.PlotBuilder;
import com.datapps.matplot.builder.PlotBuilderImpl;
import com.datapps.matplot.builder.SaveFigBuilder;
import com.datapps.matplot.builder.SaveFigBuilderImpl;
import com.datapps.matplot.builder.ScaleBuilder;
import com.datapps.matplot.builder.ScaleBuilderImpl;
import com.datapps.matplot.builder.SubplotBuilder;
import com.datapps.matplot.builder.SubplotBuilderImpl;
import com.datapps.matplot.builder.TextBuilder;
import com.datapps.matplot.builder.TextBuilderImpl;
import com.datapps.matplot.config.PythonConfig;
import com.google.common.annotations.VisibleForTesting;

public class PlotImpl implements Plot {

    private final boolean dryRun;
    private final PythonConfig pythonConfig;
    @VisibleForTesting
    List<Builder> registeredBuilders = new LinkedList<>();
    private List<Builder> showBuilders = new LinkedList<>();

    PlotImpl(PythonConfig pythonConfig, boolean dryRun) {
        this.pythonConfig = pythonConfig;
        this.dryRun = dryRun;
    }

    @VisibleForTesting
    PlotImpl(boolean dryRun) {
        this(PythonConfig.systemDefaultPythonConfig(), dryRun);
    }

    @Override
    public LegendBuilder legend() {
        LegendBuilder builder = new LegendBuilderImpl();
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public void title(String title) {
        registeredBuilders.add(new ArgsBuilderImpl("title", title));
    }

    @Override
    public LabelBuilder xlabel(String label) {
        LabelBuilder builder = LabelBuilderImpl.xLabelBuilder(label);
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public LabelBuilder ylabel(String label) {
        LabelBuilder builder = LabelBuilderImpl.yLabelBuilder(label);
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public ScaleBuilder xscale(ScaleBuilder.Scale scale) {
        ScaleBuilder builder = ScaleBuilderImpl.xScaleBuilder(scale);
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public ScaleBuilder yscale(ScaleBuilder.Scale scale) {
        ScaleBuilder builder = ScaleBuilderImpl.yScaleBuilder(scale);
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public void xlim(Number xmin, Number xmax) {
        registeredBuilders.add(new ArgsBuilderImpl("xlim", xmin, xmax));
    }

    @Override
    public void ylim(Number ymin, Number ymax) {
        registeredBuilders.add(new ArgsBuilderImpl("ylim", ymin, ymax));
    }

    @Override
    public TextBuilder text(double x, double y, String s) {
        TextBuilder builder = new TextBuilderImpl(x, y, s);
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public PlotBuilder plot() {
        PlotBuilder builder = new PlotBuilderImpl();
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public ContourBuilder contour() {
        ContourBuilder builder = new ContourBuilderImpl();
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public PColorBuilder pcolor() {
        PColorBuilder builder = new PColorBuilderImpl();
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public HistBuilder hist() {
        HistBuilder builder = new HistBuilderImpl();
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public CLabelBuilder clabel(ContourBuilder contour) {
        CLabelBuilder builder = new CLabelBuilderImpl(contour);
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public SaveFigBuilder savefig(String fname) {
        SaveFigBuilder builder = new SaveFigBuilderImpl(fname);
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public SubplotBuilder subplot(int nrows, int ncols, int index) {
        SubplotBuilder builder = new SubplotBuilderImpl(nrows, ncols, index);
        registeredBuilders.add(builder);
        return builder;
    }

    @Override
    public void close() {
        registeredBuilders.add(new ArgsBuilderImpl("close"));
    }

    @Override
    public void close(String name) {
        registeredBuilders.add(new ArgsBuilderImpl("close", name));
    }

    @Override
    public void executeSilently() throws IOException, PythonExecutionException {
        List<String> scriptLines = new LinkedList<>();
        scriptLines.add("import numpy as np");
        scriptLines.add("import matplotlib as mpl");
        scriptLines.add("mpl.use('Agg')");
        scriptLines.add("import matplotlib.pyplot as plt");
        registeredBuilders.forEach(b -> scriptLines.add(b.build()));
        showBuilders.forEach(b -> scriptLines.add(b.build()));
        PyCommand command = new PyCommand(pythonConfig);
        command.execute(scriptLines);
    }

    /**
     * matplotlib.pyplot.show(*args, **kw)
     */
    @Override
    public void show() throws IOException, PythonExecutionException {
        List<String> scriptLines = new LinkedList<>();
        scriptLines.add("import numpy as np");
        if (dryRun) {
            // No need DISPLAY for test run
            scriptLines.add("import matplotlib as mpl");
            scriptLines.add("mpl.use('Agg')");
        }
        scriptLines.add("import matplotlib.pyplot as plt");
        registeredBuilders.forEach(b -> scriptLines.add(b.build()));

        // show
        if (!dryRun) {
            scriptLines.add("plt.show()");
        }

        PyCommand command = new PyCommand(pythonConfig);
        command.execute(scriptLines);

        // After showing, registered plot is cleared
        registeredBuilders.clear();
    }

}
