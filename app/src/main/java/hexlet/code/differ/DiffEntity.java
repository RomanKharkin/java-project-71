package hexlet.code.differ;

import hexlet.code.formatters.Formattable;
import hexlet.code.formatters.Formatter;

import java.util.Objects;

public final class DiffEntity implements Formattable {
    private final String key;
    private final Object value1;
    private final Object value2;

    DiffEntity(String key11, Object value11, Object value22) {
        key = key11;
        value1 = value11;
        value2 = value22;
    }

    @Override
    public Object format(Formatter formatter) {
        if (getValue1() == null) {
            return formatter.add(this);
        }

        if (getValue2() == null) {
            return formatter.remove(this);
        }

        if (!Objects.equals(getValue1(), getValue2())) {
            return formatter.replace(this);
        }

        return formatter.nothing(this);
    }

    public Object getValue1() {
        return value1;
    }

    public Object getValue2() {
        return value2;
    }

    public String getKey() {
        return key;
    }
}
