package com.datapps.matplot.plot;

import java.io.IOException;

import com.datapps.matplot.PythonExecutionException;
import com.datapps.matplot.builder.BarBuilder;
import com.datapps.matplot.builder.CLabelBuilder;
import com.datapps.matplot.builder.ContourBuilder;
import com.datapps.matplot.builder.HistBuilder;
import com.datapps.matplot.builder.LabelBuilder;
import com.datapps.matplot.builder.LegendBuilder;
import com.datapps.matplot.builder.PColorBuilder;
import com.datapps.matplot.builder.PlotBuilder;
import com.datapps.matplot.builder.SaveFigBuilder;
import com.datapps.matplot.builder.ScaleBuilder;
import com.datapps.matplot.builder.SubplotBuilder;
import com.datapps.matplot.builder.TextBuilder;
import com.datapps.matplot.config.PythonConfig;

public interface Plot {

    static Plot create() {
        return new PlotImpl(PythonConfig.systemDefaultPythonConfig(), false);
    }

    static Plot create(PythonConfig pythonConfig) {
        return new PlotImpl(pythonConfig, false);
    }

    LegendBuilder legend();

    void title(String title);

    LabelBuilder xlabel(String label);

    LabelBuilder ylabel(String label);

    ScaleBuilder xscale(ScaleBuilder.Scale scale);

    ScaleBuilder yscale(ScaleBuilder.Scale scale);

    void xlim(Number xmin, Number xmax);

    void ylim(Number ymin, Number ymax);

    TextBuilder text(double x, double y, String s);

    PlotBuilder plot();

    BarBuilder bar();

    ContourBuilder contour();

    PColorBuilder pcolor();

    HistBuilder hist();

    CLabelBuilder clabel(ContourBuilder contour);

    SaveFigBuilder savefig(String fname);

    SubplotBuilder subplot(int nrows, int ncols, int index);

    /**
     * Close a figure window.
     */
    void close();

    /**
     * Close a figure window with name label.
     */
    void close(String name);

    /**
     * Silently execute Python script until here by builders. It is mostly useful to execute `plt.savefig()` without showing by window.
     */
    void executeSilently() throws IOException, PythonExecutionException;

    /**
     * matplotlib.pyplot.show(*args, **kw)
     */
    void show() throws IOException, PythonExecutionException;

}
