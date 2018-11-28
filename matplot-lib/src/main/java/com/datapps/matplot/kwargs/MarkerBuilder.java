package com.datapps.matplot.kwargs;

import com.datapps.matplot.builder.Builder;

/**
 * created by <icedong> on <2018/11/27 16:45>
 */
public interface MarkerBuilder <T extends Builder> extends KwargsBuilder {

    T marker(String arg);
}
