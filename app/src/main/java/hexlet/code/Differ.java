package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.parsers.Parser;
import hexlet.code.parsers.YmlParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Differ {
    private static final NullObject NULL_OBJECT = new NullObject();
    private static Parser parser = new YmlParser();
    private static Formatter formatter = new StylishFormatter();

    public static String generate(Path filePath1, Path filePath2) throws Exception {
        String fileContent1 = getFileContent(filePath1);
        var map1 = parser.parse(fileContent1);

        String fileContent2 = getFileContent(filePath2);
        var map2 = parser.parse(fileContent2);

        Map<String, PairOfValues> resultMap = new HashMap();

        map1.keySet().stream().forEach((key) -> {
            resultMap.putIfAbsent(key, null);
        });
        map2.keySet().stream().forEach((key) -> {
            resultMap.putIfAbsent(key, null);
        });

        resultMap.keySet().stream().forEach(key -> {
            var value1 = !map1.containsKey(key) ? null : Optional.ofNullable(map1.get(key)).orElse(NULL_OBJECT);
            var value2 = !map2.containsKey(key) ? null : Optional.ofNullable(map2.get(key)).orElse(NULL_OBJECT);
            resultMap.put(key, new PairOfValues(value1, value2));
        });

        return formatter.format(resultMap);
    }

    private static String getFileContent(Path path) throws Exception {

        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        // Читаем файл
        String content = Files.readString(path);

        return content;
    }

    public static void setFormatter(Formatter formatter) {
        Differ.formatter = formatter;
    }


    public static class PairOfValues {
        Object value1;
        Object value2;

        PairOfValues(Object value1, Object value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public Object getValue1() {
            return value1;
        }

        public Object getValue2() {
            return value2;
        }

        public Operation getOperation() {
            if (value1 == null) {
                return Operation.ADD;
            }

            if (value2 == null) {
                return Operation.REMOVE;
            }

            if (!Objects.equals(value1, value2)) {
                return Operation.REPLACE;
            }

            return null;
        };

        @Override
        public String toString() {
            return "PairOfValues{" + "value1=" + value1 + ", value2=" + value2 + '}';
        }
    }

    private static class NullObject {
        @Override
        public String toString() {
            return "null";
        }
    }
}
