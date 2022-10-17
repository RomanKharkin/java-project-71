package hexlet.code;

import picocli.CommandLine;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "0.0.1",
        description = "Compares two configuration files and shows a difference.")
class App implements Runnable {

    public static void main(String... args) {
        System.out.println("Hello world!");
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
    }
}