package hexlet.code.differ;

import com.fasterxml.jackson.databind.node.MissingNode;
import hexlet.code.Operation;

import java.util.Objects;

public final class DiffClass {
    private Object value1;
    private Object value2;

    DiffClass(Object value11, Object value22) {
        value1 = value11;
        value2 = value22;
    }

    public Object getValue1() {
        return value1;
    }

    public Object getValue2() {
        return value2;
    }

    public Operation getOperation() {
        if (value1 == MissingNode.getInstance()) {
            return Operation.ADD;
        }

        if (value2 == MissingNode.getInstance()) {
            return Operation.REMOVE;
        }

        if (!Objects.equals(value1, value2)) {
            return Operation.REPLACE;
        }

        return Operation.UNCHAHGED;
    }
}
