package hexlet.code.formatters;

import hexlet.code.ComparisonSign;
import hexlet.code.Differ;

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
            ComparisonSign comparisonSign = pairOfValues.getComparisonSign();

            switch (comparisonSign) {
                case FIRST_IS_ABSENT:
                    return "Property " + "'" + key + "'" + " was added with value: " + value2;
                case SECOND_IS_ABSENT:
                    return "Property " + "'" + key + "'" + " was removed";
                case EQUALS:
                    return "Property " + "'" + key + "'" + " was not changed: " + value1;
                case NOT_EQUALS:
                    return "Property " + "'" + key + "'" + " was updated. From " + value1 + " to " + value2;
                default:
                    throw new RuntimeException("Непонятно что происходит, неожиданные значения");
            }
        }).map((str) -> str).collect(Collectors.joining(delimiter));

        return sortedResult;
    }
}
