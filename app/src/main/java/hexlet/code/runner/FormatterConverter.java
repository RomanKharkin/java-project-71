package hexlet.code.runner;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.Formatters;
import picocli.CommandLine;


class FormatterConverter implements CommandLine.ITypeConverter<Formatter> {
    public Formatter convert(String formatterName) throws Exception {

        return Formatters.getFormatterByName(formatterName);
    }
}
