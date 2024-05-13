package org.acme.rest.engine.domain.common;

public class Constants {
    public static final String OPERATOR_KEY = "operator";
    public static final String TYPE_KEY = "type";
    public static final String OPERAND_KEY = "operand";
    public static final String OPERANDS_KEY = "operands";
    public static final String LEFT_OPERAND_KEY = "leftOperand";
    public static final String RIGHT_OPERAND_KEY = "rightOperand";
    public static final String VARIABLE_KEY = "variable";

    // Boolean operators
    public static final String AND_KEY = "and";
    public static final String OR_KEY = "or";
    public static final String NOT_KEY = "not";

    // Number operators
    public static final String PLUS_KEY = "plus";
    public static final String MINUS_KEY = "minus";
    public static final String MULT_KEY = "mult";
    public static final String DIV_KEY = "div";

    // Mixed operators
    public static final String EQUALS_KEY = "equals";
    public static final String ASSIGN_KEY = "assign";
    public static final String CAST_KEY = "cast";

    private Constants() {};
}
