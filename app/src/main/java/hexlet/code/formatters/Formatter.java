package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.Map;

public interface Formatter {
    String format(Map<String, Differ.PairOfValues> resultMap);
}
