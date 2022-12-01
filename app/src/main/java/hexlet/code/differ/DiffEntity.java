package hexlet.code.differ;

import hexlet.code.Operation;

public final class DiffEntity {
    private Object value1;
    private Object value2;
    private Operation operation;

    public DiffEntity(Object value11, Object value22, Operation operation11) {
        value1 = value11;
        value2 = value22;
        operation = operation11;
    }

    public Object getValue1() {
        return value1;
    }

    public Object getValue2() {
        return value2;
    }

    public Operation getOperation() {
        return operation;
    }
}
