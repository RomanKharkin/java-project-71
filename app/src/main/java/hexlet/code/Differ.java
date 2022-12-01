package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Formatters;
import hexlet.code.parsers.Parsers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static hexlet.code.differ.DiffCalculator.getDifferences;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatterName) throws Exception {
        var map1 = getData(filePath1);
        var map2 = getData(filePath2);

        var resultMap = getDifferences(map1, map2);

        Formatter formatter = Formatters.getFormatterByName(formatterName);
        return formatter.format(resultMap);
    }

    public static Map<String, Object> getData(String fileName) throws Exception {
        var format = getDataFormat(fileName);
        var parser = Parsers.getParserByFormat(format);
        var fileContent = getFileContent(fileName);
        return parser.parse(fileContent);
    }

    private static String getDataFormat(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            return fileName.substring(index + 1);
        }

        return null;
    }

    private static String getFileContent(String fileName) throws Exception {
        var path = Path.of(fileName);
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        String content = Files.readString(path);

        return content;
    }
}
