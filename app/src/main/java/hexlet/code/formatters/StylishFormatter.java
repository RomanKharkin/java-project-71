package hexlet.code.formatters;

import hexlet.code.Operation;
import hexlet.code.differ.DiffClass;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public final class StylishFormatter implements Formatter {

    @Override
    public String format(Map<String, DiffClass> resultMap) throws IOException {
        String delimiter = "\n";
        String indentation = "  ";

        var sortedResult = resultMap.keySet().stream().sorted().map((key) -> {
            DiffClass diffClass = resultMap.get(key);
            var value1 = diffClass.getValue1();
            var value2 = diffClass.getValue2();
            Operation operation = diffClass.getOperation();

            switch (operation) {
                case ADD:
                    return "+ " + key + ": " + value2;
                case REMOVE:
                    return "- " + key + ": " + value1;
                case REPLACE:
                    var val1 = "- " + key + ": " + value1;
                    var val2 = "+ " + key + ": " + value2;
                    return val1 + delimiter + indentation + val2;
                case UNCHAHGED:
                    return "  " + key + ": " + value1;
                default:
                    throw new RuntimeException("Неизвестная операция");
            }
        }).map((str) -> indentation + str).collect(Collectors.joining(delimiter));

        return "{" + delimiter + sortedResult + delimiter + "}";
    }
}
