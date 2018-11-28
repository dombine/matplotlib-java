package com.datapps.matplot.kwargs;

import com.datapps.matplot.builder.Builder;

/**
 * created by <icedong> on <2018/11/27 16:19>
 */
public interface LineColorBuilder<T extends Builder> extends KwargsBuilder {

    /**
     * 支持十六进制的颜色和英文表示
     */
    T color(String arg);

    /**
     * 系统内置了几个
     */
    T color(LineColor arg);

    enum LineColor {

        BLUE("b", "blue"),
        RED("r", "red"),
        GREEN("g", "green"),
        YELLOW("y", "yellow"),
        CYAN("c", "cyan"),
        MAGENTA("m", "magenta"),
        BLACK("k", "black"),
        WHITE("w", "white");

        private String shortName;
        private String name;

        LineColor(String shortName, String name) {
            this.shortName = shortName;
            this.name = name;
        }

        public String getShortName() {
            return this.shortName;
        }

        public String getName() {
            return this.name;
        }
    }
}
