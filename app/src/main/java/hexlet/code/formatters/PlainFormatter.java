package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Operation;

import java.util.Map;
import java.util.stream.Collectors;

public class PlainFormatter implements Formatter {
    @Override
    public String format(Map<String, Differ.PairOfValues> resultMap) {
        String delimiter = "\n";

        var sortedResult = resultMap.keySet().stream().sorted().map((key) -> {
            Differ.PairOfValues pairOfValues = resultMap.get(key);
            var value1 = pairOfValues.getValue1();
            var value2 = pairOfValues.getValue2();
            Operation operation = pairOfValues.getOperation();


            if (operation == null) {
                return "Property " + "'" + key + "'" + " was not changed: " + value1;
            }

            switch (operation) {
                case ADD:
                    return "Property " + "'" + key + "'" + " was added with value: " + value2;
                case REMOVE:
                    return "Property " + "'" + key + "'" + " was removed";
                case REPLACE:
                    return "Property " + "'" + key + "'" + " was updated. From " + value1 + " to " + value2;
                default:
                    throw new RuntimeException("Неизвестная операция");
            }
        }).collect(Collectors.joining(delimiter));

        return sortedResult;
    }
}
