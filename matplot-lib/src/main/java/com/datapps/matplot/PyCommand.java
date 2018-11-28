package com.datapps.matplot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.datapps.matplot.config.PythonConfig;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PyCommand {

    private final static Logger LOGGER = LoggerFactory.getLogger(PyCommand.class);
    private final static Pattern ERROR_PAT = Pattern.compile("^.+Error:");
    private final PythonConfig pythonConfig;

    public PyCommand(PythonConfig pythonConfig) {
        this.pythonConfig = pythonConfig;
    }

    public PyCommand() {
        this.pythonConfig = PythonConfig.systemDefaultPythonConfig();
    }

    private List<String> buildCommandArgs(String scriptPath) {
        StringBuilder shell = new StringBuilder();
        if (!Strings.isNullOrEmpty(pythonConfig.getPyenv())) {
            shell.append("pyenv shell ").append(pythonConfig.getPyenv()).append("; ");

            if (!Strings.isNullOrEmpty(pythonConfig.getVirtualenv())) {
                shell.append("export PYENV_VIRTUALENV_DISABLE_PROMPT=1; ");
                shell.append("pyenv activate ").append(pythonConfig.getVirtualenv()).append("; ");
            }
            shell.append("python ").append(scriptPath);
        }

        List<String> com;
        if (!Strings.isNullOrEmpty(pythonConfig.getPythonBinPath())) {
            com = Lists.newArrayList(pythonConfig.getPythonBinPath(), scriptPath);
        } else if (shell.length() != 0) {
            // -l: Use login shell
            com = Lists.newArrayList("bash", "-l", "-c", shell.toString());
        } else {
            // system's default
            com = Lists.newArrayList("python", scriptPath);
        }

        LOGGER.debug("Commands... : {}", com);
        return com;
    }

    private void command(List<String> commands) throws IOException, PythonExecutionException {
        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();

        // stdout
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }

        // stderr
        // TODO: have a common way with stdout

        br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder sb = new StringBuilder();
        line = br.readLine();
        boolean hasError = false;
        while (line != null) {
            sb.append(line).append('\n');
            Matcher matcher = ERROR_PAT.matcher(line);
            if (matcher.find()) {
                hasError = true;
            }
            line = br.readLine();
        }

        String msg = sb.toString();
        if (hasError) {
            LOGGER.error(msg);
            throw new PythonExecutionException("Python execution error: " + msg);
        }
        if (!msg.isEmpty()) {
            LOGGER.warn(msg);
        }
    }

    private void writeFile(String pythonScript, File script) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(script)));
        bw.write(pythonScript);
        bw.close();
    }

    public void execute(List<String> list) throws IOException, PythonExecutionException {
        String pythonScript = Joiner.on('\n').join(list);
        this.execute(pythonScript);
    }

    public void execute(String pythonScript) throws IOException, PythonExecutionException {
        File tmpDir = Files.createTempDir();
        tmpDir.deleteOnExit();
        File script = new File(tmpDir, "exec.py");

        writeFile(pythonScript, script);

        String scriptPath = Paths.get(script.toURI()).toAbsolutePath().toString();
        command(buildCommandArgs(scriptPath));
        tmpDir.delete();
    }
}
