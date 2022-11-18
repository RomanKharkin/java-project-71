package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Formatters;

import static hexlet.code.differ.DifferClass.getDifferences;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatterName) throws Exception {
        Formatter formatter = Formatters.formatterFactory(formatterName);

        var resultMap = getDifferences(filePath1, filePath2);

        return formatter.format(resultMap);
    }


}
