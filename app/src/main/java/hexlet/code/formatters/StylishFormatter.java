package hexlet.code.formatters;

import hexlet.code.Operation;
import hexlet.code.differ.DiffEntity;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public final class StylishFormatter implements Formatter {

    @Override
    public String format(Map<String, DiffEntity> resultMap) throws IOException {
        String delimiter = "\n";
        String indentation = "  ";

        var sortedResult = resultMap.keySet().stream().sorted().map((key) -> {
            DiffEntity diffEntity = resultMap.get(key);
            var value1 = diffEntity.getValue1();
            var value2 = diffEntity.getValue2();
            Operation operation = diffEntity.getOperation();

            switch (operation) {
                case ADD:
                    return "+ " + key + ": " + value2;
                case REMOVE:
                    return "- " + key + ": " + value1;
                case REPLACE:
                    var val1 = "- " + key + ": " + valueToString(value1);
                    var val2 = "+ " + key + ": " + valueToString(value2);
                    return val1 + delimiter + indentation + val2;
                case UNCHAHGED:
                    return "  " + key + ": " + value1;
                default:
                    throw new RuntimeException("Неизвестная операция");
            }
        }).map((str) -> indentation + str).collect(Collectors.joining(delimiter));

        return "{" + delimiter + sortedResult + delimiter + "}";
    }

    private String valueToString(Object value) {
        if (value == null) {
            return "null";
        }

        return value.toString();
    }
}
