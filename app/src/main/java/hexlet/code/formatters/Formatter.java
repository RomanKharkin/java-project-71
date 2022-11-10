package hexlet.code.formatters;

import hexlet.code.Differ;

import java.io.IOException;
import java.util.Map;

public interface Formatter {
    String format(Map<String, Differ.DiffClass> resultMap) throws IOException;
}
