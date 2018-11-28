package com.datapps.matplot.kwargs;

import com.datapps.matplot.builder.Builder;

public interface PatchBuilder<T extends Builder> extends KwargsBuilder {

    T linestyle(String arg);

    T ls(String arg);

    T linewidth(String arg);

    T lw(String arg);

    T label(String arg);

}
