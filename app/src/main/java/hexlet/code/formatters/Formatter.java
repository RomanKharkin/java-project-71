package hexlet.code.formatters;

import hexlet.code.differ.DiffEntity;

import java.io.IOException;
import java.util.Map;

public interface Formatter {
    String format(Map<String, DiffEntity> resultMap) throws IOException;
}
