package hexlet.code.runner;

import hexlet.code.differ.Differ;
import picocli.CommandLine;

@CommandLine.Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "0.0.1",
        description = "Compares two configuration files and shows a difference.")
public final class CommandLineRunner implements Runnable {
    @CommandLine.Parameters(paramLabel = "filepath1", description = "path to first file")
    private static String filePath1;

    @CommandLine.Parameters(paramLabel = "filepath2", description = "path to second file")
    private static String filePath2;

    @CommandLine.Option(names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    private static String formatterName;


    @Override
    public void run() {
        String diff = null;
        try {
            diff = Differ.generate(filePath1, filePath2, formatterName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(diff);
    }
}
