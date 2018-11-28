package com.datapps.matplot.utils;

import java.util.List;
import java.util.stream.Collectors;

public enum TypeConversion {
    INSTANCE;

    private final static String PYTHON_NONE = "None";

    public List<Object> typeSafeList(List<? extends Number> orgList) {
        return orgList.stream().map(x -> x == null ? PYTHON_NONE : x).collect(Collectors.toList());
    }
}
