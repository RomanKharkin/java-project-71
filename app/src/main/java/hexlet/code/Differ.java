package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Formatters;

import static hexlet.code.differ.DifferClass.getData;
import static hexlet.code.differ.DifferClass.getDifferences;

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
}
