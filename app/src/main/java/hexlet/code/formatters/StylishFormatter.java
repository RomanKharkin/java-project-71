package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Operation;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class StylishFormatter implements Formatter {

    @Override
    public String format(Map<String, Differ.PairOfValues> resultMap) throws IOException {
        String delimiter = "\n";
        String indentation = "  ";

        var sortedResult = resultMap.keySet().stream().sorted().map((key) -> {
            Differ.PairOfValues pairOfValues = resultMap.get(key);
            var value1 = pairOfValues.getValue1();
            var value2 = pairOfValues.getValue2();
            Operation operation = pairOfValues.getOperation();

            if (operation == null) {
                return "  " + key + ": " + value1;
            }

            switch (operation) {
                case ADD:
                    return "+ " + key + ": " + value2;
                case REMOVE:
                    return "- " + key + ": " + value1;
                case REPLACE:
                    var val1 = "- " + key + ": " + value1;
                    var val2 = "+ " + key + ": " + value2;
                    return val1 + delimiter + indentation + val2;
                default:
                    throw new RuntimeException("Неизвестная операция");
            }
        }).map((str) -> indentation + str).collect(Collectors.joining(delimiter));

        return "{" + delimiter + sortedResult + delimiter + "}";
    }
}
