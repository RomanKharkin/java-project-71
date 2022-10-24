package hexlet.code;

import picocli.CommandLine;

class App {

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new CommandLineRunner()).execute(args);
        System.exit(exitCode);
    }
}