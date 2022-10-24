package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {

    public static String getFileContent(Path path) throws Exception {

        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        // Читаем файл
        String content = Files.readString(path);

        return content;
    }

    public static String generate(Path filePath1, Path filePath2) throws Exception {

        String fileContent1 = getFileContent(filePath1);
        var map1 = JsonToMapMapper.convert(fileContent1);

        String fileContent2 = getFileContent(filePath2);
        var map2 = JsonToMapMapper.convert(fileContent2);

        Map<String, PairOfValues> resultMap = new HashMap();
        map1.keySet().stream().forEach((key) -> {
            resultMap.putIfAbsent(key, null);
        });
        map2.keySet().stream().forEach((key) -> {
            resultMap.putIfAbsent(key, null);
        });

        resultMap.keySet().stream().forEach(key -> resultMap.put(key, new PairOfValues(map1.get(key), map2.get(key))));

        return result(resultMap);
    }

    public static String result(Map<String, PairOfValues> resultMap) {
        String delimiter = "\n";
        String indentation = "  ";

        var sortedResult = resultMap.keySet().stream().sorted().map((key) -> {
                    var value1 = resultMap.get(key).getValue1();
                    var value2 = resultMap.get(key).getValue2();

                    if (value1 == null) {
                        return "+ " + key + ": " + value2;
                    }

                    if (value2 == null) {
                        return "- " + key + ": " + value1;
                    }

                    if (value1.equals(value2)) {
                        return "  " + key + ": " + value1;
                    }

                    if (!value1.equals(value2)) {
                        var val1 = "- " + key + ": " + value1;
                        var val2 = "+ " + key + ": " + value2;
                        return val1 + delimiter + indentation + val2;
                    }

                    return "Feller";
                })
                .map((str) -> indentation + str)
                .collect(Collectors.joining(delimiter));

        return "{" + delimiter + sortedResult + delimiter + "}";
    }


    private static class PairOfValues {
        Object value1;
        Object value2;

        public PairOfValues(Object value1, Object value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public Object getValue1() {
            return value1;
        }

        public Object getValue2() {
            return value2;
        }

        @Override
        public String toString() {
            return "PairOfValues{" + "value1=" + value1 + ", value2=" + value2 + '}';
        }
    }
}
