package com.datapps.matplot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ExecutorImpl Tester.
 *
 * created by <icedong> on <11/27/2018>
 */
public class PyCommandTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: execute(String pythonScript)
     */
    @Test
    public void testExecute() throws Exception {

        String pythonScript = "# coding=utf-8\n"
            + "import matplotlib.pyplot as plt\n"
            + "import numpy as np\n"
            + "\n"
            + "x = np.array([1, 2, 3, 4, 5, 6, 7, 8])\n"
            + "y = np.array([3, 5, 7, 6, 2, 6, 10, 15])\n"
            + "# plt.plot(x, y, 'r', ls='-')  # 折线 1 x 2 y 3 color\n"
            + "# plt.plot(x, y, 'r', lw=1)  # 4 line w\n"
            + "# 折线 饼状 柱状\n"
            + "# x = np.array([1, 2, 3, 4, 5, 6, 7, 8])\n"
            + "# y = np.array([13, 25, 17, 36, 21, 16, 10, 15])\n"
            + "plt.bar(x, y, 0.1, alpha=1, color='b')  # 5 color 4 透明度 3 0.9\n"
            + "plt.show()\n";

        PyCommand command = new PyCommand();
        command.execute(pythonScript);

    }
}
