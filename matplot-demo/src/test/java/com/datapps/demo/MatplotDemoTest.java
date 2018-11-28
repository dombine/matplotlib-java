package com.datapps.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.datapps.matplot.plot.Plot;
import com.datapps.matplot.PythonExecutionException;
import com.datapps.matplot.kwargs.LineColorBuilder.LineColor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * MatplotDemo Tester.
 *
 * created by <icedong> on <2018/11/26>
 */
public class MatplotDemoTest {

    private Plot plt;

    @Before
    public void setUp() {
        plt = Plot.create();
    }

    @After
    public void ternDown() {

    }

    @Test
    public void test2() throws IOException, PythonExecutionException {
        List<Double> x = IntStream.rangeClosed(0, (int) ((10) / 0.2)).mapToDouble(s -> s * 0.2).boxed().collect(Collectors.toList());
        List<Double> y1 = x.stream().map(Math::cos).collect(Collectors.toList());
        List<Double> y2 = x.stream().map(Math::sin).collect(Collectors.toList());
        List<Double> y3 = x.stream().map(Math::sqrt).collect(Collectors.toList());
        List<Double> y4 = new ArrayList<>(x);

        //plt.plot().add(x,y1).add(x,y2).add(x,y3).add(x,y4);
        plt.plot().add(x, y1).linestyle("-").linewidth("1.5").label("y=sin(x)").color(LineColor.MAGENTA).marker(".");
        plt.plot().add(x, y2).linestyle("-").linewidth("1.5").label("y=cos(x)").color(LineColor.GREEN).marker("*");
        plt.plot().add(x, y3).linestyle("-").linewidth("1.5").label("y=sqrt(x)").color(LineColor.YELLOW).marker("x");
        plt.plot().add(x, y4).linestyle("-").linewidth("1.5").label("y=x").color(LineColor.BLUE).marker("+");
        plt.legend().loc("upper right");

        //plt.hist().add(x).add(y1).color("#66DD66","#cccccc").linestyle("-").lw("1.5").label("y=sin(x)");
        /*String methodName = "text-" + new Exception().getStackTrace()[0].getMethodName();
        String filename = methodName + "-" + System.currentTimeMillis();
        plt.savefig("/tmp/matplot/" + filename + ".png").dpi(200);
        plt.executeSilently();*/
        plt.show();
    }

    @Test
    public void test() throws IOException, PythonExecutionException {
        List<Double> x = IntStream.rangeClosed(0, (int) ((10) / 0.2)).mapToDouble(s -> s * 0.2).boxed().collect(Collectors.toList());
        plt.hist().add(x);
        plt.title("test");
        /*String methodName = "text-" + new Exception().getStackTrace()[0].getMethodName();
        String filename = methodName + "-" + System.currentTimeMillis();
        plt.savefig("/tmp/matplot/" + filename + ".png").dpi(200);
        plt.executeSilently();*/
        plt.show();
    }

} 
