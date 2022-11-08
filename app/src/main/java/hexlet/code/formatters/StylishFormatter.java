package hexlet.code.formatters;

import hexlet.code.ComparisonSign;
import hexlet.code.Differ;

import java.util.Map;
import java.util.stream.Collectors;

public class StylishFormatter implements Formatter {

    @Override
    public String format(Map<String, Differ.PairOfValues> resultMap) {
        String delimiter = "\n";
        String indentation = "  ";

        var sortedResult = resultMap.keySet().stream().sorted().map((key) -> {
            Differ.PairOfValues pairOfValues = resultMap.get(key);
            var value1 = pairOfValues.getValue1();
            var value2 = pairOfValues.getValue2();
            ComparisonSign comparisonSign = pairOfValues.getComparisonSign();

            switch (comparisonSign) {
                case FIRST_IS_ABSENT:
                    return "+ " + key + ": " + value2;
                case SECOND_IS_ABSENT:
                    return "- " + key + ": " + value1;
                case EQUALS:
                    return "  " + key + ": " + value1;
                case NOT_EQUALS:
                    var val1 = "- " + key + ": " + value1;
                    var val2 = "+ " + key + ": " + value2;
                    return val1 + delimiter + indentation + val2;
                default:
                    throw new RuntimeException("Непонятно что происходит, неожиданные значения");
            }
        }).map((str) -> indentation + str).collect(Collectors.joining(delimiter));

        return "{" + delimiter + sortedResult + delimiter + "}";
    }
}
