package hexlet.code.formatters;

import hexlet.code.differ.DiffClass;
import hexlet.code.Operation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(Map<String, DiffClass> resultMap) {
        String delimiter = "\n";

        var sortedResult = resultMap.keySet().stream().sorted().filter((key) -> resultMap.get(key).getOperation()
                != Operation.UNCHAHGED).map((key) -> {
                    DiffClass diffClass = resultMap.get(key);
                    var value1 = diffClass.getValue1();
                    var value2 = diffClass.getValue2();
                    Operation operation = diffClass.getOperation();

                    switch (operation) {
                        case ADD:
                            return "Property " + "'" + key + "'" + " was added with value: " + valueToString(value2);
                        case REMOVE:
                            return "Property " + "'" + key + "'" + " was removed";
                        case REPLACE:
                            return "Property " + "'" + key + "'" + " was updated. From " + valueToString(value1)
                                    + " to "
                                    + valueToString(value2);
                        default:
                            throw new RuntimeException("Неизвестная операция");
                    }
                }).collect(Collectors.joining(delimiter));

        return sortedResult;
    }

    private String valueToString(Object value) {
        if (value instanceof Map<?, ?> || value instanceof List<?>) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value.toString();
    }
}
