package com.datapps.matplot.builder;

public class LabelBuilderImpl implements LabelBuilder {

    private final String methodName;
    private CompositeBuilder<LabelBuilder> innerBuilder = new CompositeBuilder<>(this);

    public LabelBuilderImpl(String label, String methodName) {
        this.methodName = methodName;
        innerBuilder.addToArgs(label);
    }

    public static LabelBuilderImpl xLabelBuilder(String label) {
        return new LabelBuilderImpl(label, "xlabel");
    }

    public static LabelBuilderImpl yLabelBuilder(String label) {
        return new LabelBuilderImpl(label, "ylabel");
    }

    @Override
    public String build() {
        return innerBuilder.build();
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }
}
