package org.acme.rest.engine.visitor;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.rest.engine.domain.Engine;
import org.acme.rest.engine.domain.common.operand.OperandInterface;
import org.acme.rest.engine.domain.rule.Rule;
import org.acme.rest.engine.domain.rule.operation.assign.AssignOperation;
import org.acme.rest.engine.domain.rule.operation.binary.BinaryOperation;
import org.acme.rest.engine.domain.rule.operation.cast.CastOperation;
import org.acme.rest.engine.domain.rule.operation.multiple.MultipleOperation;
import org.acme.rest.engine.domain.rule.operation.unary.UnaryOperation;
import org.acme.rest.engine.domain.rule.value.*;
import org.acme.rest.engine.domain.variable.VariableOperand;

import java.util.List;

@ApplicationScoped
public class EnginePrintVisitor {

    public String print(Engine engine) {
        StringBuilder builder = new StringBuilder();
        for (Rule rule : engine.getRules()) {
            builder.append(print(rule)).append("\n");
        }
        return builder.toString();
    }

    private String print(Rule rule) {
        return "\nRule " + rule.getRuleOrder() + " (" + rule.getRuleName() + "):\n" +
                "when: " + print(rule.getWhen()) + "\n" +
                "then: " + print(rule.getThen()) + "\n";
    }

    private String print(List<OperandInterface> operands) {
        StringBuilder builder = new StringBuilder();
        builder.append(print(operands.get(0)));
        for (int i = 1; i < operands.size(); i++) {
            builder.append(", ").append(print(operands.get(i)));
        }
        return builder.toString();
    }

    private String print(OperandInterface operand) {
        if (operand instanceof ValueInterface) {
            return print((ValueInterface) operand);
        }
        if (operand instanceof AssignOperation) {
            return print((AssignOperation) operand);
        }
        if (operand instanceof CastOperation) {
            return print((CastOperation) operand);
        }
        if (operand instanceof UnaryOperation) {
            return print((UnaryOperation) operand);
        }
        if (operand instanceof BinaryOperation) {
            return print((BinaryOperation) operand);
        }
        if (operand instanceof MultipleOperation) {
            return print((MultipleOperation) operand);
        }
        throw new IllegalStateException("Not implemented yet for operand type" + operand);
    }

    private String print(ValueInterface value) {
        return value.getValue().toString();
//        if (value instanceof VariableOperand) {
//            return value.getValue();
//        }
//        if (value instanceof BooleanValue) {
//            return value.getValue();
//        }
//        if (value instanceof IntegerValue) {
//
//        }
//        if (value instanceof DecimalValue) {
//
//        }
//        if (value instanceof StringValue) {
//
//        }
//        throw new IllegalStateException("Not implemented yet for value" + value);
    }

    private String print(AssignOperation operand) {
        switch (operand.getOperator()) {
            case BOOL_ASSIGN:
            case INT_ASSIGN:
            case DEC_ASSIGN:
                return "(" + operand.getVariable().getValue() + " = " + print(operand.getOperand()) + ")";
        }
        throw new IllegalStateException("Not implemented yet for binary operation" + operand.getOperator());
    }

    private String print(CastOperation operand) {
        switch (operand.getOperator()) {
            case CAST_INT:
            case CAST_DEC:
                return "((" + (operand.getType().getValue()) + ") " + print(operand.getOperand()) + ")";
        }
        throw new IllegalStateException("Not implemented yet for binary operation" + operand.getOperator());
    }

    private String print(UnaryOperation operand) {
        return "";
    }

    private String print(BinaryOperation operand) {
        switch (operand.getOperator()) {
            case BOOL_EQUALS:
            case INT_EQUALS:
            case DEC_EQUALS:
                return "(" + print(operand.getLeftOperand()) + " == " + print(operand.getRightOperand()) + ")";
            case INT_PLUS:
            case DEC_PLUS:
                return "(" + print(operand.getLeftOperand()) + " + " + print(operand.getRightOperand()) + ")";
            case INT_MINUS:
            case DEC_MINUS:
                return "(" + print(operand.getLeftOperand()) + " - " + print(operand.getRightOperand()) + ")";
            case INT_MULT:
            case DEC_MULT:
                return "(" + print(operand.getLeftOperand()) + " * " + print(operand.getRightOperand()) + ")";
            case INT_DIV:
            case DEC_DIV:
                return "(" + print(operand.getLeftOperand()) + " / " + print(operand.getRightOperand()) + ")";
        }
        throw new IllegalStateException("Not implemented yet for binary operation" + operand.getOperator());
    }

    private String print(MultipleOperation operand) {
        String op;
        switch (operand.getOperator()) {
            case AND:
                op = "&&";
                break;
            case OR:
                op = "||";
                break;
            default:
                throw new IllegalStateException("Not implemented yet for multiple operation" + operand.getOperator());
        }
        StringBuilder builder = new StringBuilder();
        List<OperandInterface> operands = operand.getOperands();
        builder.append("(").append(print(operands.get(0)));
        for (int i = 1; i < operands.size(); i++) {
            builder.append(" ").append(op).append(" ").append(print(operands.get(i)));
        }
        builder.append(")");
        return builder.toString();
    }
}
