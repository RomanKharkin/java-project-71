package hexlet.code.formatters;

import hexlet.code.differ.DiffClass;

import java.io.IOException;
import java.util.Map;

public interface Formatter {
    String format(Map<String, DiffClass> resultMap) throws IOException;
}
