package hexlet.code;

import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "0.0.1",
        description = "Compares two configuration files and shows a difference.")
class App implements Runnable {

//    @CommandLine.Option(names = "-hV") int x;

    @CommandLine.Option(names = { "-f", "--format" },
            paramLabel = "format",
            description = "output format [default: stylish]")
            String was_ist_das;

    @CommandLine.Parameters(paramLabel = "filepath1", description = "path to first file")
    File filepath1;

    @CommandLine.Parameters(paramLabel = "filepath2", description = "path to second file")
    File filepath2;

    public static void main(String... args) {
        System.out.println("Hello world!");
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
    }
}