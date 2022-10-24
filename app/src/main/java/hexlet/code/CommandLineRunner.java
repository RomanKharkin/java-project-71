package hexlet.code;

import picocli.CommandLine;

import java.nio.file.Path;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "0.0.1", description = "Compares two configuration files and shows a difference.")
public class CommandLineRunner implements Runnable {
    @CommandLine.Parameters(paramLabel = "filepath1", description = "path to first file")
    static Path filePath1;

    @CommandLine.Parameters(paramLabel = "filepath2", description = "path to second file")
    static Path filePath2;

    @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    static String format = "string";


    @Override
    public void run() {
        String diff = null;
        try {
            diff = Differ.generate(filePath1, filePath2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(diff);
    }
}
