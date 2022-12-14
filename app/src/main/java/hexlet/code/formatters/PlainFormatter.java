package hexlet.code.formatters;

import hexlet.code.Operation;
import hexlet.code.differ.DiffEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PlainFormatter implements Formatter {
    @Override
    public String format(Map<String, DiffEntity> resultMap) {
        String delimiter = "\n";

        var sortedResult = resultMap.keySet()
                                   .stream()
                                   .sorted()
                                   .filter((key) -> resultMap.get(key).getOperation() != Operation.UNCHAHGED)
                                   .map((key) -> {
                                       DiffEntity diffEntity = resultMap.get(key);
                                       var value1 = diffEntity.getValue1();
                                       var value2 = diffEntity.getValue2();
                                       Operation operation = diffEntity.getOperation();

                                       switch (operation) {
                                           case ADD:
                                               return "Property " + "'" + key + "'" + " was added with value: "
                                                              + valueToString(value2);
                                           case REMOVE:
                                               return "Property " + "'" + key + "'" + " was removed";
                                           case REPLACE:
                                               return "Property " + "'" + key + "'" + " was updated. From "
                                                              + valueToString(value1) + " to " + valueToString(value2);
                                           default:
                                               throw new Error("Неизвестная операция: " + operation);
                                       }
                                   })
                                   .collect(Collectors.joining(delimiter));

        return sortedResult;
    }


    private String valueToString(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof Map<?, ?> || value instanceof List<?>) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value.toString();
    }
}
