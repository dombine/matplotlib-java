package com.datapps.matplot.builder;

/**
 * matplotlib.pyplot.savefig(fname, **kwargs)
 */
public interface SaveFigBuilder extends Builder {

    SaveFigBuilder dpi(double arg);

    SaveFigBuilder facecolor(String arg);

    SaveFigBuilder orientation(Orientation orientation);

    SaveFigBuilder papertype(String arg);

    SaveFigBuilder format(String arg);

    SaveFigBuilder transparent(boolean arg);

    SaveFigBuilder frameon(boolean arg);

    SaveFigBuilder bboxInches(double arg);

    enum Orientation {
        horizontal,
        vertical;
    }

    // `bbox_extra_artists` is not supported yet

}
